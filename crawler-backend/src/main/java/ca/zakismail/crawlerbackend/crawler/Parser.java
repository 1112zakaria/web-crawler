package ca.zakismail.crawlerbackend.crawler;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Parser {
    private String url;
    private String data;
    private List<String> links;

    public Parser(String url) {
        this.url = url;
    }

    public void parse() {
        // FIXME: update to be generic? Strategy pattern?
        Document document;
        Element link, data;

        links = new ArrayList<>();

        // parse HTML page
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.debug(document.title());

        data = document.body().tagName("p");
        log.debug(data.text());
        this.data = data.text();

        link = document.select("a").first();
        while (link != null) {
            log.debug(link.attr("abs:href"));
            links.add(link.attr("abs:href"));
            link = link.nextElementSibling();
        }
    }

    public String getUrl() {
        return url;
    }

    public String getData() {
        return data;
    }

    public List<String> getLinks() {
        return links;
    }
}
