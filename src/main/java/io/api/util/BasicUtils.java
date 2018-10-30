package io.api.util;

import io.api.error.InvalidAddressException;

import java.util.List;
import java.util.regex.Pattern;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class BasicUtils {

    private static final Pattern addressPattern = Pattern.compile("0x[a-zA-Z0-9]{40}");

    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean isBlack(String value) {
        return value == null || value.isEmpty() || value.trim().isEmpty();
    }

    public static boolean isAddress(String value) {
        return !isEmpty(value) && addressPattern.matcher(value).matches();
    }

    public static boolean areAddresses(List<String> strings) {
        return (strings != null) && !strings.isEmpty()
                && strings.stream().noneMatch(BasicUtils::isAddress);
    }

    public static void validateAddress(String address) {
        if(!isAddress(address))
            throw new InvalidAddressException("Address is not Ethereum based.");
    }

    public static void validateAddresses(List<String> addresses) {
        if(addresses == null)
            throw new NullPointerException("Addresses can not be nullable.");

        if(addresses.isEmpty())
            return;

        for (String address : addresses) {
            if (!isAddress(address))
                throw new InvalidAddressException("Address is not Ethereum based.");
        }
    }
}
