package modularity.coderdojoevents.Api.MapBox.Response;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by Garu on 12/02/2016.
 */
public class DirectionRequest {
    private double[][] coordinates;

    public DirectionRequest(double slat, double slon, double dlat, double dlon) {
        this.coordinates = new double[2][2];
        this.coordinates[0] = new double[]{slat, slon};
        this.coordinates[1] = new double[]{dlat, dlon};
    }

    public DirectionRequest(LatLng source, LatLng destination) {
        this(source.latitude, source.longitude, destination.latitude, destination.longitude);
    }

    public double[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[][] coordinates) {
        this.coordinates = coordinates;
    }

    public HashMap<String, double[][]> toBody() {
        HashMap<String, double[][]> map = new HashMap<>();
        map.put("coordinates", getCoordinates());
        return map;
    }

    @Override
    public String toString() {
        return "[coordinates = " + coordinates + "]";
    }
}