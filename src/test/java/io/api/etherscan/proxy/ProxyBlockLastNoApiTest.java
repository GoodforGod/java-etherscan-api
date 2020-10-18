package io.api.etherscan.proxy;

import io.api.ApiRunner;
import org.junit.Test;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 13.11.2018
 */
public class ProxyBlockLastNoApiTest extends ApiRunner {

    @Test
    public void correct() {
        long noLast = getApi().proxy().blockNoLast();
        assertNotEquals(0, noLast);
    }
}
