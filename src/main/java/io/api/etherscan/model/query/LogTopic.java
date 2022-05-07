package io.api.etherscan.model.query;

import io.api.etherscan.model.Address;
import io.api.etherscan.util.BasicUtils;

import java.util.Objects;

public class LogTopic {

    private final String hex;

    private LogTopic(String hex) {
        this.hex = hex;
    }

    public static LogTopic of(Address address) {
        return of(address.to64Hex());
    }

    public static LogTopic of(String hex) {
        BasicUtils.validateTxHash(hex);
        return new LogTopic(hex);
    }

    public String getHex() {
        return hex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogTopic other = (LogTopic) o;
        return hex.equals(other.hex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hex);
    }

}
