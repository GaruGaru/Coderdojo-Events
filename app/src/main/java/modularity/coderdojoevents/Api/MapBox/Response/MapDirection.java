package modularity.coderdojoevents.Api.MapBox.Response;

public class MapDirection {
    private Double[][] durations;

    private String code;

    public Double[][] getDurations() {
        return durations;
    }

    public void setDurations(Double[][] durations) {
        this.durations = durations;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "[durations = " + durations + ", code = " + code + "]";
    }
}