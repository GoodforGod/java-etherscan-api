package io.goodforgod.api.etherscan.http.impl;

import static java.net.HttpURLConnection.*;

import io.goodforgod.api.etherscan.error.EtherScanConnectionException;
import io.goodforgod.api.etherscan.error.EtherScanTimeoutException;
import io.goodforgod.api.etherscan.http.EthHttpClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import org.jetbrains.annotations.NotNull;

/**
 * Http client implementation
 *
 * @author GoodforGod
 * @see EthHttpClient
 * @since 28.10.2018
 */
public final class UrlEthHttpClient implements EthHttpClient {

    private static final Map<String, String> DEFAULT_HEADERS = new HashMap<>();

    private static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(8);
    private static final Duration DEFAULT_READ_TIMEOUT = Duration.ZERO;

    static {
        DEFAULT_HEADERS.put("Accept-Language", "en");
        DEFAULT_HEADERS.put("Accept-Encoding", "deflate, gzip");
        DEFAULT_HEADERS.put("User-Agent", "Chrome/68.0.3440.106");
        DEFAULT_HEADERS.put("Accept-Charset", "UTF-8");
    }

    private final Map<String, String> headers;
    private final int connectTimeout;
    private final int readTimeout;

    public UrlEthHttpClient() {
        this(DEFAULT_CONNECT_TIMEOUT);
    }

    public UrlEthHttpClient(Duration connectTimeout) {
        this(connectTimeout, DEFAULT_READ_TIMEOUT);
    }

    public UrlEthHttpClient(Duration connectTimeout, Duration readTimeout) {
        this(connectTimeout, readTimeout, DEFAULT_HEADERS);
    }

    /**
     * @param connectTimeout custom connection establish timeout in millis
     * @param readTimeout    custom read timeout in millis
     * @param headers        custom HTTP headers
     */
    public UrlEthHttpClient(Duration connectTimeout,
                            Duration readTimeout,
                            Map<String, String> headers) {
        this.connectTimeout = Math.toIntExact(connectTimeout.toMillis());
        this.readTimeout = Math.toIntExact(readTimeout.toMillis());
        this.headers = Collections.unmodifiableMap(headers);
    }

    private HttpURLConnection buildConnection(URI uri, String method) throws IOException {
        final URL url = uri.toURL();
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setConnectTimeout(connectTimeout);
        connection.setReadTimeout(readTimeout);
        headers.forEach(connection::setRequestProperty);
        return connection;
    }

    @Override
    public @NotNull String get(@NotNull URI uri) {
        try {
            final HttpURLConnection connection = buildConnection(uri, "GET");
            final int status = connection.getResponseCode();
            if (status == HTTP_MOVED_TEMP || status == HTTP_MOVED_PERM) {
                return get(URI.create(connection.getHeaderField("Location")));
            } else if ((status >= HTTP_BAD_REQUEST) && (status < HTTP_INTERNAL_ERROR)) {
                throw new EtherScanConnectionException("Protocol error: " + connection.getResponseMessage());
            } else if (status >= HTTP_INTERNAL_ERROR) {
                throw new EtherScanConnectionException("Server error: " + connection.getResponseMessage());
            }

            final String data = readData(connection);
            connection.disconnect();
            return data;
        } catch (SocketTimeoutException e) {
            throw new EtherScanTimeoutException("Timeout: Could not establish connection for " + connectTimeout + " millis", e);
        } catch (Exception e) {
            throw new EtherScanConnectionException(e.getMessage(), e);
        }
    }

    @Override
    public @NotNull String post(@NotNull URI uri, byte[] body) {
        try {
            final HttpURLConnection connection = buildConnection(uri, "POST");
            final int contentLength = body.length;
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Content-Length", String.valueOf(contentLength));
            connection.setFixedLengthStreamingMode(body.length);

            connection.setDoOutput(true);
            connection.connect();
            try (OutputStream os = connection.getOutputStream()) {
                os.write(body);
            }

            final int status = connection.getResponseCode();
            if (status == HTTP_MOVED_TEMP || status == HTTP_MOVED_PERM) {
                return post(URI.create(connection.getHeaderField("Location")), body);
            } else if ((status >= HTTP_BAD_REQUEST) && (status < HTTP_INTERNAL_ERROR)) {
                throw new EtherScanConnectionException("Protocol error: " + connection.getResponseMessage());
            } else if (status >= HTTP_INTERNAL_ERROR) {
                throw new EtherScanConnectionException("Server error: " + connection.getResponseMessage());
            }

            final String data = readData(connection);
            connection.disconnect();
            return data;
        } catch (SocketTimeoutException e) {
            throw new EtherScanTimeoutException("Timeout: Could not establish connection for " + connectTimeout + " millis", e);
        } catch (Exception e) {
            throw new EtherScanConnectionException(e.getMessage(), e);
        }
    }

    private String readData(HttpURLConnection connection) throws IOException {
        final StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(getStreamReader(connection))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                content.append(inputLine);
        }

        return content.toString();
    }

    private InputStreamReader getStreamReader(HttpURLConnection connection) throws IOException {
        switch (String.valueOf(connection.getContentEncoding())) {
            case "gzip":
                return new InputStreamReader(new GZIPInputStream(connection.getInputStream()), StandardCharsets.UTF_8);
            case "deflate":
                return new InputStreamReader(new InflaterInputStream(connection.getInputStream()), StandardCharsets.UTF_8);
            default:
                return new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
        }
    }
}
