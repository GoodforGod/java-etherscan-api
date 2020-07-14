package io.api.etherscan.model.event.impl;

import io.api.etherscan.model.event.IEvent;

public class WithdrawEvent  extends Event {
    static final String eventTypeHash = "0x7fcf532c15f0a6db0bd6d0e038bea71d30d808c7d98cb3bf7268a95bf5081b65";
    static {
        IEvent.registerEventType(WithdrawEvent.eventTypeHash, WithdrawEvent.class);
    }
}
