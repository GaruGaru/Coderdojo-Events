package modularity.coderdojoevents.EventBrite.Response;

import java.io.Serializable;

public class TopMatchEvents implements Serializable {

    private Venue venue;

    private String online_event;

    private String locale;

    private String organizer_id;

    private String is_locked;

    private Next_occurrence next_occurrence;

    private String currency;

    private String changed;

    private String id;

    private String created;

    private Description description;

    private Name name;

    private String venue_id;

    private String capacity;

    private String user_id;

    private End end;

    private String subcategory_id;

    private String listed;

    private Logo logo;

    private String hide_end_date;

    private String shareable;

    private String[] ticket_classes;

    private String status;

    private String category_id;

    private String url;

    private String logo_id;

    private Start start;

    private String format_id;

    private String language;

    private String hide_start_date;

    private String tx_time_limit;

    private String tld;

    private String privacy_setting;

    private String is_free;

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

    public Next_occurrence getNext_occurrence() {
        return next_occurrence;
    }

    public void setNext_occurrence(Next_occurrence next_occurrence) {
        this.next_occurrence = next_occurrence;
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

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public End getEnd() {
        return end;
    }

    public void setEnd(End end) {
        this.end = end;
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

    public String getHide_end_date() {
        return hide_end_date;
    }

    public void setHide_end_date(String hide_end_date) {
        this.hide_end_date = hide_end_date;
    }

    public String getShareable() {
        return shareable;
    }

    public void setShareable(String shareable) {
        this.shareable = shareable;
    }

    public String[] getTicket_classes() {
        return ticket_classes;
    }

    public void setTicket_classes(String[] ticket_classes) {
        this.ticket_classes = ticket_classes;
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

    public String getFormat_id() {
        return format_id;
    }

    public void setFormat_id(String format_id) {
        this.format_id = format_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHide_start_date() {
        return hide_start_date;
    }

    public void setHide_start_date(String hide_start_date) {
        this.hide_start_date = hide_start_date;
    }

    public String getTx_time_limit() {
        return tx_time_limit;
    }

    public void setTx_time_limit(String tx_time_limit) {
        this.tx_time_limit = tx_time_limit;
    }

    public String getTld() {
        return tld;
    }

    public void setTld(String tld) {
        this.tld = tld;
    }

    public String getPrivacy_setting() {
        return privacy_setting;
    }

    public void setPrivacy_setting(String privacy_setting) {
        this.privacy_setting = privacy_setting;
    }

    public String getIs_free() {
        return is_free;
    }

    public void setIs_free(String is_free) {
        this.is_free = is_free;
    }

    @Override
    public String toString() {
        return "ClassPojo [online_event = " + online_event + ", locale = " + locale + ", organizer_id = " + organizer_id + ", is_locked = " + is_locked + ", next_occurrence = " + next_occurrence + ", currency = " + currency + ", changed = " + changed + ", id = " + id + ", created = " + created + ", description = " + description + ", name = " + name + ", venue_id = " + venue_id + ", capacity = " + capacity + ", user_id = " + user_id + ", end = " + end + ", subcategory_id = " + subcategory_id + ", listed = " + listed + ", logo = " + logo + ", hide_end_date = " + hide_end_date + ", shareable = " + shareable + ", ticket_classes = " + ticket_classes + ", status = " + status + ", category_id = " + category_id + ", url = " + url + ", logo_id = " + logo_id + ", start = " + start + ", format_id = " + format_id + ", language = " + language + ", hide_start_date = " + hide_start_date + ", tx_time_limit = " + tx_time_limit + ", tld = " + tld + ", privacy_setting = " + privacy_setting + ", is_free = " + is_free + "]";
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}