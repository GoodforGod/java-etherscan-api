package io.api.etherscan.model.event.impl;

import io.api.etherscan.model.event.IEvent;

public class MintEvent  extends Event {
    static final String eventTypeHash = "0x4c209b5fc8ad50758f13e2e1088ba56a560dff690a1c6fef26394f4c03821c4f";
    static {
        IEvent.registerEventType(MintEvent.eventTypeHash, MintEvent.class);
    }

}
