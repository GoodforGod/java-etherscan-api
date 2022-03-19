package io.api.etherscan.core.impl;

import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.manager.IQueueManager;
import io.api.etherscan.model.EthNetwork;

import java.util.function.Supplier;

/**
 * EtherScan full API Description https://etherscan.io/apis
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class EtherScanApi extends BaseApi {

    public EtherScanApi() {
        super();
    }

    public EtherScanApi(EthNetwork network) {
        super(network);
    }

    public EtherScanApi(String apiKey) {
        super(apiKey);
    }

    public EtherScanApi(EthNetwork network, Supplier<IHttpExecutor> executorSupplier) {
        super(network, executorSupplier);
    }

    public EtherScanApi(String apiKey, EthNetwork network, IQueueManager queue) {
        super(apiKey, network, queue);
    }

    public EtherScanApi(String apiKey, EthNetwork network) {
        super(apiKey, network);
    }

    public EtherScanApi(String apiKey, EthNetwork network, Supplier<IHttpExecutor> executorSupplier) {
        super(apiKey, network, executorSupplier);
    }

    public EtherScanApi(String apiKey, EthNetwork network, Supplier<IHttpExecutor> executorSupplier, IQueueManager queue) {
        super(apiKey, network, executorSupplier, queue);
    }
}
