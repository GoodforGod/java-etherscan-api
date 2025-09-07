package io.goodforgod.api.etherscan.http;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Anton Kurako (GoodforGod)
 *
 * @since 07.09.2025
 */
class SimpleEthResponse implements EthResponse {

    private final int statusCode;
    @Nullable
    private final byte[] body;
    private final Map<String, List<String>> headers;

    SimpleEthResponse(int statusCode, @Nullable byte[] body, @Nullable Map<String, List<String>> headers) {
        this.statusCode = statusCode;
        this.body = body;
        this.headers = (headers == null)
                ? Collections.emptyMap()
                : headers;
    }

    @Override
    public int statusCode() {
        return statusCode;
    }

    @Nullable
    @Override
    public byte[] body() {
        return body;
    }

    @Override
    public @NotNull Map<String, List<String>> headers() {
        return headers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SimpleEthResponse that = (SimpleEthResponse) o;
        return statusCode == that.statusCode && Arrays.equals(body, that.body) && Objects.equals(headers, that.headers);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(statusCode, headers);
        result = 31 * result + Arrays.hashCode(body);
        return result;
    }

    @Override
    public String toString() {
        return "{\"statusCode\": " + statusCode + ", \"headers\": " + headers + ", \"body\": \"" + Arrays.toString(body) + "\"}";
    }
}
