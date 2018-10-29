package io.api;

import io.api.core.EtherScanApi;

/**
 *
 */
public class App {
    public static void main(String[] args) {
        EtherScanApi api = new EtherScanApi(args[0]);
        String abi = api.contract().contractAbi("0xBB9bc244D798123fDe783fCc1C72d3Bb8C189413");
        System.out.println(abi);
    }
}
