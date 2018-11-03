package io.api.etherscan.proxy;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.error.InvalidDataHexException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class ProxyGasApiTest extends Assert {

    private final EtherScanApi api = new EtherScanApi();

    @Test
    public void correctPrice() {
        BigInteger price = api.proxy().gasPrice();
        assertNotNull(price);
        assertNotEquals(0, price.intValue());
    }

    @Test
    public void correctEstimated() {
        BigInteger price = api.proxy().gasEstimated();
        assertNotNull(price);
        assertNotEquals(0, price.intValue());
    }

    @Test
    public void correctEstimatedWithData() {
        String dataCustom = "606060405260728060106000396000f360606040526000606060405260728060106000396000f360606040526000";
        BigInteger price = api.proxy().gasEstimated();
        BigInteger priceCustom = api.proxy().gasEstimated(dataCustom);
        assertNotNull(price);
        assertNotNull(priceCustom);
        assertNotEquals(price, priceCustom);
    }

    @Test(expected = InvalidDataHexException.class)
    public void invalidParamWithError() {
        String dataCustom = "280&60106000396000f360606040526000";
        BigInteger priceCustom = api.proxy().gasEstimated(dataCustom);
    }
}
