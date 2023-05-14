package io.goodforgod.api.etherscan.model;

import java.util.Objects;

/**
 * @author GoodforGod
 * @since 28.10.2018
 */
public class Balance {

    /** Balance in Wei */
    private final Wei balance;
    private final String address;

    public Balance(String address, Wei balance) {
        this.address = address;
        this.balance = balance;
    }

    // <editor-fold desc="Getters">
    public String getAddress() {
        return address;
    }

    public Wei getBalanceInWei() {
        return balance;
    }
    // </editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Balance))
            return false;
        Balance balance1 = (Balance) o;
        return Objects.equals(balance, balance1.balance) && Objects.equals(address, balance1.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, address);
    }

    @Override
    public String toString() {
        return "Balance{" +
                "address='" + address + '\'' +
                ", balance=" + balance +
                '}';
    }
}
