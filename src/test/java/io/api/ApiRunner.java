package io.api;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.manager.impl.QueueManager;
import io.api.etherscan.model.EthNetwork;
import org.junit.Assert;

public class ApiRunner extends Assert {

    private static final EtherScanApi api;
    private static final EtherScanApi apiRopsten;
    private static final EtherScanApi apiRinkeby;
    private static final EtherScanApi apiKovan;
    private static final String key;

    static {
        final String apiKey = System.getenv("API_KEY");
        key = (apiKey == null || apiKey.isEmpty())
                ? EtherScanApi.DEFAULT_KEY
                : apiKey;

        final QueueManager queueManager = new QueueManager(2, 2100L, 2100L, 0);
        api = new EtherScanApi(key, EthNetwork.MAINNET, queueManager);
        apiKovan = new EtherScanApi(key, EthNetwork.KOVAN, queueManager);
        apiRopsten = new EtherScanApi(key, EthNetwork.ROPSTEN, queueManager);
        apiRinkeby = new EtherScanApi(key, EthNetwork.RINKEBY, queueManager);
    }

    public static String getKey() {
        return key;
    }

    public static EtherScanApi getApi() {
        return api;
    }

    public static EtherScanApi getApiRopsten() {
        return apiRopsten;
    }

    public static EtherScanApi getApiRinkeby() {
        return apiRinkeby;
    }

    public static EtherScanApi getApiKovan() {
        return apiKovan;
    }
}
