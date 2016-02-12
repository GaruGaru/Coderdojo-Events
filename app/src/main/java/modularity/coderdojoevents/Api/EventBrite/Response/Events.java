package modularity.coderdojoevents.Api.EventBrite.Response;

import java.io.Serializable;

public class Events implements Serializable {
    private String online_event;

    private String locale;

    private String organizer_id;

    private String is_locked;

    private String currency;

    private String changed;

    private String id;

    private String is_series_parent;

    private String created;

    private Description description;

    private Name name;

    private String capacity;

    private String venue_id;

    private End end;

    private String is_series;

    private String subcategory_id;

    private String listed;

    private Logo logo;

    private String shareable;

    private String status;

    private String category_id;

    private Venue venue;

    private Organizer organizer;

    private String url;

    private String logo_id;

    private Start start;

    private String resource_uri;

    private String format_id;

    private String tx_time_limit;

    private String hide_start_date;

    private String privacy_setting;

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public String getOnline_event() {
        return online_event;
    }

    public void setOnline_event(String online_event) {
        this.online_event = online_event;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getOrganizer_id() {
        return organizer_id;
    }

    public void setOrganizer_id(String organizer_id) {
        this.organizer_id = organizer_id;
    }

    public String getIs_locked() {
        return is_locked;
    }

    public void setIs_locked(String is_locked) {
        this.is_locked = is_locked;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIs_series_parent() {
        return is_series_parent;
    }

    public void setIs_series_parent(String is_series_parent) {
        this.is_series_parent = is_series_parent;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public End getEnd() {
        return end;
    }

    public void setEnd(End end) {
        this.end = end;
    }

    public String getIs_series() {
        return is_series;
    }

    public void setIs_series(String is_series) {
        this.is_series = is_series;
    }

    public String getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(String subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public String getListed() {
        return listed;
    }

    public void setListed(String listed) {
        this.listed = listed;
    }

    public Logo getLogo() {
        return logo;
    }

    public void setLogo(Logo logo) {
        this.logo = logo;
    }

    public String getShareable() {
        return shareable;
    }

    public void setShareable(String shareable) {
        this.shareable = shareable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo_id() {
        return logo_id;
    }

    public void setLogo_id(String logo_id) {
        this.logo_id = logo_id;
    }

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    public String getFormat_id() {
        return format_id;
    }

    public void setFormat_id(String format_id) {
        this.format_id = format_id;
    }

    public String getTx_time_limit() {
        return tx_time_limit;
    }

    public void setTx_time_limit(String tx_time_limit) {
        this.tx_time_limit = tx_time_limit;
    }

    public String getHide_start_date() {
        return hide_start_date;
    }

    public void setHide_start_date(String hide_start_date) {
        this.hide_start_date = hide_start_date;
    }

    public String getPrivacy_setting() {
        return privacy_setting;
    }

    public void setPrivacy_setting(String privacy_setting) {
        this.privacy_setting = privacy_setting;
    }

    @Override
    public String toString() {
        return "ClassPojo [online_event = " + online_event + ", locale = " + locale + ", organizer_id = " + organizer_id + ", is_locked = " + is_locked + ", currency = " + currency + ", changed = " + changed + ", id = " + id + ", is_series_parent = " + is_series_parent + ", created = " + created + ", description = " + description + ", name = " + name + ", capacity = " + capacity + ", venue_id = " + venue_id + ", end = " + end + ", is_series = " + is_series + ", subcategory_id = " + subcategory_id + ", listed = " + listed + ", logo = " + logo + ", shareable = " + shareable + ", status = " + status + ", category_id = " + category_id + ", venue = " + venue + ", url = " + url + ", logo_id = " + logo_id + ", start = " + start + ", resource_uri = " + resource_uri + ", format_id = " + format_id + ", tx_time_limit = " + tx_time_limit + ", hide_start_date = " + hide_start_date + ", privacy_setting = " + privacy_setting + "]";
    }
}