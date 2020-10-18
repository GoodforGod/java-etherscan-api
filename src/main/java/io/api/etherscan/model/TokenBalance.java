package io.api.etherscan.model;

import java.math.BigInteger;
import java.util.Objects;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
public class TokenBalance extends Balance {

    private final String tokenContract;

    public TokenBalance(String address, BigInteger balance, String tokenContract) {
        super(address, balance);
        this.tokenContract = tokenContract;
    }

    public String getContract() {
        return tokenContract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        TokenBalance that = (TokenBalance) o;
        return Objects.equals(tokenContract, that.tokenContract);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (tokenContract != null ? tokenContract.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TokenBalance{" +
                "tokenContract='" + tokenContract + '\'' +
                '}';
    }
}
