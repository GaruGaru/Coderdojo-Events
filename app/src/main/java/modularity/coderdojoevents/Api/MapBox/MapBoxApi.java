package modularity.coderdojoevents.Api.MapBox;

import modularity.coderdojoevents.Api.MapBox.Response.DirectionRequest;
import modularity.coderdojoevents.Api.MapBox.Response.MapDirection;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Garu on 12/02/2016.
 */
public interface MapBoxApi {
    @POST("/distances/v1/mapbox/{type}")
    Call<MapDirection> getDirection(@Path("type") String type, @Body DirectionRequest request, @Query("access_token") String token);
}
