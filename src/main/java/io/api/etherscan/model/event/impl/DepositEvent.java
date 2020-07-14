package io.api.etherscan.model.event.impl;

import io.api.etherscan.model.event.IEvent;

public class DepositEvent  extends Event {
    static final String eventTypeHash = "0xe1fffcc4923d04b559f4d29a8bfc6cda04eb5b0d3c460751c2402c5c5cc9109c";
    static {
        IEvent.registerEventType(DepositEvent.eventTypeHash, DepositEvent.class);
    }
}
