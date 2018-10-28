package io.api.core;

import io.api.core.impl.AccountProvider;
import io.api.core.impl.ContractProvider;
import io.api.model.EthereumNetwork;

import java.util.HashMap;
import java.util.Map;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 28.10.2018
 */
public class EtherScanApi {

    private static final Map<String, String> HEADERS = new HashMap<>();

    static {
        HEADERS.put("accept-language", "en,ru;q=0.9");
        HEADERS.put("accept-encoding", "gzip, deflate, br");
        HEADERS.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) Chrome/68.0.3440.106");
        HEADERS.put("cache-control", "max-age=0");
    }

    private final ContractProvider contract;
    private final AccountProvider account;

    public EtherScanApi(final String apiKey) {
        this(apiKey, EthereumNetwork.MAINNET);
    }

    public EtherScanApi(final String apiKey,
                        final EthereumNetwork network) {
        final EthereumNetwork usedNetwork = (network == null)
                ? EthereumNetwork.MAINNET
                : network;

        final String url = "https://" + usedNetwork.getDomain() + ".etherscan.io/api" + "?apikey=" + apiKey;
        this.contract = new ContractProvider(url, HEADERS);
        this.account = new AccountProvider(url, HEADERS);
    }

    public ContractProvider contract() {
        return contract;
    }

    public AccountProvider account() {
        return account;
    }
}
