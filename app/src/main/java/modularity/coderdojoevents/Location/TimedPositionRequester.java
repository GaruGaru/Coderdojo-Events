package modularity.coderdojoevents.Location;

import android.content.Context;
import android.location.LocationManager;
import android.os.Handler;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Garu on 09/04/2015.
 */
public class TimedPositionRequester implements PositionListener {
    private final int maxFixTime;
    private PositionListener listener;
    private PositionRequester gpsRequester;
    private PositionRequester networkRequester;
    private boolean done = false;

    public TimedPositionRequester(PositionListener listener, int maxFixTime) {
        this.maxFixTime = maxFixTime;
        this.listener = listener;
    }

    public void requestPosition(Context context) {

        this.gpsRequester = new PositionRequester();
        this.networkRequester = new PositionRequester();


        this.gpsRequester.requestLocation(this, context, LocationManager.GPS_PROVIDER);
        this.networkRequester.requestLocation(this, context, LocationManager.NETWORK_PROVIDER);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onPositionFixed(null);
            }
        }, maxFixTime);

    }

    @Override
    public void onPositionFixed(LatLng position) {
        if (!done) {
            this.gpsRequester.dispose();
            this.networkRequester.dispose();
            this.listener.onPositionFixed(position);
            done = true;
        }
    }
}