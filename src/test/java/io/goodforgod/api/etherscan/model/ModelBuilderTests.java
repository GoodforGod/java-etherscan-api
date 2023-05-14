package io.goodforgod.api.etherscan.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 14.05.2023
 */
class ModelBuilderTests extends Assertions {

    @Test
    void abiBuilder() {
        Abi value = Abi.builder()
                .withContractAbi("1")
                .withIsVerified(true)
                .build();

        assertNotNull(value);
        assertTrue(value.isVerified());
        assertEquals("1", value.getContractAbi());
    }

    @Test
    void blockBuilder() {
        LocalDateTime timestamp = LocalDateTime.now();
        Block value = Block.builder()
                .withBlockNumber(1)
                .withBlockReward(BigInteger.ONE)
                .withTimeStamp(timestamp)
                .build();

        assertNotNull(value);
        assertEquals(1, value.getBlockNumber());
        assertEquals(BigInteger.ONE, value.getBlockReward());
        assertEquals(timestamp, value.getTimeStamp());
    }

    @Test
    void blockUncleBuilder() {
        LocalDateTime timestamp = LocalDateTime.now();
        BlockUncle value = BlockUncle.builder()
                .withBlockNumber(1)
                .withBlockReward(BigInteger.ONE)
                .withTimeStamp(timestamp)
                .withBlockMiner("1")
                .withUncleInclusionReward("1")
                .withUncles(Collections.singletonList(BlockUncle.Uncle.builder()
                        .withBlockreward(BigInteger.ONE)
                        .withMiner("1")
                        .withUnclePosition(1)
                        .build()))
                .build();

        assertNotNull(value);
        assertEquals(1, value.getBlockNumber());
        assertEquals(BigInteger.ONE, value.getBlockReward());
        assertEquals(timestamp, value.getTimeStamp());
    }

    @Test
    void gasOracleBuilder() {
        GasOracle value = GasOracle.builder()
                .withFastGasPrice(Wei.ofWei(1000000000))
                .withProposeGasPrice(Wei.ofWei(1000000000))
                .withSafeGasPrice(Wei.ofWei(1000000000))
                .withGasUsedRatio(Collections.singletonList(new BigDecimal(1)))
                .withLastBlock(1L)
                .withSuggestBaseFee(1.0)
                .build();

        assertNotNull(value);
        assertEquals(Wei.ofWei(1000000000), value.getFastGasPriceInWei());
    }

    @Test
    void logBuilder() {
        LocalDateTime timestamp = LocalDateTime.now();
        Log value = Log.builder()
                .withAddress("1")
                .withBlockNumber(1L)
                .withData("1")
                .withGasPrice(BigInteger.ONE)
                .withGasUsed(BigInteger.ONE)
                .withLogIndex(1L)
                .withTimeStamp(timestamp)
                .withTransactionHash("1")
                .withTransactionIndex(1L)
                .withTopics(Collections.singletonList("1"))
                .build();

        assertNotNull(value);
        assertEquals(1, value.getTopics().size());
    }

    @Test
    void priceBuilder() {
        LocalDateTime timestamp = LocalDateTime.now();
        Price value = Price.builder()
                .withEthBtc(1.0)
                .withEthUsd(1.0)
                .withEthBtcTimestamp(timestamp)
                .withEthUsdTimestamp(timestamp)
                .build();

        assertNotNull(value);
        assertEquals(1.0, value.inUsd());
        assertEquals(1.0, value.inBtc());
    }

    @Test
    void statusBuilder() {
        Status value = Status.builder()
                .withIsError(1)
                .withErrDescription("1")
                .build();

        assertNotNull(value);
        assertTrue(value.haveError());
        assertEquals("1", value.getErrDescription());
    }

