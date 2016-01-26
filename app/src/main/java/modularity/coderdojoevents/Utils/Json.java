package modularity.coderdojoevents.Utils;

import com.google.gson.Gson;

/**
 * Created by Garu on 24/01/2016.
 */
public class Json {
    private static Gson instance = null;

    public static Gson get() {
        if (instance == null) instance = new Gson();
        return instance;
    }

    public static String to(Object obj) {
        return get().toJson(obj);
    }

    public static <T> T from(String jstr, Class<T> tClass) {
        return tClass.cast(get().fromJson(jstr, tClass));
    }
}
