package io.api.core;

import io.api.model.Log;

import java.util.List;

/**
 * EtherScan - API Descriptions
 * https://etherscan.io/apis#logs
 *
 * @author GoodforGod
 * @since 30.10.2018
 */
public interface ILogsProvider {

    List<Log> logs(String address);
    List<Log> logs(String address, long startBlock);
    List<Log> logs(String address, long startBlock, long endBlock);
}
