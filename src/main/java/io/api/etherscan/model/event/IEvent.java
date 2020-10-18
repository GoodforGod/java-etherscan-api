package io.api.etherscan.model.event;

import io.api.etherscan.error.ApiException;
import io.api.etherscan.error.EventModelException;
import io.api.etherscan.model.Log;

import java.util.HashMap;
import java.util.Map;

public interface IEvent {

    static final Map<String, Class<?>> subTypes = new HashMap<>();

    void setLog(Log log);

    static void registerEventType(String typeHash, Class<?> clazz) {
        subTypes.put(typeHash, clazz);
    }

    static IEvent createEvent(String typeHash, Log log) {
        if (null == typeHash) {
            throw new EventModelException("Event type hash cannot be null");
        }
        Class<?> clazz = subTypes.get(typeHash);
        try {
            IEvent evt = (IEvent) clazz.newInstance();
            evt.setLog(log);
            return evt;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new ApiException("Client-side error instantiating Event object", ex);
        }
    }
}
