package io.api.etherscan;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.model.Balance;

/**
 *
 */
public class App {
    public static void main(String[] args) {
        EtherScanApi api = new EtherScanApi();
        Balance balance = api.account().balance("0x8d4426f94e42f721C7116E81d6688cd935cB3b4F");
        System.out.println("Test");
    }
}
