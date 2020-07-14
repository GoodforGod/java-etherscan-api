package io.api.etherscan.model.event.impl;

import io.api.etherscan.model.event.IEvent;

public class SyncEvent extends Event {
    static final String eventTypeHash = "0x1c411e9a96e071241c2f21f7726b17ae89e3cab4c78be50e062b03a9fffbbad1";
    static {
        IEvent.registerEventType(SyncEvent.eventTypeHash, SyncEvent.class);
    }
}
