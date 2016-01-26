package modularity.coderdojoevents.Location;

import android.os.AsyncTask;

/**
 * Created by Garu on 09/04/2015.
 */
public class PositionTimerAsync extends AsyncTask<Integer, Integer, Boolean> {
    private PositionListener listener;

    public PositionTimerAsync(PositionListener listener) {
        this.listener = listener;
    }


    @Override
    protected Boolean doInBackground(Integer... params) {
        try {
            Thread.sleep(params[0]);
            return true;
        } catch (InterruptedException ignored) {

        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (!isCancelled())
            listener.onPositionFixed(null);
    }
}