package io.api.model.utility;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 29.10.2018
 */
public class BalanceTO {

    private String account;
    private String balance;

    public BalanceTO(String account, String balance) {
        this.account = account;
        this.balance = balance;
    }

    public String getAccount() {
        return account;
    }

    public String getBalance() {
        return balance;
    }
}
