package io.api;

import io.api.core.EtherScanApi;
import io.api.model.Tx;
import io.api.model.TxInternal;
import io.api.model.TxToken;

import java.util.List;

/**
 *
 */
public class App {
    public static void main(String[] args) {
        EtherScanApi api = new EtherScanApi(args[0]);
        List<Tx> txs = api.account().txs("0x8d4426f94e42f721C7116E81d6688cd935cB3b4F");
        List<TxInternal> txInternals = api.account().txsInternal("0x2c1ba59d6f58433fb1eaee7d20b26ed83bda51a3");
        List<TxToken> txTokens = api.account().txsToken("0xf261B3A60Ef40eE0B369B0705c1a2c58B02799DF");
        System.out.println("Test");
    }
}
