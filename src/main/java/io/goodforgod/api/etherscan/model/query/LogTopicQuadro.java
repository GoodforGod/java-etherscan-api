package io.goodforgod.api.etherscan.model.query;

import static io.goodforgod.api.etherscan.model.query.LogQueryParams.*;

import io.goodforgod.api.etherscan.LogsAPI;
import io.goodforgod.api.etherscan.error.EtherScanLogQueryException;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Quadro topic parameter builder for The Event Log API
 *
 * @see LogQueryBuilderImpl
 * @see LogsAPI
 * @author GoodforGod
 * @since 31.10.2018
 */
public class LogTopicQuadro implements LogTopicBuilder {

    private final String address;
    private final long startBlock, endBlock;
    private final String topic0, topic1, topic2, topic3;

    private LogOp topic0_1_opr, topic1_2_opr, topic2_3_opr, topic0_2_opr, topic0_3_opr, topic1_3_opr;

    LogTopicQuadro(String address,
                   long startBlock,
                   long endBlock,
                   String topic0,
                   String topic1,
                   String topic2,
                   String topic3) {
        this.address = address;
        this.startBlock = startBlock;
        this.endBlock = endBlock;
        this.topic0 = topic0;
        this.topic1 = topic1;
        this.topic2 = topic2;
        this.topic3 = topic3;
    }

    public LogTopicQuadro setOpTopic0_1(LogOp topic0_1_opr) {
        this.topic0_1_opr = topic0_1_opr;
        return this;
    }

    public LogTopicQuadro setOpTopic1_2(LogOp topic1_2_opr) {
        this.topic1_2_opr = topic1_2_opr;
        return this;
    }

    public LogTopicQuadro setOpTopic2_3(LogOp topic2_3_opr) {
        this.topic2_3_opr = topic2_3_opr;
        return this;
    }

    public LogTopicQuadro setOpTopic0_2(LogOp topic0_2_opr) {
        this.topic0_2_opr = topic0_2_opr;
        return this;
    }

    public LogTopicQuadro setOpTopic0_3(LogOp topic0_3_opr) {
        this.topic0_3_opr = topic0_3_opr;
        return this;
    }

    public LogTopicQuadro setOpTopic1_3(LogOp topic1_3_opr) {
        this.topic1_3_opr = topic1_3_opr;
        return this;
    }

    @Override
    public @NotNull LogQuery build() {
        if (topic0_1_opr == null)
            throw new EtherScanLogQueryException("topic0_1_opr can not be null.");
        if (topic0_2_opr == null)
            throw new EtherScanLogQueryException("topic0_2_opr can not be null.");
        if (topic0_3_opr == null)
            throw new EtherScanLogQueryException("topic0_3_opr can not be null.");
        if (topic1_2_opr == null)
            throw new EtherScanLogQueryException("topic1_2_opr can not be null.");
        if (topic2_3_opr == null)
            throw new EtherScanLogQueryException("topic2_3_opr can not be null.");
        if (topic1_3_opr == null)
            throw new EtherScanLogQueryException("topic1_3_opr can not be null.");

        return new LogQueryImpl(ADDRESS_PARAM + address
                + FROM_BLOCK_PARAM + startBlock + TO_BLOCK_PARAM + endBlock
                + TOPIC_0_PARAM + topic0
                + TOPIC_1_PARAM + topic1
                + TOPIC_2_PARAM + topic2
                + TOPIC_3_PARAM + topic3
                + TOPIC_0_1_OPR_PARAM + topic0_1_opr.getOperation()
                + TOPIC_0_2_OPR_PARAM + topic0_2_opr.getOperation()
                + TOPIC_0_3_OPR_PARAM + topic0_2_opr.getOperation()
                + TOPIC_1_2_OPR_PARAM + topic0_2_opr.getOperation()
                + TOPIC_1_3_OPR_PARAM + topic1_2_opr.getOperation()
                + TOPIC_2_3_OPR_PARAM + topic0_2_opr.getOperation());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LogTopicQuadro that = (LogTopicQuadro) o;
        return startBlock == that.startBlock && endBlock == that.endBlock && Objects.equals(address, that.address)
                && Objects.equals(topic0, that.topic0) && Objects.equals(topic1, that.topic1)
                && Objects.equals(topic2, that.topic2) && Objects.equals(topic3, that.topic3) && topic0_1_opr == that.topic0_1_opr
                && topic1_2_opr == that.topic1_2_opr && topic2_3_opr == that.topic2_3_opr && topic0_2_opr == that.topic0_2_opr
                && topic0_3_opr == that.topic0_3_opr && topic1_3_opr == that.topic1_3_opr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, startBlock, endBlock, topic0, topic1, topic2, topic3, topic0_1_opr, topic1_2_opr,
                topic2_3_opr, topic0_2_opr, topic0_3_opr, topic1_3_opr);
    }

    @Override
    public String toString() {
        return "LogTopicQuadro{" +
                "address=" + address + '\'' +
                ", startBlock=" + startBlock +
                ", endBlock=" + endBlock +
                ", topic0=" + topic0 + '\'' +
                ", topic1=" + topic1 + '\'' +
                ", topic2=" + topic2 + '\'' +
                ", topic3=" + topic3 + '\'' +
                ", topic0_1_opr=" + topic0_1_opr +
                ", topic1_2_opr=" + topic1_2_opr +
                ", topic2_3_opr=" + topic2_3_opr +
                ", topic0_2_opr=" + topic0_2_opr +
                ", topic0_3_opr=" + topic0_3_opr +
                ", topic1_3_opr=" + topic1_3_opr +
                '}';
    }
}
