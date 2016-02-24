package modularity.coderdojoevents.Utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Garu on 27/01/2016.
 */
public class GeoUtils {


    public static LatLng getLatLng(Context context, String location) {
        if (Geocoder.isPresent()) {
            try {
                Geocoder gc = new Geocoder(context);
                List<Address> addresses = gc.getFromLocationName(location, 5); // get the found Address Objects
                List<LatLng> ll = new ArrayList<>(addresses.size()); // A list to save the coordinates if they are available
                for (Address a : addresses)
                    if (a.hasLatitude() && a.hasLongitude())
                        ll.add(new LatLng(a.getLatitude(), a.getLongitude()));

                return ll.get(0);
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    public static LatLng getLatLng(Context context, CharSequence sequence) {
        return getLatLng(context, sequence.toString());
    }

    public static String getCityByLatLng(Context context, LatLng coord) {
        return getCityByLatLng(context, coord.latitude, coord.longitude);
    }

    public static String getCityByLatLng(Context context, double lat, double lon) {
        Geocoder gcd = new Geocoder(context, DateUtils.getLocale());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(lat, lon, 1);
            return (addresses.isEmpty()) ? null : addresses.get(0).getLocality();
        } catch (IOException e) {
            return null;
        }
    }

}
