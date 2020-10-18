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

    static {
        final String apiKey = System.getenv("API_KEY");
        final String keyOrDefault = (apiKey == null || apiKey.isEmpty())
                ? EtherScanApi.DEFAULT_KEY
                : apiKey;

        final QueueManager queue = keyOrDefault.equals(EtherScanApi.DEFAULT_KEY)
                ? QueueManager.DEFAULT_KEY_QUEUE
                : QueueManager.PERSONAL_KEY_QUEUE;

        api = new EtherScanApi(keyOrDefault, EthNetwork.MAINNET, queue);
        apiRopsten = new EtherScanApi(keyOrDefault, EthNetwork.ROPSTEN, queue);
        apiRinkeby = new EtherScanApi(keyOrDefault, EthNetwork.RINKEBY, queue);
        apiKovan = new EtherScanApi(keyOrDefault, EthNetwork.KOVAN, queue);
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
