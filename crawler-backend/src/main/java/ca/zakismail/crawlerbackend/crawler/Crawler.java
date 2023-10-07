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

    private void crawlPage(Page page) {
        // set page to visited
        page.setVisited(true);

        // read page data

        // read page links and create new page object

        // add non-visited page links to queue to visit
    }

    public List<Page> getPages() {
        return new ArrayList<>(pages.values());
    }

    private Page getPage(String url) {
        Page page = new Page(url);
        if (pages.containsKey(url)) {
            return pages.get(url);
        }
        pages.put(url, page);
        return page;
    }

}
