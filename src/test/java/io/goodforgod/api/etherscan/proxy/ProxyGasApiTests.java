package io.goodforgod.api.etherscan.proxy;

import io.goodforgod.api.etherscan.ApiRunner;
import io.goodforgod.api.etherscan.error.EtherScanInvalidDataHexException;
import io.goodforgod.api.etherscan.model.Wei;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
class ProxyGasApiTests extends ApiRunner {

    @Test
    void correctPrice() {
        Wei price = getApi().proxy().gasPrice();
        assertNotNull(price);
        assertNotEquals(0, price.asWei().intValue());
    }

    @Test
    void correctEstimated() {
        Wei price = getApi().proxy().gasEstimated();
        assertNotNull(price);
        assertNotEquals(0, price.asWei().intValue());
    }

    @Test
    void correctEstimatedWithData() {
        String dataCustom = "606060405260728060106000396000f360606040526000606060405260728060106000396000f360606040526000";
        Wei price = getApi().proxy().gasEstimated();
        Wei priceCustom = getApi().proxy().gasEstimated(dataCustom);
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
