package modularity.coderdojoevents.Api.EventBrite.Api;

import modularity.coderdojoevents.Api.ApiUtils;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Garu on 13/01/2016.
 */
public class EventBrite {

    public static final String PUBLIC_TOKEN = "BKIYA2K56NSZUDVG6LWR";

    private static final String ENDPOINT = "http://eventbriteapi.com/";

    private final BriteApi api;

    public EventBrite() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(ApiUtils.buildHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.api = retrofit.create(BriteApi.class);
    }


    public BriteApi getApi() {
        return api;
    }

}
