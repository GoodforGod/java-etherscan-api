package io.api.etherscan.core.impl;

import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.manager.IQueueManager;
import io.api.etherscan.model.network.PolygonNetwork;

import java.util.function.Supplier;

public class PolygonScanApi extends BaseApi {

    public PolygonScanApi() {
        super(PolygonNetwork.MAINNET);
    }

    public PolygonScanApi(PolygonNetwork network) {
        super(network);
    }

    public PolygonScanApi(String apiKey) {
        super(apiKey, PolygonNetwork.MAINNET);
    }

    public PolygonScanApi(PolygonNetwork network, Supplier<IHttpExecutor> executorSupplier) {
        super(network, executorSupplier);
    }

    public PolygonScanApi(String apiKey, PolygonNetwork network, IQueueManager queue) {
        super(apiKey, network, queue);
    }

    public PolygonScanApi(String apiKey, PolygonNetwork network) {
        super(apiKey, network);
    }

    public PolygonScanApi(String apiKey, PolygonNetwork network, Supplier<IHttpExecutor> executorSupplier) {
        super(apiKey, network, executorSupplier);
    }

    public PolygonScanApi(String apiKey, PolygonNetwork network, Supplier<IHttpExecutor> executorSupplier, IQueueManager queue) {
        super(apiKey, network, executorSupplier, queue);
    }
}
