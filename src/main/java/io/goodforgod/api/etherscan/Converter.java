package io.goodforgod.api.etherscan;

import org.jetbrains.annotations.NotNull;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 14.05.2023
 */
public interface Converter {

    @NotNull
    <T> T fromJson(@NotNull String json, @NotNull Class<T> type);
}
