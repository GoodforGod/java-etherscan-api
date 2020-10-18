package io.api.etherscan.model.event.impl;

import io.api.etherscan.model.event.IEvent;

public class TransferErc20Event extends Event {

    static final String eventTypeHash = "0xddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef";
    static {
        IEvent.registerEventType(TransferErc20Event.eventTypeHash, TransferErc20Event.class);
    }

    String fromAddress;

    String toAddress;

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

}
