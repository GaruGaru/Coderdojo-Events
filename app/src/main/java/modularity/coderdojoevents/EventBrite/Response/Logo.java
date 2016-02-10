package modularity.coderdojoevents.EventBrite.Response;

import java.io.Serializable;

public class Logo implements Serializable {
    private String id;

    private String edge_color_set;

    private String edge_color;

    private String url;

    private String aspect_ratio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEdge_color_set() {
        return edge_color_set;
    }

    public void setEdge_color_set(String edge_color_set) {
        this.edge_color_set = edge_color_set;
    }

    public String getEdge_color() {
        return edge_color;
    }

    public void setEdge_color(String edge_color) {
        this.edge_color = edge_color;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAspect_ratio() {
        return aspect_ratio;
    }

    public void setAspect_ratio(String aspect_ratio) {
        this.aspect_ratio = aspect_ratio;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", edge_color_set = " + edge_color_set + ", edge_color = " + edge_color + ", url = " + url + ", aspect_ratio = " + aspect_ratio + "]";
    }
}
