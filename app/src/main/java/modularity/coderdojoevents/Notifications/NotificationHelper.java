package modularity.coderdojoevents.Notifications;

import java.util.ArrayList;
import java.util.List;

import modularity.coderdojoevents.Api.EventBrite.Response.Events;

/**
 * Created by Garu on 21/02/2016.
 */

public class NotificationHelper {

    public static List<Events> getDifferences(Events[] oldEvents, Events[] newEvents) {
        List<Events> newsList = new ArrayList<>();
        for (Events n : newEvents)
            if (!contains(oldEvents, n.getId()))
                newsList.add(n);
        return newsList;
    }

    private static boolean contains(Events[] lst, String id) {
        for (Events e : lst)
            if (e.getId().equals(id))
                return true;
        return false;
    }
}
