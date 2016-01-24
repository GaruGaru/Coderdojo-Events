package modularity.coderdojoevents.EventBrite.Response;

import java.io.Serializable;

public class Address implements Serializable
{
    private String region;

    private String postal_code;

    private String longitude;

    private String address_1;

    private String latitude;

    private String address_2;

    private String country;

    private String city;

    public String getRegion ()
    {
        return region;
    }

    public void setRegion (String region)
    {
        this.region = region;
    }

    public String getPostal_code ()
    {
        return postal_code;
    }

    public void setPostal_code (String postal_code)
    {
        this.postal_code = postal_code;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getAddress_1 ()
    {
        return address_1;
    }

    public void setAddress_1 (String address_1)
    {
        this.address_1 = address_1;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getAddress_2 ()
    {
        return address_2;
    }

    public void setAddress_2 (String address_2)
    {
        this.address_2 = address_2;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [region = "+region+", postal_code = "+postal_code+", longitude = "+longitude+", address_1 = "+address_1+", latitude = "+latitude+", address_2 = "+address_2+", country = "+country+", city = "+city+"]";
    }
}
