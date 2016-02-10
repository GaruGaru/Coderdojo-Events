package modularity.coderdojoevents.EventBrite.Android;

import modularity.coderdojoevents.EventBrite.Response.BriteEvent;

/**
 * Created by Garu on 22/01/2016.
 */
public interface BriteListener {
    void onRequestDone(BriteEvent eventList);

    void onRequestError(int errorCode);
}
