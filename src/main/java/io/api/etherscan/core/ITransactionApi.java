package io.api.etherscan.core;

import io.api.etherscan.error.ApiException;
import io.api.etherscan.model.Status;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * EtherScan - API Descriptions https://etherscan.io/apis#transactions
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface ITransactionApi {

    /**
     * Check Contract Execution Status (if there was an error during contract
     * execution)
     * 
     * @param txhash transaction hash
     * @return optional status result
     * @throws ApiException parent exception class
     */
    @NotNull
    Optional<Status> execStatus(String txhash) throws ApiException;

    /**
     * Check Transaction Receipt Status (Only applicable for Post Byzantium fork
     * transactions)
     * 
     * @param txhash transaction hash
     * @return 0 = Fail, 1 = Pass, empty value for pre-byzantium fork
     * @throws ApiException parent exception class
     */
    @NotNull
    Optional<Boolean> receiptStatus(String txhash) throws ApiException;
}
