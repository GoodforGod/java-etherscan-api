package io.api.etherscan;

import io.api.etherscan.core.impl.EtherScanApi;
import io.api.etherscan.model.*;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public class App {
    public static void main(String[] args) {
        EtherScanApi api = new EtherScanApi();
        Balance balance = api.account().balance("0x8d4426f94e42f721C7116E81d6688cd935cB3b4F");
        Balance balance1 = api.account().balance("0x8d4426f94e42f721C7116E81d6688cd935cB3b4F");
        Balance balance2 = api.account().balance("0x8d4426f94e42f721C7116E81d6688cd935cB3b4F");
        Balance balance3 = api.account().balance("0x8d4426f94e42f721C7116E81d6688cd935cB3b4F");
        Balance balance4 = api.account().balance("0x8d4426f94e42f721C7116E81d6688cd935cB3b4F");
        Balance balance5 = api.account().balance("0x8d4426f94e42f721C7116E81d6688cd935cB3b4F");
        List<Tx> txs = api.account().txs("0x8d4426f94e42f721C7116E81d6688cd935cB3b4F");
        List<TxToken> txTokens = api.account().txsToken("0xf261B3A60Ef40eE0B369B0705c1a2c58B02799DF");
        List<TxInternal> txInternals = api.account().txsInternal("0x2c1ba59d6f58433fb1eaee7d20b26ed83bda51a3");
        Optional<UncleBlock> uncles = api.block().uncles(2165403);
        Optional<UncleBlock> uncleEmpty = api.block().uncles(999965403);
        System.out.println("Test");
    }
}
