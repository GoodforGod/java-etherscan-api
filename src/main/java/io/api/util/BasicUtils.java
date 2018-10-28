package io.api.util;

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
}
