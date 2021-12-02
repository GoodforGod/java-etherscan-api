package io.api;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.manager.impl.QueueManager;
import io.api.etherscan.model.EthNetwork;
import org.junit.AfterClass;
import org.junit.Assert;

public class ApiRunner extends Assert {

    private static final EtherScanApi api;
    private static final EtherScanApi apiRopsten;
    private static final EtherScanApi apiRinkeby;
    private static final EtherScanApi apiKovan;
    private static final String apiKey;

    static {
        final String key = System.getenv("API_KEY");
        apiKey = (key == null || key.isEmpty())
                ? EtherScanApi.DEFAULT_KEY
                : key;

        final QueueManager queueManager = (EtherScanApi.DEFAULT_KEY.equals(apiKey))
                ? QueueManager.DEFAULT_KEY_QUEUE
                : new QueueManager(1, 1200L, 1200L, 0);

        api = new EtherScanApi(ApiRunner.apiKey, EthNetwork.MAINNET, queueManager);
        apiKovan = new EtherScanApi(ApiRunner.apiKey, EthNetwork.KOVAN, queueManager);
        apiRopsten = new EtherScanApi(ApiRunner.apiKey, EthNetwork.ROPSTEN, queueManager);
        apiRinkeby = new EtherScanApi(ApiRunner.apiKey, EthNetwork.RINKEBY, queueManager);
    }

    public static String getApiKey() {
        return apiKey;
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

    @AfterClass
    public static void cleanup() throws Exception {
        api.close();
        apiRopsten.close();
        apiRinkeby.close();
        apiKovan.close();
    }
}
