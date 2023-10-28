package serial;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Deserializer {

    /**
     * Separator for deserializer
     */
    private static final String SEPARATOR = ",";

    /**
     * Buffer for deserializer
     */
    private String buffer;

    /**
     * Initialize deserializer
     */
    public Deserializer(String buffer) {
        this.buffer = buffer;
    }

    /**
     * Read token from buffer
     */
    public String readToken() {
        int idx = buffer.indexOf(SEPARATOR);
        if (idx == -1) {
            return buffer;
        }
        String ret = buffer.substring(0, idx);
        buffer = buffer.substring(idx + SEPARATOR.length());
        return ret;
    }

    /**
     * Deserialize integer
     */
    public int deserializeInt() {
        return Integer.parseInt(readToken());
    }

    /**
     * Deserialize string
     */
    public String deserializeString() {
        return readToken();
    }

    /**
     * Deserialize class
     */
    public <T> T deserializeClass(Class<?> c) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = c.getDeclaredMethod("deserialize", Deserializer.class);
        T result = (T) m.invoke(null, this);
        return result;
    }

    /**
     * Deserialize list
     */
    public <T> ArrayList<T> deserializeList(Class<?> c) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int count = deserializeInt();
        ArrayList<T> ret = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ret.add((T) deserialize(c));
        }
        return ret;
    }

    /**
     * Deserialize object
     */
    public Object deserialize(Class<?> c) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (c == Integer.class) {
            return deserializeInt();
        } else if (c == String.class) {
            return deserializeString();
        } else if (c == List.class) {
            return deserializeList(c);
        } else {
            return deserializeClass(c);
        }
    }

//    Class Pokemua {
//        public static Pokemua deserialize(Deserializer deserializer) {
//            int id = deserializer.deserialize(Integer);
//        }
//    }
}
