package io.api.etherscan.model.query.impl;

import io.api.etherscan.core.ILogsApi;

/**
 * Base parameters for The Event Log API builder
 *
 * @see LogQueryBuilder
 * @see ILogsApi
 *
 * @author GoodforGod
 * @since 31.10.2018
 */
abstract class BaseLogQuery {

    static final String FROM_BLOCK_PARAM = "&fromBlock=";
    static final String TO_BLOCK_PARAM = "&toBlock=";
    static final String ADDRESS_PARAM = "&address=";

    static final String TOPIC_0_PARAM = "&topic0=",
            TOPIC_1_PARAM = "&topic1=",
            TOPIC_2_PARAM = "&topic2=",
            TOPIC_3_PARAM = "&topic3=";

    static final String TOPIC_0_1_OPR_PARAM = "&topic0_1_opr=",
            TOPIC_1_2_OPR_PARAM = "&topic1_2_opr=",
            TOPIC_2_3_OPR_PARAM = "&topic2_3_opr=",
            TOPIC_0_2_OPR_PARAM = "&topic0_2_opr=",
            TOPIC_0_3_OPR_PARAM = "&topic0_3_opr=",
            TOPIC_1_3_OPR_PARAM = "&topic1_3_opr=";
}
