package io.api.etherscan.model;

import java.math.BigInteger;

public class TxErc20 extends BaseTxToken {

    private BigInteger tokenDecimal;

    public BigInteger getTokenDecimal() {
        return tokenDecimal;
    }

    @Override
    public String toString() {
        return "TxErc20{" +
                "tokenDecimal=" + tokenDecimal +
                '}' + super.toString();
    }
}
