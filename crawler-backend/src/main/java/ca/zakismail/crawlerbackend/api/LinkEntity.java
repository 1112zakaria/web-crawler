package ca.zakismail.crawlerbackend.api;

import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@RequiredArgsConstructor
public class LinkEntity {
    @GeneratedValue @Id
    private Long id;
    private Long rootPageId;
    private Long childPageId;

    public LinkEntity(Long rootPageId, Long childPageId) {
        this.rootPageId = rootPageId;
        this.childPageId = childPageId;
    }

    public Long getId() {
        return id;
    }
}
