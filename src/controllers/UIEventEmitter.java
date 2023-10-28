package controllers;

import event.Event;

public interface UIEventEmitter<T extends Event> {

    /**
     * Emit event
     */
    public void emit(T payload);
}
