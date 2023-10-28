package component;

import javafx.event.Event;

public abstract class Callback<T extends Event, P> {

    public abstract void call(T event, P data);
}
