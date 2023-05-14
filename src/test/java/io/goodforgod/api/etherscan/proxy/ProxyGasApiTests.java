package io.goodforgod.api.etherscan.proxy;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.error.EtherScanInvalidDataHexException;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class ProxyGasApiTests extends ApiRunner {

    @Test
    void correctPrice() {
        BigInteger price = getApi().proxy().gasPrice();
        assertNotNull(price);
        assertNotEquals(0, price.intValue());
    }

    @Test
    void correctEstimated() {
        BigInteger price = getApi().proxy().gasEstimated();
        assertNotNull(price);
        assertNotEquals(0, price.intValue());
    }

    @Test
    void correctEstimatedWithData() {
        String dataCustom = "606060405260728060106000396000f360606040526000606060405260728060106000396000f360606040526000";
        BigInteger price = getApi().proxy().gasEstimated();
        BigInteger priceCustom = getApi().proxy().gasEstimated(dataCustom);
        assertNotNull(price);
        assertNotNull(priceCustom);
        assertNotEquals(price, priceCustom);
    }

    @Test
    void invalidParamWithError() {
        String dataCustom = "280&60106000396000f360606040526000";
        assertThrows(EtherScanInvalidDataHexException.class, () -> getApi().proxy().gasEstimated(dataCustom));
    }
}
