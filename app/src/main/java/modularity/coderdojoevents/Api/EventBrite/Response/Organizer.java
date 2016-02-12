package modularity.coderdojoevents.Api.EventBrite.Response;

import java.io.Serializable;

public class Organizer implements Serializable {
    private String id;

    private Logo logo;

    private Description description;

    private String name;

    private String num_future_events;

    private String resource_uri;

    private String num_past_events;

    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum_future_events() {
        return num_future_events;
    }

    public void setNum_future_events(String num_future_events) {
        this.num_future_events = num_future_events;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    public String getNum_past_events() {
        return num_past_events;
    }

    public void setNum_past_events(String num_past_events) {
        this.num_past_events = num_past_events;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", logo = " + logo + ", description = " + description + ", name = " + name + ", num_future_events = " + num_future_events + ", resource_uri = " + resource_uri + ", num_past_events = " + num_past_events + ", url = " + url + "]";
    }
}

		