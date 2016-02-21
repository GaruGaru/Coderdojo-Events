package modularity.coderdojoevents.Api.EventBrite.RequestBuilder;

import com.google.android.gms.maps.model.LatLng;

import modularity.coderdojoevents.Settings.DojoSettings;


/**
 * Created by Garu on 21/02/2016.
 */
public class DojoRequestBuilder {

    public static final String QUERY = "Coderdojo";
    public static final String[] EXPANDS = new String[]{"venue", "organizer", "ticket_classes"};
    public static final String UNIT = Request.KM;
    public static final String SORTBY = Request.DATE;

    public static Request build(LatLng from, int within) {
        return RequestBuilder.build()
                .search(QUERY)
                .from(from)
                .within(within).unit(UNIT)
                .expand(EXPANDS)
                .sortBy(SORTBY);
    }

    public static Request build(DojoSettings user) {
        return RequestBuilder.build()
                .search(QUERY)
                .from(user.getUserPosition())
                .within(user.getEventDistance()).unit(UNIT)
                .expand(EXPANDS)
                .sortBy(SORTBY);
    }
}
