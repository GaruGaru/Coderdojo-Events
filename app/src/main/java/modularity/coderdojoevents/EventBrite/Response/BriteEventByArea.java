package modularity.coderdojoevents.EventBrite.Response;

import java.io.Serializable;

public class BriteEventByArea implements Serializable
{
    private Location location;

    private Events[] events;

    private Pagination pagination;

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation (Location location)
    {
        this.location = location;
    }

    public Events[] getEvents ()
    {
        return events;
    }

    public void setEvents (Events[] events)
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

    @Override
    public String toString()
    {
        return "ClassPojo [location = "+location+", events = "+events+", pagination = "+pagination+"]";
    }
}
