package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;

public class ApiRunner extends Assertions {

    private static final String DEFAULT_KEY = "YourApiKeyToken";

    private static final EtherScanAPI api;
    private static final String apiKey;

    static {
        final String key = System.getenv("API_KEY");
        final RequestQueueManager queueManager = RequestQueueManager.DEFAULT;

        apiKey = (key == null || key.isEmpty())
                ? DEFAULT_KEY
                : key;
        api = EtherScanAPI.builder().withApiKey(ApiRunner.apiKey).withNetwork(EthNetworks.MAINNET).withQueue(queueManager)
                .build();
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static EtherScanAPI getApi() {
        return api;
    }

    @AfterAll
    public static void cleanup() throws Exception {
        api.close();
    }
}
