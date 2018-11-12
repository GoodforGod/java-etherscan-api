package io.api.etherscan.proxy;

import io.api.etherscan.core.impl.EtherScanApi;
import org.junit.Assert;
import org.junit.Test;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 13.11.2018
 */
public class ProxyBlockLastNoApiTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correct() {
        long noLast = api.proxy().blockNoLast();
        assertNotEquals(0, noLast);
    }
}
