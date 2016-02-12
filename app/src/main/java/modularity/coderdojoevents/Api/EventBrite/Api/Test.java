package modularity.coderdojoevents.Api.EventBrite.Api;

import java.io.IOException;

import modularity.coderdojoevents.Api.EventBrite.Android.AsyncBriteRequestArea;
import modularity.coderdojoevents.Api.EventBrite.Android.BriteListener;
import modularity.coderdojoevents.Api.EventBrite.Response.BriteEvent;
import modularity.coderdojoevents.Api.EventBrite.Response.Events;
import modularity.coderdojoevents.Utils.DateUtils;

/**
 * Created by Garu on 13/01/2016.
 */
public class Test {
    public static void main(String[] args) throws IOException {


        new AsyncBriteRequestArea(new BriteListener() {
            @Override
            public void onRequestDone(BriteEvent eventList) {

                for (Events event : eventList.getEvents()) {
                    System.out.println(event.getName().getText());
                    System.out.println(event.getOrganizer().getName());
                    System.out.println(DateUtils.formatUtc(event.getStart().getLocal()));
                    System.out.println(event.getCapacity());
                    System.out.println(event.getStatus());
                    System.out.println();
                }
            }

            @Override
            public void onRequestError(int errorCode) {

            }
        }).execute("Coderdojo",
                "42",
                "11",
                "50km",
                "venue,organizer",
                "date"
        );


    }
}
