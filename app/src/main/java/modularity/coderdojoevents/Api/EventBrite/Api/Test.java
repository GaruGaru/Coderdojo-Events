package modularity.coderdojoevents.Api.EventBrite.Api;

import java.io.IOException;

import modularity.coderdojoevents.Api.EventBrite.RequestBuilder.Request;
import modularity.coderdojoevents.Api.EventBrite.RequestBuilder.RequestBuilder;
import modularity.coderdojoevents.Api.EventBrite.Response.BriteEvent;
import retrofit.Call;

/**
 * Created by Garu on 13/01/2016.
 */
public class Test {
    public static void main(String[] args) throws IOException {

        Request request = RequestBuilder.build()
                .search("Coderdojo")
                .from(42.11231, 11.241324)
                .within(50).unit(Request.UNIT_KM)
                .expand("venue", "organizer", "ticket_classes")
                .sortBy("date");

        Call<BriteEvent> execute = request.execute(new EventBrite().getApi(), EventBrite.PUBLIC_TOKEN);



    }
}
