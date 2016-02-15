package modularity.coderdojoevents.Api;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * Created by Garu on 12/02/2016.
 */
public class ApiUtils {
    private static final int TIMEOUT = 6;

    public static OkHttpClient buildClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(TIMEOUT, TimeUnit.SECONDS);
        client.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
        client.setWriteTimeout(TIMEOUT, TimeUnit.SECONDS);
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(logger);
        return client;
    }
}
