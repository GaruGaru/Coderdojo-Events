package modularity.coderdojoevents.EventBrite.Android;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import modularity.coderdojoevents.EventBrite.Api.BriteManager;
import modularity.coderdojoevents.EventBrite.Response.BriteEvent;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by Garu on 22/01/2016.
 */
public class AsyncBriteRequestArea extends AsyncTask<String, Integer, BriteEvent> {

    public static final int ERROR_NULL_RESPONSE = 1;
    private static final int ERROR_IO = 2;
    private List<BriteListener> listeners;
    private int responseCode;

    public AsyncBriteRequestArea(BriteListener... listeners) {
        this.listeners = new ArrayList<>();
        Collections.addAll(this.listeners, listeners);
    }

    @Override
    protected BriteEvent doInBackground(String... params) {
        try {

            BriteManager briteManager = new BriteManager();
            Call<BriteEvent> apiCall = briteManager.getApi().getEventsByArea(params[0], params[1], params[2], params[3], params[4], params[5], BriteManager.PUBLIC_TOKEN);
            Response<BriteEvent> response = apiCall.execute();
            this.responseCode = response.code();
            return (response.isSuccess()) ? response.body() : null;

        } catch (IOException e) {
            this.responseCode = ERROR_IO;
            return null;
        }
    }

    @Override
    protected void onPostExecute(BriteEvent eventList) {
        requestDone(eventList);
    }

    private void requestDone(BriteEvent response) {
        if (response == null) requestError(ERROR_NULL_RESPONSE);
        else
            for (BriteListener listener : listeners)
                listener.onRequestDone(response);
    }

    private void requestError(int errorCode) {
        for (BriteListener listener : listeners)
            listener.onRequestError(errorCode);
    }
}
