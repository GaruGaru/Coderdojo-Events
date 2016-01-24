package modularity.coderdojoevents.Utils;

/**
 * Created by Garu on 24/01/2016.
 */
public class StringUtils {

    public static String shorten(String str, int l) {
        return (str.length() <= l) ? str : str.substring(0, l) + "...";
    }

}
