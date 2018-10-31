package io.api.model;

import io.api.model.utility.BalanceTO;

import java.math.BigInteger;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class Balance {

    /** Balance in Wei */
    private final Wei balance;
    private final String address;

    public Balance(final String address,
                   final BigInteger balance) {
        this.address = address;
        this.balance = new Wei(balance);
    }

    public static Balance of(BalanceTO balance) {
        return new Balance(balance.getAccount(), new BigInteger(balance.getBalance()));
    }

    //<editor-fold desc="Getters">
    public String getAddress() {
        return address;
    }

    public BigInteger getWei() {
        return balance.getValue();
    }

    public BigInteger getKwei() {
        return balance.getKwei();
    }

    public BigInteger getMwei() {
        return balance.getMwei();
    }

    public BigInteger getGwei() {
        return balance.getGwei();
    }

    public BigInteger getEther() {
        return balance.getEther();
    }
    //</editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Balance balance1 = (Balance) o;

        if (!balance.equals(balance1.balance)) return false;
        return address != null ? address.equals(balance1.address) : balance1.address == null;
    }

    @Override
    public int hashCode() {
        int result = balance.hashCode();
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "address='" + address + '\'' +
                ", balance=" + balance +
                '}';
    }
}
