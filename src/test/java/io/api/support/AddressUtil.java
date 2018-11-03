package io.api.support;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 03.11.2018
 */
public class AddressUtil {

    public static List<String> genFakeAddresses(int size) {
        final List<String> addresses = new ArrayList<>();
        for (int i = 0; i < size; i++)
            addresses.add("0x9327cb34984c" + ThreadLocalRandom.current().nextInt(1000, 9999) + "ec1EA0eAE98Ccf80A74f95B9");

        return addresses;
    }

    public static List<String> genRealAddresses() {
        final List<String> addresses = new ArrayList<>();

        addresses.add("0x00e01a648ff41346cdeb873182383333d2184dd1");
        addresses.add("0x6b92ab6d455d06b022bf5003922cdd8b07172586");
        addresses.add("0x69c46c1e40465ae9a6c90990ae1568005fe62d6e");
        addresses.add("0x199eee5b6ca1aaf030c77f0b5c50e39908fd2072");
        addresses.add("0x9327cb34984c3992ec1ea0eae98ccf80a74f95b9");
        addresses.add("0xc9f32ce1127e44c51cbd182d6364f3d707fd0d47");
        addresses.add("0x122c7f492c51c247e293b0f996fa63de61474959");
        addresses.add("0xdb82af76f9ccddfe9e8f7996492f4c5bd5f9d53d");
        addresses.add("0xfbb62df5cbc8d0bc4c2a0e5c53b8602d49471495");
        addresses.add("0xa7b8edb0583b478e2b7c431feb4bc6629a6cd1e5");
        addresses.add("0xa8afa9a714e06b9eb1b500617494ac213f2d428a");
        addresses.add("0x4985a8D068A9CAC4B81FaaAFB8D4516AE46dD768");
        addresses.add("0x581ffAadEEB6d183A73d93413e137CB8c6057603");
        addresses.add("0x66a6892477F9B48Ed47f407B30ddE754405E1910");
        addresses.add("0xD4718F4601Af5365360896F763bb9D7173257450");
        addresses.add("0x05fBf1E3f105df6a4553f3C7f2ed93070A4BAB46");
        addresses.add("0x36ec53a8fba6358d59b3c4476d82cc60a2b0fad7");
        addresses.add("0x7a16a0fd441a7591f2456c3cda068e33c6bbaa96");
        addresses.add("0xe22D388301E7D57e20F59E03c14CA4c026C6C3d9");
        addresses.add("0x7f55B3b6774b9573aE2c36eA790118CeBa6C057F");
        addresses.add("0x2965deab20b16565d5fb08799728c51b6cdda34b");
        addresses.add("0xad2e834840954231b924b51bfb92c7f457ca30d9");
        addresses.add("0x5fE584cB2d2A937C4AbBD0cad57841cc8Db97Df1");
        addresses.add("0x536de519a2ab6fd0cc8055e5119b3c2a8dd2464b");
        addresses.add("0xe546f64ebf75cdc134e2e2a05fd6ed2fbfbf7c8d");
        addresses.add("0x9b398c9d1b4516b9c3ccbc4c6b6350702ed58c4d");

        return addresses;
    }
}
