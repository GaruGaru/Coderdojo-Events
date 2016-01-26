package modularity.coderdojoevents.Location;

import com.google.android.gms.maps.model.LatLng;

public interface PositionListener {
    void onPositionFixed(LatLng position);
}