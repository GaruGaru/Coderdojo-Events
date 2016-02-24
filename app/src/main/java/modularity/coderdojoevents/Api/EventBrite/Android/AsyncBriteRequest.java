package modularity.coderdojoevents.Api.EventBrite.Android;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import modularity.coderdojoevents.Api.EventBrite.Api.EventBrite;
import modularity.coderdojoevents.Api.EventBrite.RequestBuilder.Request;
import modularity.coderdojoevents.Api.EventBrite.Response.BriteEvent;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by Garu on 22/01/2016.
 */
public class AsyncBriteRequest extends AsyncTask<Request, Integer, BriteEvent> {

    public static final int ERROR_NULL_RESPONSE = 1;
    private List<BriteListener> listeners;

    public AsyncBriteRequest(BriteListener... listeners) {
        this.listeners = new ArrayList<>();
        Collections.addAll(this.listeners, listeners);
    }

    @Override
    protected BriteEvent doInBackground(Request... params) {

        try {

            EventBrite eventBrite = new EventBrite();
            Call<BriteEvent> requestCall = params[0].execute(eventBrite.getApi(), EventBrite.PUBLIC_TOKEN);
            Response<BriteEvent> response = requestCall.execute();

            return (response.isSuccess()) ? response.body() : null;

        } catch (IOException e) {

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
