package modularity.coderdojoevents.Api.EventBrite.Response;

import java.io.Serializable;

public class Next_occurrence implements Serializable {
    private String timezone;

    private String utc;

    private String local;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "ClassPojo [timezone = " + timezone + ", utc = " + utc + ", local = " + local + "]";
    }
}
