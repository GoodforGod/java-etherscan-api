package io.api.etherscan.model.event.impl;

import io.api.etherscan.model.Log;
import io.api.etherscan.model.event.IEvent;

/**
 * Base class for a higher-level API on top of {@link Log}. Each Event class has
 * an identifying hash
 */
public class Event implements IEvent {

    static String eventTypeHash;

    private Log log;

    String address;

    public static String getEventTypeHash() {
        return eventTypeHash;
    }

    public Log getLog() {
        return log;
    }

    public String getAddress() {
        return address;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
