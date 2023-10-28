package pane;

public interface UIEventListener<T> {
    public void handleEvent(T payload);
}
