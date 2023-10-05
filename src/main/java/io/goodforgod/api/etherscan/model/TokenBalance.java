package io.goodforgod.api.etherscan.model;

import java.util.Objects;

/**
 * @author GoodforGod
 * @since 31.10.2018
 */
public class TokenBalance extends Balance {

    private final String tokenContract;

    public TokenBalance(String address, Wei balance, String tokenContract) {
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
        if (!(o instanceof TokenBalance))
            return false;
        if (!super.equals(o))
            return false;
        TokenBalance that = (TokenBalance) o;
        return Objects.equals(tokenContract, that.tokenContract);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tokenContract);
    }

    @Override
    public String toString() {
        return "TokenBalance{" +
                "tokenContract=" + tokenContract +
                '}';
    }
}
