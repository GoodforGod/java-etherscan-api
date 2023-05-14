package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.manager.RequestQueueManager;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;

public class ApiRunner extends Assertions {

    private static final String DEFAULT_KEY = "YourApiKeyToken";

    private static final String API_KEY;
    private static final EtherScanAPI API;

    static {
        API_KEY = System.getenv().entrySet().stream()
                .filter(e -> e.getKey().startsWith("ETHERSCAN_API_KEY"))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(DEFAULT_KEY);

        final RequestQueueManager queueManager = (DEFAULT_KEY.equals(API_KEY))
                ? RequestQueueManager.DEFAULT
                : RequestQueueManager.PERSONAL;

        API = EtherScanAPI.builder()
                .withApiKey(ApiRunner.API_KEY)
                .withNetwork(EthNetworks.MAINNET)
                .withQueue(queueManager)
                .build();
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static EtherScanAPI getApi() {
        return API;
    }

    @AfterAll
    public static void cleanup() throws Exception {
        API.close();
    }
}
