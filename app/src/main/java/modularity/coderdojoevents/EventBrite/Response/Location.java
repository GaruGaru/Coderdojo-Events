package modularity.coderdojoevents.EventBrite.Response;

public class Location
{
    private String longitude;

    private String within;

    private String latitude;

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getWithin ()
    {
        return within;
    }

    public void setWithin (String within)
    {
        this.within = within;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [longitude = "+longitude+", within = "+within+", latitude = "+latitude+"]";
    }
}

		