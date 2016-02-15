package modularity.coderdojoevents.Api.EventBrite.Response;

import java.io.Serializable;

public class Ticket_classes implements Serializable {

    public static final String AVAILABLE = "AVAILABLE";


    private String on_sale_status;

    private String event_id;

    private String donation;

    private String minimum_quantity;

    private String hide_description;

    private String id;

    private String free;

    private String maximum_quantity;

    private String description;

    private String name;

    private String maximum_quantity_per_order;

    private String resource_uri;

    private String[] variants;

    private boolean has_pdf_ticket;

    public String getOn_sale_status() {
        return on_sale_status;
    }

    public void setOn_sale_status(String on_sale_status) {
        this.on_sale_status = on_sale_status;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getDonation() {
        return donation;
    }

    public void setDonation(String donation) {
        this.donation = donation;
    }

    public String getMinimum_quantity() {
        return minimum_quantity;
    }

    public void setMinimum_quantity(String minimum_quantity) {
        this.minimum_quantity = minimum_quantity;
    }

    public String getHide_description() {
        return hide_description;
    }

    public void setHide_description(String hide_description) {
        this.hide_description = hide_description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getMaximum_quantity() {
        return maximum_quantity;
    }

    public void setMaximum_quantity(String maximum_quantity) {
        this.maximum_quantity = maximum_quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaximum_quantity_per_order() {
        return maximum_quantity_per_order;
    }

    public void setMaximum_quantity_per_order(String maximum_quantity_per_order) {
        this.maximum_quantity_per_order = maximum_quantity_per_order;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }

    public String[] getVariants() {
        return variants;
    }

    public void setVariants(String[] variants) {
        this.variants = variants;
    }

    public boolean getHas_pdf_ticket() {
        return has_pdf_ticket;
    }

    public void setHas_pdf_ticket(boolean has_pdf_ticket) {
        this.has_pdf_ticket = has_pdf_ticket;
    }

    @Override
    public String toString() {
        return "[on_sale_status = " + on_sale_status + ", event_id = " + event_id + ", donation = " + donation + ", minimum_quantity = " + minimum_quantity + ", hide_description = " + hide_description + ", id = " + id + ", free = " + free + ", maximum_quantity = " + maximum_quantity + ", description = " + description + ", name = " + name + ", maximum_quantity_per_order = " + maximum_quantity_per_order + ", resource_uri = " + resource_uri + ", variants = " + variants + ", has_pdf_ticket = " + has_pdf_ticket + "]";
    }
}