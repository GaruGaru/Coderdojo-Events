package modularity.coderdojoevents.EventBrite.Api;

import java.io.IOException;

import modularity.coderdojoevents.EventBrite.Response.BriteEvent;
import modularity.coderdojoevents.EventBrite.Response.Events;
import modularity.coderdojoevents.Utils.DateUtils;
import retrofit.Call;

/**
 * Created by Garu on 13/01/2016.
 */
public class Test {
    public static void main(String[] args) throws IOException {

        BriteManager manager = new BriteManager();


        Call<BriteEvent> eventsByCity = manager.getApi().getEventsByCity("Coderdojo", "Laterina", "venue,organizer", "date", BriteManager.PUBLIC_TOKEN);

        BriteEvent body = eventsByCity.execute().body();

        for (Events event : body.getEvents()) {
            System.out.println(event.getName().getText());
            System.out.println(event.getOrganizer().getName());
            System.out.println(DateUtils.formatUtc(event.getStart().getLocal()));
            System.out.println(event.getCapacity());
            System.out.println();
        }


    }
}
