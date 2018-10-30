package io.api.util;

import io.api.error.EtherScanException;
import io.api.error.InvalidAddressException;
import io.api.model.temporary.BaseResponseTO;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Pattern;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class BasicUtils {

    private static final Pattern addressPattern = Pattern.compile("0x[a-zA-Z0-9]{40}");
    private static final Pattern txhashPattern = Pattern.compile("0x[a-zA-Z0-9]{64}");

    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean isBlack(String value) {
        return value == null || value.isEmpty() || value.trim().isEmpty();
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isNotAddress(String value) {
        return isEmpty(value) || !addressPattern.matcher(value).matches();
    }

    public static boolean isNotTxHash(String value) {
        return isEmpty(value) || !txhashPattern.matcher(value).matches();
    }

    public static void validateAddress(String address) {
        if(isNotAddress(address))
            throw new InvalidAddressException("Address [" + address + "] is not Ethereum based.");
    }

    public static void validateTxHash(String txhash) {
        if(isNotTxHash(txhash))
            throw new InvalidAddressException("TxHash [" + txhash + "] is not Ethereum based.");
    }

    public static <T extends BaseResponseTO> void validateTxResponse(T response) {
        if(response.getStatus() != 1
                && !response.getMessage().startsWith("No tra")
                && !response.getMessage().startsWith("No rec"))
            throw new EtherScanException(response.getMessage() + ", with status " + response.getStatus());
    }

    public static void validateAddresses(List<String> addresses) {
        for (String address : addresses) {
            if (isNotAddress(address))
                throw new InvalidAddressException("Address [" + address + "] is not Ethereum based.");
        }
    }

    @NotNull
    public static List<List<String>> partition(List<String> list, int pairSize) {
        if(isEmpty(list))
            return Collections.emptyList();

        final List<List<String>> partitioned = new ArrayList<>();
        final Iterator<String> iterator = list.iterator();
        int counter = 0;

        List<String> temp = new ArrayList<>();
        while (iterator.hasNext()) {
            temp.add(iterator.next());

            if(++counter > pairSize) {
                partitioned.add(temp);
                temp = new ArrayList<>();
                counter = 0;
            }
        }

        return partitioned;
    }
}
