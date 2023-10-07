package ca.zakismail.crawlerbackend.api;

import ca.zakismail.crawlerbackend.crawler.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PageService {

    private PageRepository pageRepository;
    private LinkRepository linkRepository;

    @Autowired
    public void setPageRepository(PageRepository repository) {
        this.pageRepository = repository;
    }

    @Autowired
    public void setLinkRepository(LinkRepository repository) {
        this.linkRepository = repository;
    }

    public PageEntity addPage(Page page) {
        // Update page's data if it exists or create new page
        PageEntity pageEntity;

        pageEntity = pageRepository.findByUrl(page.getUrl()).orElse(null);
        if (pageEntity == null) {
            // page does not exist
            pageEntity = new PageEntity(page.getUrl(), page.getData());
        } else {
            // page exists, update data
            pageEntity.setData(page.getData());
        }
        pageRepository.save(pageEntity);
        return pageEntity;
    }

    public PageEntity getPage(Page page) {
        // FIXME: this might not be very optimal...
        PageEntity pageEntity;
        // search page by name
        pageEntity = pageRepository.findByUrl(page.getUrl()).orElse(null);
        // if null, create new page entity w/ blank data?
        if (pageEntity == null) {
            pageEntity = addPage(page);
        }
        return pageEntity;
    }

    public LinkEntity addLink(Page rootPage, Page childPage) {
        PageEntity rootEntity, childEntity;
        LinkEntity linkEntity;

        // Get root and child PageEntity objects
        rootEntity = getPage(rootPage);
        childEntity = getPage(childPage);

        // increment page reference count
        incrementPageReferenceCount(childEntity);

        // save link using IDs to DB
        linkEntity = new LinkEntity(rootEntity.getId(), childEntity.getId());
        linkRepository.save(linkEntity);
        return linkEntity;
    }

    private void incrementPageReferenceCount(PageEntity page) {
        page.incrementReferenceCount();
        pageRepository.save(page);
    }

    public List<PageEntity> getAllPages() {
        return (List<PageEntity>) pageRepository.findAll();
    }

}
