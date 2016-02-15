package modularity.coderdojoevents.Api.EventBrite.RequestBuilder;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import modularity.coderdojoevents.Api.EventBrite.Api.BriteApi;
import modularity.coderdojoevents.Api.EventBrite.Response.BriteEvent;
import retrofit.Call;

/**
 * Created by Garu on 15/02/2016.
 */
public class Request {

    public static final String UNIT_KM = "km";
    public static final String UNIT_MILES = "mi";

    public static final String SORT_BY_DATE = "date";
    public static final String SORT_BY_DISTANCE = "distance";

    private String query;
    private double[] from;
    private String distanceUnit = UNIT_KM;
    private int distanceWithin;
    private List<String> expand;
    private String city;
    private String sortBy = SORT_BY_DATE;

    public Request() {
        this.expand = new ArrayList<>();
        this.from = new double[2];
    }

    public Request search(String query) {
        this.query = query;
        return this;
    }

    public Request from(double lat, double lon) {
        this.from[0] = lat;
        this.from[1] = lon;
        return this;
    }

    public Request in(String place) {
        this.city = place;
        return this;
    }

    public Request from(String lat, String lon) {
        from(Double.valueOf(lat), Double.valueOf(lon));
        return this;
    }

    public Request from(LatLng latlng) {
        from(latlng.latitude, latlng.longitude);
        return this;
    }

    public Request within(int distance) {
        this.distanceWithin = distance;
        return this;
    }

    public Request expand(String... infos) {
        Collections.addAll(this.expand, infos);
        return this;
    }

    public Request unit(String unit) {
        this.distanceUnit = unit;
        return this;
    }

    private String getLatStr() {
        return String.valueOf(from[0]);
    }

    private String getLonStr() {
        return String.valueOf(from[1]);
    }

    private String getWithinStr() {
        String w = String.valueOf(distanceWithin) + distanceUnit;
        return w;
    }

    private String getExpand() {
        String expands = "";
        for (String s : expand)
            expands += s + ",";
        return expands;
    }

    public Request sortBy(String param) {
        this.sortBy = param;
        return this;
    }

    public Call<BriteEvent> execute(BriteApi api, String token) {
        if (city == null || city.isEmpty())
            return api.getEventsByArea(query, getLatStr(), getLonStr(), getWithinStr(), getExpand(), sortBy, token);
        else
            return api.getEventsByCity(query, city, getExpand(), sortBy, token);
    }


}
