package io.api.model;

import io.api.model.temporary.BalanceTO;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class Balance {

    /** Balance in Wei */
    private final long balance;
    private final String address;

    public Balance(final String address,
                   final long balance) {
        this.address = address;
        this.balance = balance;
    }

    public static Balance of(BalanceTO balance) {
        return new Balance(balance.getAccount(), Long.valueOf(balance.getBalance()));
    }

    //<editor-fold desc="Getters">
    public String getAddress() {
        return address;
    }

    public long getWei() {
        return balance;
    }

    public double getKwei() {
        return balance / 1000;
    }

    public double getMwei() {
        return balance / 1000000;
    }

    public double getGwei() {
        return balance / 1000000000;
    }

    public double getEther() {
        return balance / 1000000000000000L;
    }
    //</editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Balance balance1 = (Balance) o;

        if (Double.compare(balance1.balance, balance) != 0) return false;
        return address.equals(balance1.address);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = address.hashCode();
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
