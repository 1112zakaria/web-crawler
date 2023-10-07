package ca.zakismail.crawlerbackend.crawler;


import java.util.*;

public class Crawler {
    // required: visited, adjacency list
    private HashMap<String, Page> pages;
    private Queue<Page> queue; // queue of next pages to visit
    private String rootUrl;

    public Crawler(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public void crawl() {
        Page nextPage, rootPage;

        // resets pages and queue on every call
        pages = new HashMap<>();
        queue = new LinkedList<>();
        rootPage = new Page(rootUrl);
        pages.put(rootPage.getUrl(), rootPage);
        queue.add(rootPage);

        // traverse all pages in the queue while non-empty
        while (!queue.isEmpty()) {
            // crawl page
            nextPage = queue.poll();
            crawlPage(nextPage);
        }
    }

    private void crawlPage(Page rootPage) {
        Parser parser;
        Page childPage;

        // set page to visited
        rootPage.setState(PageState.VISITED);

        // read page data
        parser = new Parser(rootPage.getUrl());
        parser.parse();

        // read page links and fetch/create child page object
        for (String linkUrl : parser.getLinks()) {
            childPage = getPage(linkUrl);
            rootPage.addLink(childPage);
            if (childPage.getState() == PageState.NEW) {
                // add non-visited child page links to queue to visit
                queue.add(childPage);
                childPage.setState(PageState.QUEUED);
            }
        }
    }

    public Set<Page> getPages() {
        return new HashSet<>(pages.values());
    }

    private Page getPage(String url) {
        Page page;
        if (pages.containsKey(url)) {
            return pages.get(url);
        }
        page = new Page(url);
        pages.put(url, page);
        return page;
    }

    public static void main(String[] args) {
        Crawler c = new Crawler("https://people.scs.carleton.ca/~davidmckenney/tinyfruits/N-0.html");
        c.crawl();
    }

}
