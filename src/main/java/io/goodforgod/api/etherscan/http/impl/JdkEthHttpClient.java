package io.goodforgod.api.etherscan.http.impl;

import io.goodforgod.api.etherscan.error.EtherScanConnectionException;
import io.goodforgod.api.etherscan.error.EtherScanConnectionTimeoutException;
import io.goodforgod.api.etherscan.http.EthHttpClient;
import io.goodforgod.api.etherscan.http.EthResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;

/**
 * Anton Kurako (GoodforGod)
 *
 * @since 07.09.2025
 */
@Internal
public class JdkEthHttpClient implements EthHttpClient {

    private static final Map<String, String> DEFAULT_HEADERS = new HashMap<>();

    private static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(8);
    private static final Duration DEFAULT_READ_TIMEOUT = Duration.ofMinutes(2);

    static {
        DEFAULT_HEADERS.put("Accept-Language", "en");
        DEFAULT_HEADERS.put("Accept-Encoding", "deflate, gzip");
        DEFAULT_HEADERS.put("User-Agent", "Chrome/68.0.3440.106");
        DEFAULT_HEADERS.put("Accept-Charset", "UTF-8");
    }

    private final HttpClient httpClient;
    private final Duration requestTimeout;
    private final Map<String, String> headers;

    public JdkEthHttpClient() {
        this(HttpClient.newBuilder()
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .version(HttpClient.Version.HTTP_2)
                .build(),
                DEFAULT_READ_TIMEOUT,
                DEFAULT_HEADERS);
    }

    public JdkEthHttpClient(HttpClient httpClient) {
        this(httpClient, DEFAULT_READ_TIMEOUT, DEFAULT_HEADERS);
    }

    public JdkEthHttpClient(HttpClient httpClient, Duration requestTimeout) {
        this(httpClient, requestTimeout, DEFAULT_HEADERS);
    }

    public JdkEthHttpClient(HttpClient httpClient, Duration requestTimeout, Map<String, String> headers) {
        this.httpClient = httpClient;
        this.requestTimeout = requestTimeout;
        this.headers = headers;
    }

    @Override
    public EthResponse get(@NotNull URI uri) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .timeout(requestTimeout);

        headers.forEach(requestBuilder::header);

        try {
            HttpResponse<InputStream> response = httpClient.send(requestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            byte[] bodyAsBytes = getDeflatedBytes(response);
            return new EthResponse() {

                @Override
                public int statusCode() {
                    return response.statusCode();
                }

                @Override
                public byte[] body() {
                    return bodyAsBytes;
                }

                @Override
                public @NotNull Map<String, List<String>> headers() {
                    return response.headers().map();
                }
            };
        } catch (HttpConnectTimeoutException e) {
            throw new EtherScanConnectionTimeoutException(
                    "Connection Timeout: Could not establish connection to Etherscan server for "
                            + httpClient.connectTimeout().orElse(DEFAULT_CONNECT_TIMEOUT) + " millis",
                    e);
        } catch (IOException e) {
            throw new EtherScanConnectionException("Etherscan HTTP server network error occurred: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            throw new EtherScanConnectionException("Etherscan HTTP server interrupt exception occurred: " + e.getMessage(), e);
        }
    }

    @Override
    public EthResponse post(@NotNull URI uri, byte[] body) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofByteArray(body))
                .uri(uri)
                .timeout(requestTimeout);

        headers.forEach(requestBuilder::header);

        try {
            HttpResponse<InputStream> response = httpClient.send(requestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            byte[] bodyAsBytes = getDeflatedBytes(response);
            return new EthResponse() {

                @Override
                public int statusCode() {
                    return response.statusCode();
                }

                @Override
                public byte[] body() {
                    return bodyAsBytes;
                }

                @Override
                public @NotNull Map<String, List<String>> headers() {
                    return response.headers().map();
                }
            };
        } catch (HttpConnectTimeoutException e) {
            throw new EtherScanConnectionTimeoutException(
                    "Connection Timeout: Could not establish connection to Etherscan server for "
                            + httpClient.connectTimeout().orElse(DEFAULT_CONNECT_TIMEOUT) + " millis",
                    e);
        } catch (IOException e) {
            throw new EtherScanConnectionException("Etherscan HTTP server network error occurred: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            throw new EtherScanConnectionException("Etherscan HTTP server interrupt exception occurred: " + e.getMessage(), e);
        }
    }

    private static byte[] getDeflatedBytes(HttpResponse<InputStream> response) {
        try {
            Optional<String> encoding = response.headers().firstValue("content-encoding");
            if (encoding.isEmpty()) {
                try (var is = response.body()) {
                    return is.readAllBytes();
                }
            }

            switch (encoding.get().strip().toLowerCase(Locale.ROOT)) {
                case "gzip":
                    return new GZIPInputStream(response.body()).readAllBytes();
                case "deflate":
                    return new InflaterInputStream(response.body()).readAllBytes();
                default:
                    try (var is = response.body()) {
                        return is.readAllBytes();
                    }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
