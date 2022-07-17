package io.api.etherscan.model;

import java.math.BigInteger;

public class TxErc721 extends BaseTxToken {

    private BigInteger tokenID;
    private BigInteger tokenDecimal;

    public BigInteger getTokenID() {
        return tokenID;
    }

    public BigInteger getTokenDecimal() {
        return tokenDecimal;
    }

    @Override
    public String toString() {
        return "TxErc721{" +
                "tokenID=" + tokenID +
                ", tokenDecimal=" + tokenDecimal +
                '}' + super.toString();
    }
}
