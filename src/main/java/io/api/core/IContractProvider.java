package io.api.core;

/**
 * EtherScan - API Descriptions
 * https://etherscan.io/apis#contracts
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public interface IContractProvider {

    /** Get Verified Contract Sources */
    String contractAbi(String address);
}
