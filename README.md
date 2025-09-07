# Java EtherScan API 

[![Minimum required Java version](https://img.shields.io/badge/Java-1.8%2B-blue?logo=openjdk)](https://openjdk.org/projects/jdk8/)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.goodforgod/java-etherscan-api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.goodforgod/java-etherscan-api)
[![Java CI](https://github.com/GoodforGod/java-etherscan-api/workflows/CI%20Master/badge.svg)](https://github.com/GoodforGod/java-etherscan-api/actions?query=workflow%3ACI+Master)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_java-etherscan-api&metric=coverage)](https://sonarcloud.io/dashboard?id=GoodforGod_java-etherscan-api)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_java-etherscan-api&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=GoodforGod_java-etherscan-api)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_java-etherscan-api&metric=ncloc)](https://sonarcloud.io/dashboard?id=GoodforGod_java-etherscan-api)

[Etherscan.io](https://docs.etherscan.io/) Java API implementation.

Library supports EtherScan *API* for all available *Ethereum Networks* for *etherscan.io*

## Dependency :rocket:

**Gradle**
```groovy
implementation "com.github.goodforgod:java-etherscan-api:3.0.0"
```

**Maven**
```xml
<dependency>
    <groupId>com.github.goodforgod</groupId>
    <artifactId>java-etherscan-api</artifactId>
    <version>3.0.0</version>
</dependency>
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

## MainNet and TestNets

API support all Ethereum [default networks](https://docs.etherscan.io/getting-started/endpoint-urls):
- [Mainnet](https://api.etherscan.io/)
- [Goerli](https://api-goerli.etherscan.io/)
- [Sepolia](https://api-sepolia.etherscan.io/)

```java
EtherScanAPI api = EtherScanAPI.builder().build();
EtherScanAPI apiGoerli = EtherScanAPI.builder().withNetwork(EthNetworks.GORLI).build();
EtherScanAPI apiSepolia = EtherScanAPI.builder().withNetwork(EthNetworks.SEPOLIA).build();
```

### Custom Network

In case you want to use API for other EtherScan compatible network, you can easily provide custom network with domain api URI.

```java
EtherScanAPI api = EtherScanAPI.builder()
        .withNetwork(() -> URI.create("https://api-my-custom.etherscan.io/api"))
        .build();
```

## Custom HttpClient

In case you need to set custom timeout, custom headers or better implementation for HttpClient, 
just implement **EthHttpClient** by your self or initialize it with your values.

```java
Supplier<EthHttpClient> ethHttpClientSupplier = () -> new UrlEthHttpClient(Duration.ofMillis(300), Duration.ofMillis(300));
EtherScanAPI api = EtherScanAPI.builder()
    .withHttpClient(supplier)
    .build();
```

Also you can use Java 11+ HttpClient:
```java
Supplier<EthHttpClient> ethHttpClientSupplier = () -> new JdkEthHttpClient();
EtherScanAPI api = EtherScanAPI.builder()
    .withHttpClient(supplier)
    .build();
```

## API Examples

You can read about all API methods on [Etherscan](https://docs.etherscan.io/api-endpoints/accounts)

*Library support all available EtherScan API.*

You can use library *with or without* API key *([Check API request\sec restrictions when used without API key](https://docs.etherscan.io/getting-started/viewing-api-usage-statistics))*.

Library will automatically limit requests up to **1 requests in 5 seconds** when used *without* key and up to **5 requests in 1 seconds** when used with API KEY (free plan).
```java
EtherScanAPI.builder()
        .withApiKey(ApiRunner.API_KEY)
        .build();
```

Below are examples for each API category.

### Account API

**Get Ether Balance for a single Address**
```java
EtherScanAPI api = EtherScanAPI.builder().build();
Balance balance = api.account().balance("0x8d4426f94e42f721C7116E81d6688cd935cB3b4F");
```

### Block API

**Get uncles block for block height**
```java
EtherScanAPI api = EtherScanAPI.builder().build();
Optional<UncleBlock> uncles = api.block().uncles(200000);
```

### Contract API
**Request contract ABI from [verified codes](https://etherscan.io/contractsVerified)**
```java
EtherScanAPI api = EtherScanAPI.builder().build();
Abi abi = api.contract().contractAbi("0xBB9bc244D798123fDe783fCc1C72d3Bb8C189413");
```

### Logs API

**Get event logs for single topic**
```java
EtherScanAPI api = EtherScanAPI.builder().build();
LogQuery query = LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
           .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545")
           .build();
List<Log> logs = api.logs().logs(query);
```

**Get event logs for 3 topics with respectful operations**
```java
EtherScanAPI api = EtherScanAPI.builder().build();
LogQuery query = LogQuery.builder("0x33990122638b9132ca29c723bdf037f1a891a70c")
        .withBlockFrom(379224)
        .withBlockTo(400000)
        .withTopic("0xf63780e752c6a54a94fc52715dbc5518a3b4c3c2833d301a204226548a2a8545",
        "0x72657075746174696f6e00000000000000000000000000000000000000000000",
        "0x72657075746174696f6e00000000000000000000000000000000000000000000")
        .setOpTopic0_1(LogOp.AND)
        .setOpTopic0_2(null)
        .setOpTopic1_2(LogOp.AND)
        .build();
 
List<Log> logs = api.logs().logs(query);
```

### Proxy API

**Get tx details with proxy endpoint**
```java
EtherScanAPI api = EtherScanAPI.builder().build();
Optional<TxProxy> tx = api.proxy().tx("0x1e2910a263.0.08d0616a0beb24c1a491d78771baa54a33e66065e03b1f46bc1");
```

**Get block info with proxy endpoint**
```java
EtherScanAPI api = EtherScanAPI.builder().build();
Optional<BlockProxy> block = api.proxy().block(15215);
```

### Stats API

**Statistic about last price**
```java
EtherScanAPI api = EtherScanAPI.builder().build();
Price price = api.stats().priceLast();
```

### Transaction API

**Request receipt status for tx**
```java
EtherScanAPI api = EtherScanAPI.builder().build();
Optional<Boolean> status = api.txs().receiptStatus("0x513c1ba0bebf66436b5fed86ab668452b7805593c05073eb2d51d3a52f480a76");
```

### Token API

You can read about token API [here](https://docs.etherscan.io/api-endpoints/tokens)

Token API methods migrated to [Account](#account-api) & [Stats](#stats-api) respectfully.

## License

This project licensed under the MIT - see the [LICENSE](LICENSE) file for details.
