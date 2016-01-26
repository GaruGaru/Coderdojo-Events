package modularity.coderdojoevents.Settings;

import android.content.Context;

import modularity.coderdojoevents.EventBrite.Response.BriteEvent;
import modularity.coderdojoevents.Utils.Json;

/**
 * Created by Garu on 26/01/2016.
 */
public class DojoSettings extends SettingsManager {
    private static final String KEY_EVENTS = "EVENTS";

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


}
