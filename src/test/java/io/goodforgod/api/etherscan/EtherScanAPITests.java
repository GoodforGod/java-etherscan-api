package io.goodforgod.api.etherscan;

import io.goodforgod.api.etherscan.error.EtherScanConnectionException;
import io.goodforgod.api.etherscan.error.EtherScanKeyException;
import io.goodforgod.api.etherscan.http.EthHttpClient;
import io.goodforgod.api.etherscan.http.impl.UrlEthHttpClient;
import io.goodforgod.api.etherscan.model.Balance;
import java.net.URI;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 05.11.2018
 */
class EtherScanAPITests extends ApiRunner {

    private final EthNetworks network = EthNetworks.SEPOLIA;

    @Test
    void validKey() {
        String validKey = "YourKey";
        EtherScanAPI api = EtherScanAPI.builder(validKey).withApiKey(validKey).withNetwork(network).build();
        assertNotNull(api);
    }

    @Test
    void emptyKey() {
        assertThrows(EtherScanKeyException.class, () -> EtherScanAPI.builder("someKey").withApiKey("").build());
    }

    @Test
    void blankKey() {
        assertThrows(EtherScanKeyException.class,
                () -> EtherScanAPI.builder("someKey").withApiKey("         ").withNetwork(network).build());
    }

    @Test
    void noTimeoutOnRead() {
        Supplier<EthHttpClient> supplier = () -> new UrlEthHttpClient(Duration.ofMillis(300));
        EtherScanAPI api = EtherScanAPI.builder(ApiRunner.getKey()).withNetwork(EthNetworks.MAINNET).withHttpClient(supplier)
                .build();
        Balance balance = api.account().balance("0xF318ABc9A5a92357c4Fea8d082dade4D43e780B7");
        assertNotNull(balance);
    }

    @Test
    void noTimeoutOnReadGroli() {
        Balance balance = getApi().account().balance("0xF318ABc9A5a92357c4Fea8d082dade4D43e780B7");
        assertNotNull(balance);
    }

    @Test
    void noTimeoutOnReadTobalala() {
        Balance balance = getApi().account().balance("0xF318ABc9A5a92357c4Fea8d082dade4D43e780B7");
        assertNotNull(balance);
    }

    @Test
    void noTimeoutUnlimitedAwait() {
        Balance balance = getApi().account().balance("0xF318ABc9A5a92357c4Fea8d082dade4D43e780B7");
        assertNotNull(balance);
    }

    @Test
    void timeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Supplier<EthHttpClient> supplier = () -> new UrlEthHttpClient(Duration.ofMillis(300), Duration.ofMillis(300));
        EtherScanAPI api = EtherScanAPI.builder(ApiRunner.getKey())
                .withNetwork(() -> URI.create("https://api-unknown.etherscan.io/api"))
                .withHttpClient(supplier)
                .build();

        assertThrows(EtherScanConnectionException.class,
                () -> api.account().blocksMined("0x0010f94b296A852aAac52EA6c5Ac72e03afD032D"));
    }
}
