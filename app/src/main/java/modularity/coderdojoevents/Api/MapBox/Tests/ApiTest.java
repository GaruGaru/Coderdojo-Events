package modularity.coderdojoevents.Api.MapBox.Tests;

import java.io.IOException;

import modularity.coderdojoevents.Api.MapBox.Api.MapBox;
import modularity.coderdojoevents.Api.MapBox.Response.DirectionRequest;
import modularity.coderdojoevents.Api.MapBox.Response.MapDirection;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by Garu on 12/02/2016.
 */
public class ApiTest {
    public static void main(String[] args) throws IOException {

        MapBox mapBox = new MapBox();

        DirectionRequest request = new DirectionRequest(43.684516, 11.258139, 41.902783, 12.496366);

        Call<MapDirection> direction = mapBox.getMapBoxApi().getDirection(MapBox.TYPE_DRIVING, request.toBody(), MapBox.TOKEN);

        Response<MapDirection> execute = direction.execute();

        System.out.println(execute.toString());

    }
}
