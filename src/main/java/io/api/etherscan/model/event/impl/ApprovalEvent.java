package io.api.etherscan.model.event.impl;

import io.api.etherscan.model.event.IEvent;

public class ApprovalEvent extends Event {

    static final String eventTypeHash = "0x8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925";
    static {
        IEvent.registerEventType(ApprovalEvent.eventTypeHash, ApprovalEvent.class);
    }
}
