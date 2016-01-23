package modularity.coderdojoevents.Settings;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager {

    private static final String APP_SETTINGS_KEY = "modularity.coderdojoevents";
    private Context context;

    public SettingsManager(Context context) {
        this.context = context;
    }

    public SharedPreferences.Editor getEditor() {
        return context.getSharedPreferences(APP_SETTINGS_KEY, Context.MODE_PRIVATE).edit();
    }

    public SharedPreferences getPreferences() {
        return context.getSharedPreferences(APP_SETTINGS_KEY, Context.MODE_PRIVATE);
    }
}