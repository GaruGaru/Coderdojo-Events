package modularity.coderdojoevents.Api.MapBox.Android;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import modularity.coderdojoevents.Api.MapBox.Api.MapBox;
import modularity.coderdojoevents.Api.MapBox.Response.DirectionRequest;
import modularity.coderdojoevents.Api.MapBox.Response.MapDirection;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by Garu on 13/02/2016.
 */
public class AsyncDirectionRequest extends AsyncTask<DirectionRequest, Integer, MapDirection> {

    private List<DirectionListener> listeners;

    public AsyncDirectionRequest(DirectionListener... listeners) {
        this.listeners = new ArrayList<>();
        Collections.addAll(this.listeners, listeners);
    }

    @Override
    protected MapDirection doInBackground(DirectionRequest... params) {
        MapBox mapBox = new MapBox();

        Call<MapDirection> direction = mapBox.getMapBoxApi().getDirection(MapBox.TYPE_DRIVING, params[0].toBody(), MapBox.TOKEN);

        try {
            Response<MapDirection> execute = direction.execute();
            return (execute.isSuccess()) ? execute.body() : null;
        } catch (IOException e) {
            return null;
        }

    }

    @Override
    protected void onPostExecute(MapDirection mapDirection) {
        super.onPostExecute(mapDirection);
        for (DirectionListener listener : listeners)
            listener.onDirectionResponse(mapDirection);
    }
}
