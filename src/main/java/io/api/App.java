package io.api;

import io.api.core.EtherScanApi;
import io.api.model.Tx;

import java.util.List;

/**
 *
 */
public class App {
    public static void main(String[] args) {
        EtherScanApi api = new EtherScanApi(args[0]);
        List<Tx> txs = api.account().txs("0x8d4426f94e42f721C7116E81d6688cd935cB3b4F");
        System.out.println("Test");
    }
}
