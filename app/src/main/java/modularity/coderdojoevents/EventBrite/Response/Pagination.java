package modularity.coderdojoevents.EventBrite.Response;

import java.io.Serializable;

public class Pagination implements Serializable {
    private String page_size;

    private String object_count;

    private String page_number;

    private String page_count;

    public String getPage_size() {
        return page_size;
    }

    public void setPage_size(String page_size) {
        this.page_size = page_size;
    }

    public String getObject_count() {
        return object_count;
    }

    public void setObject_count(String object_count) {
        this.object_count = object_count;
    }

    public String getPage_number() {
        return page_number;
    }

    public void setPage_number(String page_number) {
        this.page_number = page_number;
    }

    public String getPage_count() {
        return page_count;
    }

    public void setPage_count(String page_count) {
        this.page_count = page_count;
    }

    @Override
    public String toString() {
        return "ClassPojo [page_size = " + page_size + ", object_count = " + object_count + ", page_number = " + page_number + ", page_count = " + page_count + "]";
    }
}

	