package io.api.etherscan;

import io.api.ApiRunner;
import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.ApiException;
import io.api.etherscan.error.ApiKeyException;
import io.api.etherscan.error.ApiTimeoutException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.executor.impl.HttpExecutor;
import io.api.etherscan.model.Balance;
import io.api.etherscan.model.EthNetwork;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 05.11.2018
 */
class EtherScanApiTest extends ApiRunner {

    private final EthNetwork network = EthNetwork.KOVAN;
    private final String validKey = "YourKey";

    @Test
    void validKey() {
        EtherScanApi api = new EtherScanApi(validKey, network);
        assertNotNull(api);
    }

    @Test
    void emptyKey() {
        assertThrows(ApiKeyException.class, () -> new EtherScanApi(""));
    }

    @Test
    void blankKey() {
        assertThrows(ApiKeyException.class, () -> new EtherScanApi("         ", network));
    }

    @Test
    void nullNetwork() {
        assertThrows(ApiException.class, () -> new EtherScanApi(validKey, null));
    }

    @Test
    void noTimeoutOnRead() {
        Supplier<IHttpExecutor> supplier = () -> new HttpExecutor(300);
        EtherScanApi api = new EtherScanApi(EthNetwork.MAINNET, supplier);
        Balance balance = api.account().balance("0xF318ABc9A5a92357c4Fea8d082dade4D43e780B7");
        assertNotNull(balance);
    }

    @Test
    void noTimeoutOnReadGroli() {
        Supplier<IHttpExecutor> supplier = () -> new HttpExecutor(300);
        Balance balance = getApi().account().balance("0xF318ABc9A5a92357c4Fea8d082dade4D43e780B7");
        assertNotNull(balance);
    }

    @Test
    void noTimeoutOnReadTobalala() {
        Supplier<IHttpExecutor> supplier = () -> new HttpExecutor(30000);
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
        Supplier<IHttpExecutor> supplier = () -> new HttpExecutor(300, 300);
        EtherScanApi api = new EtherScanApi(getApiKey(), EthNetwork.KOVAN, supplier);
        assertThrows(ApiTimeoutException.class, () -> api.account().minedBlocks("0x0010f94b296A852aAac52EA6c5Ac72e03afD032D"));
    }
}
