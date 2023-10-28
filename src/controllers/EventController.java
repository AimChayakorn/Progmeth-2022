package controllers;

import event.Event;
import pane.UIEventListener;

public abstract class EventController<T extends Event> extends SubController implements UIEventEmitter<T> {

    /**
     * Initialize event controller
     */
    public EventController(Controller parent) {
        super(parent);
    }

    /**
     * Emit event
     */
    public void emit(T payload) {
        Object ui = this.getController().getUI();
        try {
            @SuppressWarnings("unchecked")
            UIEventListener<T> listener = (UIEventListener<T>) ui;
            listener.handleEvent(payload);
        } catch (Exception e) {
            System.out.println("Unexpected event listener");
            System.out.println(e);
        }
    }
}
