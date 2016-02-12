package modularity.coderdojoevents.Api.MapBox.Response;

public class MapDirection {
    private String[][] durations;

    private String code;

    public String[][] getDurations() {
        return durations;
    }

    public void setDurations(String[][] durations) {
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