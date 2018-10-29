package io.api.model;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class TokenTransaction extends BaseTransaction {

    private String tokenName;
    private String tokenSymbol;
    private String tokenDecimal;

    public TokenTransaction(String blockNumber, String blockHash, String timeStamp, String hash, String nonce, String confirmations,
                            String transactionIndex, String from, String to, String value,
                            String gas, String gasPrice, String gasUsed, String cumulativeGasUsed, String input, String contractAddress,
                            String tokenName, String tokenSymbol, String tokenDecimal) {
        super(blockNumber, blockHash, timeStamp, hash, nonce, confirmations,
                transactionIndex, from, to, value, gas,
                gasPrice, gasUsed, cumulativeGasUsed, input, contractAddress);
        this.tokenName = tokenName;
        this.tokenSymbol = tokenSymbol;
        this.tokenDecimal = tokenDecimal;
    }

    //<editor-fold desc="Getter">
    public String getTokenName() {
        return tokenName;
    }

    public String getTokenSymbol() {
        return tokenSymbol;
    }

    public String getTokenDecimal() {
        return tokenDecimal;
    }
    //</editor-fold>
}
