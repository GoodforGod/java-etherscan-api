package io.api.core;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public interface IContractProvider {

    /** Get Verified Contract Sources */
    String contractAbi(String address);
}
