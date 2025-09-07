package io.goodforgod.api.etherscan.http;

import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Anton Kurako (GoodforGod)
 * 
 * @since 07.09.2025
 */
public interface EthResponse {

    int statusCode();

    @Nullable
    byte[] body();

    @NotNull
    Map<String, List<String>> headers();

    static EthResponse of(int statusCode, @Nullable byte[] body) {
        return new SimpleEthResponse(statusCode, body, null);
    }

    static EthResponse of(int statusCode, @Nullable byte[] body, @Nullable Map<String, List<String>> headers) {
        return new SimpleEthResponse(statusCode, body, headers);
    }
}
