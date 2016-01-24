package modularity.coderdojoevents.EventBrite.Response;

import java.io.Serializable;

/**
 * Created by Garu on 13/01/2016.
 */
public class EventList implements Serializable
{
    private Location location;

    private String[] events;

    private Pagination pagination;

    private TopMatchEvents[] top_match_events;

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation (Location location)
    {
        this.location = location;
    }

    public String[] getEvents ()
    {
        return events;
    }

    public void setEvents (String[] events)
    {
        this.events = events;
    }

    public Pagination getPagination ()
    {
        return pagination;
    }

    public void setPagination (Pagination pagination)
    {
        this.pagination = pagination;
    }

    public TopMatchEvents[] getTop_match_events ()
    {
        return top_match_events;
    }

    public void setTop_match_events (TopMatchEvents[] top_match_events)
    {
        this.top_match_events = top_match_events;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [location = "+location+", events = "+events+", pagination = "+pagination+", top_match_events = "+top_match_events+"]";
    }
}

