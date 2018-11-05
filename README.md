# java-etherscan-api

[Etherscan](https://etherscan.io/apis) Java API implementation.


![](https://media.giphy.com/media/1msHfmVdtuwkXww4ZC/giphy.gif)

## Dependency :rocket:
**Maven**
```xml
<dependency>
    <groupId>com.github.goodforgod</groupId>
    <artifactId>java-etherscan-api</artifactId>
    <version>1.0.0</version>
</dependency>
```

**Gradle**
```groovy
dependencies {
    compile 'com.github.goodforgod:java-etherscan-api:1.0.0'
}
```

## Content
- [Overall](#overall)
- [API examples](#api-examples)
    - [Account](#account-api)
    - [Block](#block-api)
    - [Contract](#contract-api)
    - [Logs](#logs-api)
    - [Proxy](#proxy-api)
    - [Stats](#stats-api)
    - [Transactions](#transaction-api)
- [Version History](#version-history)

## Overall

How all is linked together:

## Api Examples

You can read about all API methods on [Etherscan](https://etherscan.io/apis)

You can you API with you key or without key as well.
```java
EtherScanApi api = new EtherScanApi();
EtherScanApi api = new EtherScanApi("YourApiKey");
```

### Mainnet and Testnets
API support: *MAINNET, ROPSTEN, KOVAN, RINKEBY* networks.
```java
EtherScanApi api = new EtherScanApi(EthNetwork.MAINNET);
EtherScanApi api = new EtherScanApi("YourApiKey", EthNetwork.KOVAN);
```

### Account Api
**Get Ether Balance for a single Address Example**
```java
EtherScanApi api = new EtherScanApi();
Balance balance = api.account().balance("0x8d4426f94e42f721C7116E81d6688cd935cB3b4F");
```

### Block Api

### Contract Api

### Logs Api

### Proxy Api

### Stats Api

### Transaction Api


### Token Api
You can read account API [here](https://etherscan.io/apis#accounts)

Token API migrated to account & stats respectfully.

## Version History

**1.0.0** - Initial project with all API functionality.

## License

This project is licensed under the MIT - see the [LICENSE](LICENSE) file for details.
