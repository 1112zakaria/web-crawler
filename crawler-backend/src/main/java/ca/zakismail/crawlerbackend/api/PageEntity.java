package ca.zakismail.crawlerbackend.api;

import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@RequiredArgsConstructor
public class PageEntity {
    @GeneratedValue @Id
    private Long id;
    private String url;
    private String data;

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
}
