package io.goodforgod.api.etherscan.executor.impl;

import static java.net.HttpURLConnection.*;

import io.goodforgod.api.etherscan.error.EtherScanConnectionException;
import io.goodforgod.api.etherscan.error.EtherScanTimeoutException;
import io.goodforgod.api.etherscan.executor.EthHttpClient;
import io.goodforgod.api.etherscan.util.BasicUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

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

    private HttpURLConnection buildConnection(String urlAsString, String method) throws IOException {
        final URL url = new URL(urlAsString);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setConnectTimeout(connectTimeout);
        connection.setReadTimeout(readTimeout);
        headers.forEach(connection::setRequestProperty);
        return connection;
    }

    @Override
    public String get(final String urlAsString) {
        try {
            final HttpURLConnection connection = buildConnection(urlAsString, "GET");
            final int status = connection.getResponseCode();
            if (status == HTTP_MOVED_TEMP || status == HTTP_MOVED_PERM) {
                return get(connection.getHeaderField("Location"));
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
    public String post(String urlAsString, String dataToPost) {
        try {
            final HttpURLConnection connection = buildConnection(urlAsString, "POST");
            final String contentLength = (BasicUtils.isBlank(dataToPost))
                    ? "0"
                    : String.valueOf(dataToPost.length());
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Content-Length", contentLength);
            connection.setFixedLengthStreamingMode(dataToPost.length());

            connection.setDoOutput(true);
            connection.connect();
            try (OutputStream os = connection.getOutputStream()) {
                os.write(dataToPost.getBytes(StandardCharsets.UTF_8));
            }

            final int status = connection.getResponseCode();
            if (status == HTTP_MOVED_TEMP || status == HTTP_MOVED_PERM) {
                return post(connection.getHeaderField("Location"), dataToPost);
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
