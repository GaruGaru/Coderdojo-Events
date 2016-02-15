package modularity.coderdojoevents.Api.MapBox.Api;

import modularity.coderdojoevents.Api.ApiUtils;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Garu on 12/02/2016.
 */
public class MapBox {

    public static final String TOKEN = "pk.eyJ1IjoiZ2FydWdhcnUiLCJhIjoiY2lranJzbWV5MDA1cnZxbHN3NzZ0NWo3diJ9.ajY1S2aB5v164f7mQs5nBg";
    public static final String TYPE_DRIVING = "driving";
    public static final String TYPE_WALKING = "walking";
    public static final String TYPE_CYCLING = "cycling ";
    private static final String ENDPOINT = "https://api.mapbox.com";
    private MapBoxApi mapBoxApi;

    public MapBox() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(ApiUtils.buildClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.mapBoxApi = retrofit.create(MapBoxApi.class);
    }


    public MapBoxApi getMapBoxApi() {
        return mapBoxApi;
    }
}
