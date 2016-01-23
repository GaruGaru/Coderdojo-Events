package modularity.coderdojoevents.EventBrite.Android;

import modularity.coderdojoevents.EventBrite.Response.BriteEventByArea;
import modularity.coderdojoevents.EventBrite.Response.EventList;

/**
 * Created by Garu on 22/01/2016.
 */
public interface BriteListener {
    void onRequestDone(BriteEventByArea eventList);
    void onRequestError(int errorCode);
}
