package modularity.coderdojoevents.Utils;

import android.content.Intent;
import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Garu on 11/02/2016.
 */
public class MapsUtils {
    public static Intent getDirectionIntent(LatLng source, LatLng destination) {
        String saddr = source.latitude + "," + source.longitude;
        String daddr = destination.latitude + "," + destination.longitude;
        return new Intent(
                android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr=" + saddr + "&daddr=" + daddr)
        );
    }
}
