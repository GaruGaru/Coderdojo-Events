package modularity.coderdojoevents.Api.MapBox.Tests;

import java.io.IOException;

import modularity.coderdojoevents.Api.MapBox.MapBox;
import modularity.coderdojoevents.Api.MapBox.Response.DirectionRequest;
import modularity.coderdojoevents.Api.MapBox.Response.MapDirection;
import retrofit.Call;

/**
 * Created by Garu on 12/02/2016.
 */
public class ApiTest {
    public static void main(String[] args) throws IOException {

        MapBox mapBox = new MapBox();

        DirectionRequest request = new DirectionRequest(43.684516, 11.258139, 43.752273, 11.319688);
        Call<MapDirection> direction = mapBox.getMapBoxApi().getDirection(MapBox.TYPE_DRIVING, request, MapBox.TOKEN);

        MapDirection body = direction.execute().body();

        System.out.print(body.toString());

    }
}
