package modularity.coderdojoevents.EventBrite.Api;

import modularity.coderdojoevents.EventBrite.Response.BriteEventByArea;
import modularity.coderdojoevents.EventBrite.Response.EventList;
import modularity.coderdojoevents.EventBrite.Response.Venue;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Garu on 13/01/2016.
 */
public interface BriteApi {

    @GET("/v3/events/search/")
    Call<EventList> getEventsByKeyword(
            @Query("q") String keyword,
            @Query("token") String authKey
    );


    @GET("/v3/events/search/")
    Call<EventList> getEventsByKeyword(
            @Query("q") String keyword,
            @Query("sort_by") String sortBy,
            @Query("token") String authKey
    );


    @GET("/v3/events/search/")
    Call<EventList> getEventsByArea(
            @Query("q") String keyword,
            @Query("location.latitude") String latitude,
            @Query("location.longitude") String longitude,
            @Query("location.within") String distance,
            @Query("token") String authKey
    );

    @GET("/v3/events/search/")
    Call<EventList> getEventsByArea(
            @Query("q") String keyword,
            @Query("location.latitude") String latitude,
            @Query("location.longitude") String longitude,
            @Query("location.within") String distance,
            @Query("sort_by") String sortBy,
            @Query("token") String authKey
    );

    @GET("/v3/events/search/")
    Call<EventList> getEventsByUser(
            @Query("q") String keyword,
            @Query("user.id") String userId,
            @Query("sort_by") String sortBy,
            @Query("token") String authKey
    );

    @GET("/v3/events/search/")
    Call<EventList> getEventsByUser(
            @Query("q") String keyword,
            @Query("user.id") String userId,
            @Query("token") String authKey
    );

    @GET("/v3/events/search/")
    Call<EventList> getEventsByOrganizer(
            @Query("q") String keyword,
            @Query("organizer.id") String organizerId,
            @Query("sort_by") String sortBy,
            @Query("token") String authKey
    );


    @GET("/v3/events/search/")
    Call<EventList> getEventsByOrganizer(
            @Query("q") String keyword,
            @Query("organizer.id") String organizerId,
            @Query("token") String authKey
    );


    @GET("/v3/events/search/")
    Call<EventList> getEventsByAddress(
            @Query("q") String keyword,
            @Query("location.address") String locationAddress,
            @Query("token") String authKey
    );

    @GET("/v3/events/search/")
    Call<EventList> getEventsByAddress(
            @Query("q") String keyword,
            @Query("location.address") String locationAddress,
            @Query("sort_by") String sortBy,
            @Query("token") String authKey
    );

    @GET("/v3/events/search/")
    Call<EventList> getEventsByCity(
            @Query("q") String keyword,
            @Query("venue.city") String city,
            @Query("sort_by") String sortBy,
            @Query("token") String authKey
    );

    @GET("/v3/events/search/")
    Call<EventList> getEventsByCity(
            @Query("q") String keyword,
            @Query("venue.city") String city,
            @Query("token") String authKey
    );

    @GET("/v3/events/search/")
    Call<EventList> getEventsByRegion(
            @Query("q") String keyword,
            @Query("venue.region") String region,
            @Query("token") String authKey
    );


    @GET("/v3/events/search/")
    Call<EventList> getEventsByRegion(
            @Query("q") String keyword,
            @Query("venue.region") String region,
            @Query("sort_by") String sortBy,
            @Query("token") String authKey
    );

    @GET("/v3/events/search/")
    Call<EventList> getEventsByCountry(
            @Query("q") String keyword,
            @Query("venue.country") String country,
            @Query("token") String authKey
    );

    @GET("/v3/events/search/")
    Call<EventList> getEventsByCountry(
            @Query("q") String keyword,
            @Query("venue.country") String country,
            @Query("sort_by") String sortBy,
            @Query("token") String authKey
    );

    @GET("/v3/venues/{id}/")
    Call<Venue> getVenue(@Path("id") String id, @Query("token") String authKey);


    @GET("/v3/events/search/")
    Call<BriteEventByArea> getEventsByArea(
            @Query("q") String keyword,
            @Query("location.latitude") String latitude,
            @Query("location.longitude") String longitude,
            @Query("location.within") String distance,
            @Query("expand") String expand,
            @Query("sort_by") String sortBy,
            @Query("token") String authKey);

}
