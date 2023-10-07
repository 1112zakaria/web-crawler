package ca.zakismail.crawlerbackend.crawler;

import java.util.HashSet;
import java.util.Objects;

public class Page {
    private final String url;
    private String data;
    private final HashSet<Page> links = new HashSet<>();
    private boolean isVisited = false;

    public Page(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Objects.equals(url, page.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    public boolean addLink(Page link) {
        // return false if link already exists
        if (links.contains(link)) {
            return false;
        }
        links.add(link);
        return true;
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public String getUrl() {
        return url;
    }
}
