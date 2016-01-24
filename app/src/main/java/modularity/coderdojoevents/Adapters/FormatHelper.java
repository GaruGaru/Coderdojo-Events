package modularity.coderdojoevents.Adapters;

import modularity.coderdojoevents.EventBrite.Response.Venue;
import modularity.coderdojoevents.Utils.StringUtils;

/**
 * Created by Garu on 24/01/2016.
 */
public class FormatHelper {

    public static String formatVenue(Venue venue) {
        String str = "";
        str += venue.getAddress().getCity() + " - " + venue.getAddress().getAddress_1();
        str = StringUtils.shorten(str, 30);
        return str;
    }


}
