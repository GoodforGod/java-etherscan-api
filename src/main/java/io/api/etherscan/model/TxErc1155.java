package io.api.etherscan.model;

import java.math.BigInteger;

public class TxErc1155 extends BaseTxToken {

    private BigInteger tokenID;
    private BigInteger tokenValue;

    public BigInteger getTokenID() {
        return tokenID;
    }

    public BigInteger getTokenValue() {
        return tokenValue;
    }

    @Override
    public String toString() {
        return "TxErc1155{" +
                "tokenID=" + tokenID +
                ", tokenValue=" + tokenValue +
                '}' + super.toString();
    }
}
