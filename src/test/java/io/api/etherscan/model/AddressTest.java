package io.api.etherscan.model;

import io.api.etherscan.error.InvalidAddressException;
import org.junit.Assert;
import org.junit.Test;

public class AddressTest extends Assert {

    @Test(expected = InvalidAddressException.class)
    public void invalidAddress() {
        Address.of("033990122638b9132ca29c723bdf037f1a891a70c");
    }

    @Test
    public void validAddress() {
        Address address = Address.of("0x33990122638b9132ca29c723bdf037f1a891a70c");
        assertEquals("0x33990122638b9132ca29c723bdf037f1a891a70c", address.getAddress());

        Address sameAddress = Address.of("0x33990122638b9132ca29c723bdf037f1a891a70c");
        assertEquals(address, sameAddress);
        assertEquals(address.hashCode(), sameAddress.hashCode());
    }

    @Test
    public void to64Hex() {
        Address address = Address.of("0x33990122638b9132ca29c723bdf037f1a891a70c");
        assertEquals("0x00000000000000000000000033990122638b9132ca29c723bdf037f1a891a70c", address.to64Hex());
    }
}