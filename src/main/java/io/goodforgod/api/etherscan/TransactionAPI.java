package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanException;
import io.goodforgod.api.etherscan.model.Status;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * EtherScan - API Descriptions <a href="https://docs.etherscan.io/api-endpoints/stats">...</a>
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface TransactionAPI {

    /**
     * Check Contract Execution Status (if there was an error during contract execution)
     * 
     * @param txhash transaction hash
     * @return optional status result
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Optional<Status> statusExec(String txhash) throws EtherScanException;

    /**
     * Check Transaction Receipt Status (Only applicable for Post Byzantium fork transactions)
     * 
     * @param txhash transaction hash
     * @return 0 = Fail, 1 = Pass, empty value for pre-byzantium fork
     * @throws EtherScanException parent exception class
     */
    @NotNull
    Optional<Boolean> statusReceipt(String txhash) throws EtherScanException;
}
