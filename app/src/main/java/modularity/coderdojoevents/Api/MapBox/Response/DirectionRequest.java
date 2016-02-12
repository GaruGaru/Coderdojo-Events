package modularity.coderdojoevents.Api.MapBox.Response;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Garu on 12/02/2016.
 */
public class DirectionRequest {
    private String[][] coordinates;

    public DirectionRequest(String slat, String slon, String dlat, String dlon) {
        this.coordinates = new String[2][2];
        this.coordinates[0] = new String[]{slat, slon};
        this.coordinates[1] = new String[]{dlat, dlon};
    }

    public DirectionRequest(double slat, double slon, double dlat, double dlon) {
        this(String.valueOf(slat), String.valueOf(slon), String.valueOf(dlat), String.valueOf(dlon));
    }

    public DirectionRequest(LatLng source, LatLng destination) {
        this(source.latitude, source.longitude, destination.latitude, destination.longitude);
    }

    public String[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String[][] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "[coordinates = " + coordinates + "]";
    }
}