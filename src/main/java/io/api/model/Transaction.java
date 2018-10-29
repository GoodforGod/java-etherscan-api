package io.api.model;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class Transaction extends BaseTransaction {

    private String txreceipt_status;
    private boolean isError;

    public Transaction(String blockNumber, String blockHash, String timeStamp, String hash, String nonce, String confirmations,
                       String transactionIndex, String from, String to, String value,
                       String gas, String gasPrice, String gasUsed, String cumulativeGasUsed, String input, String contractAddress,
                       String txreceipt_status, boolean isError) {
        super(blockNumber, blockHash, timeStamp, hash, nonce, confirmations,
                transactionIndex, from, to, value,
                gas, gasPrice, gasUsed, cumulativeGasUsed, input, contractAddress);
        this.txreceipt_status = txreceipt_status;
        this.isError = isError;
    }

    //<editor-fold desc="Getters">
    public String getTxreceipt_status() {
        return txreceipt_status;
    }

    public boolean isError() {
        return isError;
    }
    //</editor-fold>
}
