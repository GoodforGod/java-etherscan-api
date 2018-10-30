package io.api.core;

import java.math.BigInteger;

/**
 * EtherScan - API Descriptions
 * https://etherscan.io/apis#tokens
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface ITokenProvider {

    /** ERC20 Total Supply */
    BigInteger supply(String contract);

    /** ERC20 token balance for address */
    BigInteger balance(String address, String contract);
}
