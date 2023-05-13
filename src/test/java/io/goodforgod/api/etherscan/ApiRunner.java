package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import io.goodforgod.api.etherscan.manager.impl.SemaphoreRequestQueueManager;
import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;

public class ApiRunner extends Assertions {

    private static final String DEFAULT_KEY = "YourApiKeyToken";

    private static final EtherScanAPI api;
    private static final EtherScanAPI apiRopsten;
    private static final EtherScanAPI apiRinkeby;
    private static final EtherScanAPI apiKovan;
    private static final String apiKey;

    static {
        final String key = System.getenv("API_KEY");
        apiKey = (key == null || key.isEmpty())
                ? DEFAULT_KEY
                : key;

        final RequestQueueManager queueManager = (DEFAULT_KEY.equals(apiKey))
                ? RequestQueueManager.DEFAULT
                : new SemaphoreRequestQueueManager(1, Duration.ofMillis(1200L), Duration.ofMillis(1200L), 0);

        api = EtherScanAPI.builder().withApiKey(ApiRunner.apiKey).withNetwork(EthNetworks.MAINNET).withQueue(queueManager)
                .build();
        apiKovan = EtherScanAPI.builder().withApiKey(ApiRunner.apiKey).withNetwork(EthNetworks.KOVAN).withQueue(queueManager)
                .build();
        apiRopsten = EtherScanAPI.builder().withApiKey(ApiRunner.apiKey).withNetwork(EthNetworks.ROPSTEN).withQueue(queueManager)
                .build();
        apiRinkeby = EtherScanAPI.builder().withApiKey(ApiRunner.apiKey).withNetwork(EthNetworks.RINKEBY).withQueue(queueManager)
                .build();
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static EtherScanAPI getApi() {
        return api;
    }

    public static EtherScanAPI getApiRopsten() {
        return apiRopsten;
    }

    public static EtherScanAPI getApiRinkeby() {
        return apiRinkeby;
    }

    public static EtherScanAPI getApiKovan() {
        return apiKovan;
    }

    @AfterAll
    public static void cleanup() throws Exception {
        api.close();
        apiRopsten.close();
        apiRinkeby.close();
        apiKovan.close();
    }
}
