package io.goodforgod.api.etherscan.model;

import io.goodforgod.api.etherscan.model.proxy.BlockProxy;
import io.goodforgod.api.etherscan.model.proxy.ReceiptProxy;
import io.goodforgod.api.etherscan.model.proxy.TxProxy;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
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

        GasOracle value2 = GasOracle.builder()
                .withFastGasPrice(Wei.ofWei(1000000000))
                .withProposeGasPrice(Wei.ofWei(1000000000))
                .withSafeGasPrice(Wei.ofWei(1000000000))
                .withGasUsedRatio(Collections.singletonList(new BigDecimal(1)))
                .withLastBlock(1L)
                .withSuggestBaseFee(1.0)
                .build();
        assertEquals(value, value2);
        assertEquals(value.hashCode(), value2.hashCode());
        assertEquals(value.toString(), value2.toString());
    }

    @Test
    void logBuilder() {
        LocalDateTime timestamp = LocalDateTime.now();
        Log value = Log.builder()
                .withAddress("1")
                .withBlockNumber(1L)
                .withData("1")
                .withGasPrice(Wei.ofWei(1))
                .withGasUsed(Wei.ofWei(1))
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
                .withFrom("1")
                .withTo("1")
                .withCumulativeGasUsed(Wei.ofWei(BigInteger.ONE))
                .withGas(Wei.ofWei(BigInteger.ONE))
                .withGasPrice(Wei.ofWei(BigInteger.ONE))
                .withGasUsed(Wei.ofWei(BigInteger.ONE))
                .withHash("1")
                .withInput("1")
                .withIsError("1")
                .withNonce(1L)
                .withTimeStamp(timestamp)
                .withValue(BigInteger.ONE)
                .withTransactionIndex(1)
                .withTxReceiptStatus("1")
                .build();

        assertNotNull(value);
        assertTrue(value.haveError());
        assertEquals("1", value.getTo());
        assertEquals("1", value.getFrom());

        Tx value2 = Tx.builder()
                .withBlockHash("1")
                .withBlockNumber(1L)
                .withConfirmations(1L)
                .withContractAddress("1")
                .withFrom("1")
                .withTo("1")
                .withCumulativeGasUsed(Wei.ofWei(BigInteger.ONE))
                .withGas(Wei.ofWei(BigInteger.ONE))
                .withGasPrice(Wei.ofWei(BigInteger.ONE))
                .withGasUsed(Wei.ofWei(BigInteger.ONE))
                .withHash("1")
                .withInput("1")
                .withIsError("1")
                .withNonce(1L)
                .withTimeStamp(timestamp)
                .withValue(BigInteger.ONE)
                .withTransactionIndex(1)
                .withTxReceiptStatus("1")
                .build();

        assertEquals(value, value2);
        assertEquals(value.hashCode(), value2.hashCode());
        assertEquals(value.toString(), value2.toString());
    }

    @Test
    void txErc20Builder() {
        LocalDateTime timestamp = LocalDateTime.now();
        TxErc20 value = TxErc20.builder()
                .withBlockHash("1")
                .withBlockNumber(1L)
                .withConfirmations(1L)
                .withContractAddress("1")
                .withFrom("1")
                .withTo("1")
                .withCumulativeGasUsed(Wei.ofWei(BigInteger.ONE))
                .withGas(Wei.ofWei(BigInteger.ONE))
                .withGasPrice(Wei.ofWei(BigInteger.ONE))
                .withGasUsed(Wei.ofWei(BigInteger.ONE))
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
                .withFrom("1")
                .withTo("1")
                .withCumulativeGasUsed(Wei.ofWei(BigInteger.ONE))
                .withGas(Wei.ofWei(BigInteger.ONE))
                .withGasPrice(Wei.ofWei(BigInteger.ONE))
                .withGasUsed(Wei.ofWei(BigInteger.ONE))
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
                .withFrom("1")
                .withTo("1")
                .withCumulativeGasUsed(Wei.ofWei(BigInteger.ONE))
                .withGas(Wei.ofWei(BigInteger.ONE))
                .withGasPrice(Wei.ofWei(BigInteger.ONE))
                .withGasUsed(Wei.ofWei(BigInteger.ONE))
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

        TxErc1155 value2 = TxErc1155.builder()
                .withBlockHash("1")
                .withBlockNumber(1L)
                .withConfirmations(1L)
                .withContractAddress("1")
                .withFrom("1")
                .withTo("1")
                .withCumulativeGasUsed(Wei.ofWei(BigInteger.ONE))
                .withGas(Wei.ofWei(BigInteger.ONE))
                .withGasPrice(Wei.ofWei(BigInteger.ONE))
                .withGasUsed(Wei.ofWei(BigInteger.ONE))
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

        assertEquals(value, value2);
        assertEquals(value.hashCode(), value2.hashCode());
        assertEquals(value.toString(), value2.toString());
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
                .withGas(Wei.ofWei(BigInteger.ONE))
                .withGasUsed(Wei.ofWei(BigInteger.ONE))
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

        TxInternal value2 = TxInternal.builder()
                .withBlockNumber(1L)
                .withContractAddress("1")
                .withFrom("1")
                .withTo("1")
                .withValue(BigInteger.ONE)
                .withGas(Wei.ofWei(BigInteger.ONE))
                .withGasUsed(Wei.ofWei(BigInteger.ONE))
                .withHash("1")
                .withInput("1")
                .withTimeStamp(timestamp)
                .withErrCode("1")
                .withIsError(1)
                .withTraceId("1")
                .withType("1")
                .build();

        assertEquals(value, value2);
        assertEquals(value.hashCode(), value2.hashCode());
        assertEquals(value.toString(), value2.toString());
    }

    @Test
    void ethSupplyBuilder() {
        EthSupply value = EthSupply.builder()
                .withBurntFees(Wei.ofWei(1))
                .withEth2Staking(Wei.ofWei(1))
                .withEthSupply(Wei.ofWei(1))
                .withWithdrawnTotal(Wei.ofWei(1))
                .build();

        assertNotNull(value);
        assertEquals(BigInteger.valueOf(1), value.getTotal().asWei());

        EthSupply valueEmpty = EthSupply.builder()
                .build();
        assertNotNull(valueEmpty);
        assertEquals(BigInteger.ZERO, valueEmpty.getTotal().asWei());

        EthSupply value2 = EthSupply.builder()
                .withBurntFees(Wei.ofWei(1))
                .withEth2Staking(Wei.ofWei(1))
                .withEthSupply(Wei.ofWei(1))
                .withWithdrawnTotal(Wei.ofWei(1))
                .build();
        assertEquals(value, value2);
        assertEquals(value.hashCode(), value2.hashCode());
        assertEquals(value.toString(), value2.toString());
    }

    @Test
    void receiptProxyBuilder() {
        LocalDateTime timestamp = LocalDateTime.now();
        ReceiptProxy value = ReceiptProxy.builder()
                .withBlockHash("1")
                .withBlockNumber(1L)
                .withContractAddress("1")
                .withCumulativeGasUsed(Wei.ofWei(1))
                .withFrom("1")
                .withTo("1")
                .withGasUsed(Wei.ofWei(1))
                .withRoot("1")
                .withLogsBloom("1")
                .withTransactionHash("1")
                .withTransactionIndex(1L)
                .withLogs(Arrays.asList(Log.builder()
                        .withTopics(Arrays.asList("1"))
                        .withTransactionIndex(1L)
                        .withTransactionHash("1")
                        .withTimeStamp(timestamp)
                        .withLogIndex(1L)
                        .withGasUsed(Wei.ofWei(1))
                        .withGasPrice(Wei.ofWei(1))
                        .withData("1")
                        .withAddress("1")
                        .build()))
                .build();

        assertNotNull(value);
        assertEquals(BigInteger.valueOf(1), value.getGasUsed().asWei());
    }

    @Test
    void blockProxyBuilder() {
        LocalDateTime timestamp = LocalDateTime.now();
        BlockProxy value = BlockProxy.builder()
                .withGasUsed(Wei.ofWei(1))
                .withLogsBloom("1")
                .withDifficulty("1")
                .withExtraData("1")
                .withGasLimit(Wei.ofWei(1))
                .withHash("1")
                .withMiner("1")
                .withMixHash("1")
                .withNonce("1")
                .withNumber(1L)
                .withParentHash("1")
                .withReceiptsRoot("1")
                .withSha3Uncles("1")
                .withSize(1L)
                .withStateRoot("1")
                .withTimestamp(timestamp)
                .withTotalDifficulty("1")
                .withTransactionsRoot("1")
                .withUncles(Arrays.asList("1"))
                .withTransactions(Arrays.asList(TxProxy.builder()
                        .withBlockHash("1")
                        .withBlockNumber(1L)
                        .withFrom("1")
                        .withGas(Wei.ofWei(1))
                        .withGasPrice(Wei.ofWei(1))
                        .withHash("1")
                        .withInput("1")
                        .withNonce(1L)
                        .withR("1")
                        .withS("1")
                        .withTo("1")
                        .withTransactionIndex(1L)
                        .withV("1")
                        .withValue("1")
                        .withV("1")
                        .build()))
                .build();

        assertNotNull(value);
        assertEquals(BigInteger.valueOf(1), value.getGasUsed().asWei());
    }

    @Test
    void gasEstimate() {
        GasEstimate gas1 = new GasEstimate(1);
        GasEstimate gas2 = new GasEstimate(1);
        assertEquals(gas1, gas2);
        assertEquals(gas1.hashCode(), gas2.hashCode());
        assertEquals(gas1.toString(), gas2.toString());
    }
}
