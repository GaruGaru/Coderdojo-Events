package modularity.coderdojoevents.Location;

import android.content.Context;
import android.location.LocationManager;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Garu on 09/04/2015.
 */
public class TimedPositionRequester implements PositionListener {
    private final int maxFixTime;
    private PositionListener listener;
    private PositionRequester gpsRequester;
    private PositionRequester networkRequester;
    private PositionTimerAsync timer;

    public TimedPositionRequester(PositionListener listener, int maxFixTime) {
        this.maxFixTime = maxFixTime;
        this.listener = listener;
    }

    public void requestPosition(Context context) {

        this.gpsRequester = new PositionRequester();
        this.networkRequester = new PositionRequester();
        this.timer = new PositionTimerAsync(this);

        this.gpsRequester.requestLocation(this, context, LocationManager.GPS_PROVIDER);
        this.networkRequester.requestLocation(this, context, LocationManager.NETWORK_PROVIDER);
        this.timer.execute(maxFixTime);

    }

    @Override
    public void onPositionFixed(LatLng position) {
        this.gpsRequester.dispose();
        this.networkRequester.dispose();
        this.timer.cancel(true);
        this.listener.onPositionFixed(position);
    }
}