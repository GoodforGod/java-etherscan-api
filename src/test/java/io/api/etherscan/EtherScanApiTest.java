package io.api.etherscan;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.ApiException;
import io.api.etherscan.error.ApiKeyException;
import io.api.etherscan.error.ApiTimeoutException;
import io.api.etherscan.executor.IHttpExecutor;
import io.api.etherscan.executor.impl.HttpExecutor;
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
        String emptyKey = "";
        EtherScanApi api = new EtherScanApi(emptyKey, network);
    }

    @Test(expected = ApiKeyException.class)
    public void blankKey() {
        String blankKey = "         ";
        EtherScanApi api = new EtherScanApi(blankKey, network);
    }

    @Test(expected = ApiException.class)
    public void nullNetwork() {
        EtherScanApi api = new EtherScanApi(validKey, null);
        assertNotNull(api);
    }

    @Test(expected = ApiTimeoutException.class)
    public void timeout() {
        Supplier<IHttpExecutor> supplier = () -> new HttpExecutor(300, 300);
        EtherScanApi api = new EtherScanApi(EthNetwork.KOVAN, supplier);
        List<Block> blocks = api.account().minedBlocks("0x0010f94b296A852aAac52EA6c5Ac72e03afD032D");
        assertNotNull(api);
    }
}
