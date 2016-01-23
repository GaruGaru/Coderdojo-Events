package modularity.coderdojoevents.EventBrite.Api;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Garu on 13/01/2016.
 */
public class BriteManager {

    private static final int TIMEOUT = 3;

    public static final String PUBLIC_TOKEN = "BKIYA2K56NSZUDVG6LWR";
    private static final String ENDPOINT = "http://eventbriteapi.com/";

    private final BriteApi api;

    public BriteManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(buildClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.api = retrofit.create(BriteApi.class);
    }

    private OkHttpClient buildClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(TIMEOUT, TimeUnit.SECONDS);
        client.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
        client.setWriteTimeout(TIMEOUT, TimeUnit.SECONDS);
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(logger);
        return client;
    }

    public BriteApi getApi() {
        return api;
    }

}
