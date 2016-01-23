package modularity.coderdojoevents.EventBrite.Api;

import java.io.IOException;

import modularity.coderdojoevents.EventBrite.Android.BriteListener;
import modularity.coderdojoevents.EventBrite.Response.BriteEventByArea;
import modularity.coderdojoevents.EventBrite.Response.EventList;
import modularity.coderdojoevents.EventBrite.Response.Events;
import modularity.coderdojoevents.Utils.DateUtils;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by Garu on 13/01/2016.
 */
public class Test {
    public static void main(String[] args) throws IOException {

        BriteManager manager = new BriteManager();

        Call<BriteEventByArea> eventsByArea = manager.getApi()
                .getEventsByArea("Coderdojo", "43.4674366", "11.15248447", "50km", "venue,organizer", "distance", BriteManager.PUBLIC_TOKEN);

        BriteEventByArea body = eventsByArea.execute().body();

        for (Events event : body.getEvents()) {
            System.out.println(event.getName().getText());
            System.out.println(event.getOrganizer().getName());
            System.out.println(DateUtils.formatUtc(event.getStart().getLocal()));
            System.out.println();
        }


    }
}
