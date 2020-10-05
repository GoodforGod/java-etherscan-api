# java-etherscan-api

[![travis](https://travis-ci.org/GoodforGod/java-etherscan-api.svg?branch=master)](https://travis-ci.com/iSnow/java-etherscan-api)
[![Maintainability](https://api.codeclimate.com/v1/badges/808997be2e69ff1ae8fe/maintainability)](https://codeclimate.com/github/GoodforGod/java-etherscan-api/maintainability)
[![codecov](https://codecov.io/gh/GoodforGod/java-etherscan-api/branch/master/graph/badge.svg)](https://codecov.io/gh/GoodforGod/java-etherscan-api)
[![Jitpack](https://jitpack.io/v/iSnow/java-etherscan-api.svg)](https://jitpack.io/#iSnow/java-etherscan-api)

[Etherscan](https://etherscan.io/apis) Java API implementation.

Library supports all available EtherScan *API* calls for all available *Ethereum Networks*.

## Dependency :rocket:
**Maven**
```xml
<dependency>
    <groupId>com.github.goodforgod</groupId>
    <artifactId>java-etherscan-api</artifactId>
    <version>1.0.2</version>
</dependency>
```

**Gradle**
```groovy
dependencies {
    compile 'com.github.goodforgod:java-etherscan-api:1.0.2'
}
```

## Content
- [Ethereum Networks](#mainnet-and-testnets)
- [Custom HttpClient](#custom-httpclient)
- [API examples](#api-examples)
    - [Account](#account-api)
    - [Block](#block-api)
    - [Contract](#contract-api)
    - [Logs](#logs-api)
    - [Proxy](#proxy-api)
    - [Stats](#stats-api)
    - [Transactions](#transaction-api)
    - [Token](#token-api)
- [Version History](#version-history)

## Mainnet and Testnets
API support Ethereum: *[MAINNET](https://etherscan.io),
 [ROPSTEN](https://ropsten.etherscan.io), 
 [KOVAN](https://kovan.etherscan.io), 
 [RINKEBY](https://rinkeby.etherscan.io), 
 [GORLI](https://goerli.etherscan.io), 
 [TOBALABA](https://tobalaba.etherscan.com)* networks.
```java
EtherScanApi api = new EtherScanApi(EthNetwork.MAINNET); // Default
EtherScanApi apiRinkeby = new EtherScanApi(EthNetwork.RINKEBY);
EtherScanApi apiRopsten = new EtherScanApi(EthNetwork.ROPSTEN);
EtherScanApi apiKovan = new EtherScanApi("YourApiKey", EthNetwork.KOVAN);
```

## Custom HttpClient

In case you need to set custom timeout, custom headers or better implementation for HttpClient, 
just implement **IHttpExecutor** by your self or initialize it with your values.

```java
int connectionTimeout = 10000;
int readTimeout = 7000;
 
Supplier<IHttpExecutor> supplier = () -> new HttpExecutor(connectionTimeout);
Supplier<IHttpExecutor> supplierFull = () -> new HttpExecutor(connectionTimeout, readTimeout);
 
EtherScanApi api = new EtherScanApi(EthNetwork.RINKEBY, supplier);
EtherScanApi apiWithKey = new EtherScanApi("YourApiKey", EthNetwork.MAINNET, supplierFull);
```

## API Examples

You can read about all API methods on [Etherscan](https://etherscan.io/apis)

*Library support all available EtherScan API.*

You can use library *with or without* API key *([Check API request\sec restrictions when used without API key](https://ethereum.stackexchange.com/questions/34190/does-etherscan-require-the-use-of-an-api-key))*.

Library will automatically limit requests up to **5 req/sec** when used *without* key.
```java
EtherScanApi api = new EtherScanApi();
EtherScanApi api = new EtherScanApi("YourApiKey");
```

Below are examples for each API category.

### Account Api
**Get Ether Balance for a single Address**
```java
EtherScanApi api = new EtherScanApi();
Balance balance = api.account().balance("0x8d4426f94e42f721C7116E81d6688cd935cB3b4F");
```

### Block Api
**Get uncles block for block height**
```java
EtherScanApi api = new EtherScanApi();
Optional<UncleBlock> uncles = api.block().uncles(200000);
```

### Contract Api
**Request contract ABI from [verified codes](https://etherscan.io/contractsVerified)**
```java
EtherScanApi api = new EtherScanApi();
Abi abi = api.contract().contractAbi("0xBB9bc244D798123fDe783fCc1C72d3Bb8C189413");
```

### Logs Api
**Get event logs for single topic**
```java
EtherScanApi api = new EtherScanApi();
LogQuery query = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c")
           .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
           .build();
List<Log> logs = api.logs().logs(query);
```

**Get event logs for 3 topics with respectful operations**
```java
EtherScanApi api = new EtherScanApi();
LogQuery query = LogQueryBuilder.with("0x33990122638b9132ca29c723bdf037f1a891a70c", 379224, 400000)
        .topic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
                "0x72657075746174696f6e00000000000000000000000000000000000000000000",
                "0x72657075746174696f6e00000000000000000000000000000000000000000000")
        .setOpTopic0_1(LogOp.AND)
        .setOpTopic0_2(LogOp.OR)
        .setOpTopic1_2(LogOp.AND)
        .build();
 
List<Log> logs = api.logs().logs(query);
```

### Proxy Api
**Get tx detailds with proxy endpoint**
```java
EtherScanApi api = new EtherScanApi(EthNetwork.MAINNET);
Optional<TxProxy> tx = api.proxy().tx("0x1e2910a262b1008d0616a0beb24c1a491d78771baa54a33e66065e03b1f46bc1");
```

**Get block info with proxy endpoint**
```java
EtherScanApi api = new EtherScanApi(EthNetwork.MAINNET);
Optional<BlockProxy> block = api.proxy().block(15215);
```

### Stats Api
**Statistic about last price**
```java
EtherScanApi api = new EtherScanApi();
Price price = api.stats().lastPrice();
```

### Transaction Api
**Request receipt status for tx**
```java
EtherScanApi api = new EtherScanApi();
Optional<Boolean> status = api.txs().receiptStatus("0x513c1ba0bebf66436b5fed86ab668452b7805593c05073eb2d51d3a52f480a76");
```

### Token Api
You can read about token API [here](https://etherscan.io/apis#tokens)

Token API methods migrated to [Account](#account-api) & [Stats](#stats-api) respectfully.

## Version History

**1.0.2** - Minor http client improvements.

**1.0.1** - Gorli & TOBALABA networks support.

**1.0.0** - Initial project with all API functionality, for all available networks, with tests coverage for all cases.

## License

This project is licensed under the MIT - see the [LICENSE](LICENSE) file for details.
