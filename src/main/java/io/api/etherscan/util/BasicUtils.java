package io.api.etherscan.util;

import io.api.etherscan.error.EtherScanException;
import io.api.etherscan.error.InvalidAddressException;
import io.api.etherscan.error.InvalidTxHashException;
import io.api.etherscan.model.utility.BaseResponseTO;
import io.api.etherscan.model.utility.BlockParam;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Basic utils for library
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class BasicUtils {

    private static final int MAX_END_BLOCK = 999999999;
    private static final int MIN_START_BLOCK = 0;

    private static final Pattern ADDRESS_PATTERN = Pattern.compile("0x[a-zA-Z0-9]{40}");
    private static final Pattern TXHASH_PATTERN = Pattern.compile("0x[a-zA-Z0-9]{64}");
    private static final Pattern HEX_PATTERN = Pattern.compile("[a-zA-Z0-9]+");

    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean isBlank(String value) {
        return isEmpty(value) || value.trim().isEmpty();
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static BlockParam compensateBlocks(long startBlock, long endBlock) {
        long startCompensated = compensateMinBlock(startBlock);
        long endCompensated = compensateMaxBlock(endBlock);

        final long startFinal = (startCompensated > endCompensated)
                ? endCompensated
                : startCompensated;

        final long endFinal = (startCompensated > endCompensated)
                ? startCompensated
                : endCompensated;

        return new BlockParam(startFinal, endFinal);
    }

    public static long compensateMinBlock(long blockNumber) {
        return (blockNumber < MIN_START_BLOCK)
                ? MIN_START_BLOCK
                : blockNumber;
    }

    public static long compensateMaxBlock(long blockNumber) {
        return (blockNumber > MAX_END_BLOCK)
                ? MAX_END_BLOCK
                : blockNumber;
    }

    public static boolean isNotAddress(String value) {
        return isEmpty(value) || !ADDRESS_PATTERN.matcher(value).matches();
    }

    public static boolean isNotTxHash(String value) {
        return isEmpty(value) || !TXHASH_PATTERN.matcher(value).find();
    }

    public static boolean isNotHex(String value) {
        return isEmpty(value) || !HEX_PATTERN.matcher(value).matches();
    }

    public static BigInteger parseHex(String hex) {
        try {
            if (BasicUtils.isEmpty(hex))
                return BigInteger.valueOf(0);

            final String formatted = (hex.length() > 2 && hex.charAt(0) == '0' && hex.charAt(1) == 'x')
                    ? hex.substring(2, hex.length())
                    : hex;

            return new BigInteger(formatted, 16);
        } catch (NumberFormatException e) {
            return BigInteger.valueOf(-1L);
        }
    }

    public static void validateAddress(String address) {
        if (isNotAddress(address))
            throw new InvalidAddressException("Address [" + address + "] is not Ethereum based.");
    }

    public static void validateTxHash(String txhash) {
        if (isNotTxHash(txhash))
            throw new InvalidTxHashException("TxHash [" + txhash + "] is not Ethereum based.");
    }

    public static <T extends BaseResponseTO> void validateTxResponse(T response) {
        if (response == null)
            throw new EtherScanException("EtherScan responded with null value");

        if (response.getStatus() != 1) {
            if (response.getMessage() == null) {
                throw new EtherScanException("Unexpected Etherscan exception, no information from server about error, code " + response.getStatus());
            } else if (!response.getMessage().startsWith("No tra") && !response.getMessage().startsWith("No rec")) {
                throw new EtherScanException(response.getMessage() + ", with status " + response.getStatus());
            }
        }
    }

    public static void validateAddresses(List<String> addresses) {
        for (String address : addresses) {
            if (isNotAddress(address))
                throw new InvalidAddressException("Address [" + address + "] is not Ethereum based.");
        }
    }

    @NotNull
    public static List<List<String>> partition(List<String> list, int pairSize) {
        if (isEmpty(list))
            return Collections.emptyList();

        final List<List<String>> partitioned = new ArrayList<>();
        final Iterator<String> iterator = list.iterator();
        int counter = 0;

        List<String> temp = new ArrayList<>();
        while (iterator.hasNext()) {
            temp.add(iterator.next());

            if (++counter > pairSize) {
                partitioned.add(temp);
                temp = new ArrayList<>();
                counter = 0;
            }
        }

        if (!temp.isEmpty())
            partitioned.add(temp);

        return partitioned;
    }
}
