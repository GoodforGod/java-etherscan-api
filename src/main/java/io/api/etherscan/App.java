package io.api.etherscan;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.executor.impl.HttpExecutor;
import io.api.etherscan.model.EthNetwork;

import java.util.function.Supplier;

public class App {
    public static void main(String[] args) {
        int connectionTimeout = 10000;
        int readTimeout = 7000;
        Supplier<IHttpExecutor> supplier = () -> new HttpExecutor(connectionTimeout);

        EtherScanApi api = new EtherScanApi(EthNetwork.RINKEBY, supplier);
        EtherScanApi apiWithKey = new EtherScanApi("YourApiKey", EthNetwork.MAINNET, supplier);
    }
}
