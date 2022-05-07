package io.api.etherscan.model.query;

import io.api.etherscan.error.InvalidTxHashException;
import io.api.etherscan.model.Address;
import org.junit.Assert;
import org.junit.Test;

public class LogTopicTest extends Assert {

    @Test
    public void fromAddress() {
        Address address = Address.of("0x33990122638b9132ca29c723bdf037f1a891a70c");
        LogTopic logTopic = LogTopic.of(address);

        assertEquals("0x00000000000000000000000033990122638b9132ca29c723bdf037f1a891a70c", logTopic.getHex());
    }

    @Test(expected = InvalidTxHashException.class)
    public void invalid() {
        LogTopic.of("ddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef");
    }

    @Test
    public void valid() {
        LogTopic logTopic = LogTopic.of("0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef");
        assertEquals("0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef", logTopic.getHex());

        LogTopic sameLogTopic = LogTopic.of("0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef");
        assertEquals(logTopic, sameLogTopic);
        assertEquals(logTopic.hashCode(), sameLogTopic.hashCode());
    }
}