package modularity.coderdojoevents.Api.MapBox.Response;

/**
 * Created by Garu on 13/02/2016.
 */
public class DistanceParser {
    public static double parse(MapDirection direction) {
        double total = 0;
        Double[][] durations = direction.getDurations();
        for (Double[] duration : durations)
            for (int j = 0; j < durations[0].length; j++)
                if (duration[j] != null && duration[j] != 0)
                    total += duration[j];

        return total;
    }
}
