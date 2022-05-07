package io.api.etherscan.model;

import io.api.etherscan.util.BasicUtils;

import java.util.Objects;

public class Address {

    private final String address;

    private Address(String address) {
        this.address = address;
    }

    public static Address of(String address) {
        BasicUtils.validateAddress(address);
        return new Address(address);
    }

    public String getAddress() {
        return address;
    }

    public String to64Hex() {
        String addressWithoutOx = address.substring(2);
        return "0x" + padLeftZeros(addressWithoutOx);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address other = (Address) o;
        return address.equals(other.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    private String padLeftZeros(String inputString) {
        if (inputString.length() >= 64) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 64 - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }
}
