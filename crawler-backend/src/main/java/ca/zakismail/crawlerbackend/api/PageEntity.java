package ca.zakismail.crawlerbackend.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Properties;

@Entity
@RequiredArgsConstructor
public class PageEntity {
    @GeneratedValue @Id
    private Long id;
    private String url;
    private String data;
    private int referenceCount = 0;

    public PageEntity(String url, String data) {
        this.url = url;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void incrementReferenceCount() {
        this.referenceCount++;
    }

    public String getUrl() {
        return url;
    }

    public String getData() {
        return data;
    }

    public String getPageEntityLink() {
        // FIXME: this should be done programmatically, base url
        String link = "localhost:" + 4000 + "/page/" + id;
        return link;
    }
}
