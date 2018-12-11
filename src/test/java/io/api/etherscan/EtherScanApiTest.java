package io.api.etherscan;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.ApiException;
import io.api.etherscan.error.ApiKeyException;
import io.api.etherscan.error.ApiTimeoutException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.executor.impl.HttpExecutor;
import io.api.etherscan.model.Balance;
import io.api.etherscan.model.Block;
import io.api.etherscan.model.EthNetwork;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.function.Supplier;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 05.11.2018
 */
public class EtherScanApiTest extends Assert {

    private EthNetwork network = EthNetwork.KOVAN;
    private String validKey = "YourKey";

    @Test
    public void validKey() {
        EtherScanApi api = new EtherScanApi(validKey, network);
        assertNotNull(api);
    }

    @Test(expected = ApiKeyException.class)
    public void emptyKey() {
        new EtherScanApi("");
    }

    @Test(expected = ApiKeyException.class)
    public void blankKey() {
        new EtherScanApi("         ", network);
    }

    @Test(expected = ApiException.class)
    public void nullNetwork() {
        EtherScanApi api = new EtherScanApi(validKey, null);
        assertNotNull(api);
    }

    @Test
    public void noTimeoutOnRead() {
        Supplier<IHttpExecutor> supplier = () -> new HttpExecutor(300);
        EtherScanApi api = new EtherScanApi(EthNetwork.MAINNET, supplier);
        Balance balance = api.account().balance("0xF318ABc9A5a92357c4Fea8d082dade4D43e780B7");
        assertNotNull(balance);
    }

    @Test
    public void noTimeoutUnlimitedAwait() {
        Supplier<IHttpExecutor> supplier = () -> new HttpExecutor(-30, -300);
        EtherScanApi api = new EtherScanApi(EthNetwork.MAINNET, supplier);
        Balance balance = api.account().balance("0xF318ABc9A5a92357c4Fea8d082dade4D43e780B7");
        assertNotNull(balance);
    }

    @Test(expected = ApiTimeoutException.class)
    public void timeout() {
        Supplier<IHttpExecutor> supplier = () -> new HttpExecutor(300, 300);
        EtherScanApi api = new EtherScanApi(EthNetwork.KOVAN, supplier);
        List<Block> blocks = api.account().minedBlocks("0x0010f94b296A852aAac52EA6c5Ac72e03afD032D");
        assertNotNull(blocks);
    }
}
