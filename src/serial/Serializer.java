package serial;

import java.util.ArrayList;
import java.util.List;

public class Serializer {

    /**
     * Separator for serializer
     */
    private static final String SEPARATOR = ",";

    /**
     * Buffer for serializer
     */
    private StringBuilder buffer;

    /**
     * Initialize serializer
     */
    public Serializer() {
        buffer = new StringBuilder();
    }

    /**
     * Add token to buffer
     */
    private void addToken(String token) {
        if (!buffer.isEmpty()) {
            buffer.append(SEPARATOR);
        }
        buffer.append(token);
    }

    /**
     * Serialize object
     */
    public <T extends Serialize> void serialize(T data) {
        data.serialize(this);
    }

    /**
     * Serialize list of object
     */
    public <T extends Serialize> void serialize(List<T> data) {
        addToken(String.valueOf(data.size()));
        for (T d : data) {
            serialize(d);
        }
    }

    /**
     * Serialize array list of object
     */
    public <T extends Serialize> void serialize(ArrayList<T> data) {
        addToken(String.valueOf(data.size()));
        for (T d : data) {
            serialize(d);
        }
    }

    /**
     * Serialize string
     */
    public void serialize(String data) {
        addToken(data);
    }

    /**
     * Serialize integer
     */
    public void serialize(Integer data) {
        addToken(data.toString());
    }

    /**
     * Build the result
     */
    public String build() {
        return buffer.toString();
    }
}