    @Test
    void txBuilder() {
        LocalDateTime timestamp = LocalDateTime.now();
        Tx value = Tx.builder()
                .withBlockHash("1")
                .withBlockNumber(1L)
                .withConfirmations(1L)
                .withContractAddress("1")
                .withCumulativeGasUsed(BigInteger.ONE)
                .withFrom("1")
                .withTo("1")
                .withGas(BigInteger.ONE)
                .withGasPrice(BigInteger.ONE)
                .withGasUsed(BigInteger.ONE)
                .withHash("1")
                .withInput("1")
                .withIsError("1")
                .withNonce(1L)
                .withTimeStamp(timestamp)
                .withValue(BigInteger.ONE)
                .withTransactionIndex(1)
                .withTxreceiptStatus("1")
                .build();

        assertNotNull(value);
        assertTrue(value.haveError());
        assertEquals("1", value.getTo());
        assertEquals("1", value.getFrom());
    }

    @Test
    void txErc20Builder() {
        LocalDateTime timestamp = LocalDateTime.now();
        TxErc20 value = TxErc20.builder()
                .withBlockHash("1")
                .withBlockNumber(1L)
                .withConfirmations(1L)
                .withContractAddress("1")
                .withCumulativeGasUsed(BigInteger.ONE)
                .withFrom("1")
                .withTo("1")
                .withGas(BigInteger.ONE)
                .withGasPrice(BigInteger.ONE)
                .withGasUsed(BigInteger.ONE)
                .withHash("1")
                .withInput("1")
                .withTokenName("1")
                .withTokenSymbol("1")
                .withTokenDecimal("1")
                .withNonce(1L)
                .withTimeStamp(timestamp)
                .withValue(BigInteger.ONE)
                .withTransactionIndex(1)
                .build();

        assertNotNull(value);
        assertEquals("1", value.getTo());
        assertEquals("1", value.getFrom());
    }

    @Test
    void txErc721Builder() {
        LocalDateTime timestamp = LocalDateTime.now();
        TxErc721 value = TxErc721.builder()
                .withBlockHash("1")
                .withBlockNumber(1L)
                .withConfirmations(1L)
                .withContractAddress("1")
                .withCumulativeGasUsed(BigInteger.ONE)
                .withFrom("1")
                .withTo("1")
                .withGas(BigInteger.ONE)
                .withGasPrice(BigInteger.ONE)
                .withGasUsed(BigInteger.ONE)
                .withHash("1")
                .withInput("1")
                .withTokenName("1")
                .withTokenSymbol("1")
                .withTokenDecimal("1")
                .withTokenID("1")
                .withNonce(1L)
                .withTimeStamp(timestamp)
                .withTransactionIndex(1)
                .build();

        assertNotNull(value);
        assertEquals("1", value.getTo());
        assertEquals("1", value.getFrom());
    }

    @Test
    void txErc1155Builder() {
        LocalDateTime timestamp = LocalDateTime.now();
        TxErc1155 value = TxErc1155.builder()
                .withBlockHash("1")
                .withBlockNumber(1L)
                .withConfirmations(1L)
                .withContractAddress("1")
                .withCumulativeGasUsed(BigInteger.ONE)
                .withFrom("1")
                .withTo("1")
                .withGas(BigInteger.ONE)
                .withGasPrice(BigInteger.ONE)
                .withGasUsed(BigInteger.ONE)
                .withHash("1")
                .withInput("1")
                .withTokenName("1")
                .withTokenSymbol("1")
                .withTokenDecimal("1")
                .withTokenID("1")
                .withNonce(1L)
                .withTimeStamp(timestamp)
                .withTransactionIndex(1)
                .build();

        assertNotNull(value);
        assertEquals("1", value.getTo());
        assertEquals("1", value.getFrom());
    }

    @Test
    void txInternalBuilder() {
        LocalDateTime timestamp = LocalDateTime.now();
        TxInternal value = TxInternal.builder()
                .withBlockNumber(1L)
                .withContractAddress("1")
                .withFrom("1")
                .withTo("1")
                .withValue(BigInteger.ONE)
                .withGas(BigInteger.ONE)
                .withGasUsed(BigInteger.ONE)
                .withHash("1")
                .withInput("1")
                .withTimeStamp(timestamp)
                .withErrCode("1")
                .withIsError(1)
                .withTraceId("1")
                .withType("1")
                .build();

        assertNotNull(value);
        assertEquals("1", value.getTo());
        assertEquals("1", value.getFrom());
    }
}
