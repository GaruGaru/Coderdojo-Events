package modularity.coderdojoevents.Settings;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import modularity.coderdojoevents.Api.EventBrite.Response.BriteEvent;
import modularity.coderdojoevents.Utils.Json;

/**
 * Created by Garu on 26/01/2016.
 */
public class DojoSettings extends SettingsManager {
    private static final String KEY_EVENTS = "EVENTS";
    private static final String KEY_USER_POS = "USER_POS";


    public DojoSettings(Context context) {
        super(context);
    }

    public BriteEvent getEvents() {
        String string = getPreferences().getString(KEY_EVENTS, "");
        return (string.isEmpty()) ? null : Json.from(string, BriteEvent.class);
    }

    public void setEvents(BriteEvent events) {
        if (events != null)
            getEditor().putString(KEY_EVENTS, Json.to(events)).commit();
    }

    public LatLng getUserPosition() {
        String json = getPreferences().getString(KEY_USER_POS, "");
        return (json.isEmpty()) ? null : Json.from(json, LatLng.class);
    }

    public void setUserPosition(LatLng latLng) {
        setUserPosition(latLng.latitude, latLng.longitude);
    }

    public void setUserPosition(double lat, double lon) {
        String json = Json.to(new LatLng(lat, lon));
        getEditor().putString(KEY_USER_POS, json).commit();
    }

}
