package ca.zakismail.crawlerbackend.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class PageController {

    private PageService pageService;

    @Autowired
    public void setPageService(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping()
    public String listAllPages(Model model) {
        List<PageEntity> pages;
        pages = pageService.getAllPages();
        model.addAttribute("pages", pages);
        return "pagesList";
    }

    @GetMapping("/page/{id}")
    public String getPageInfo(Model model, @PathVariable Long id) {
        PageEntity page;
        List<PageEntity> linkedPages;

        page = pageService.getPage(id);
        if (page == null) {
            log.debug("page does not exist");
            return "error";
        }
        log.debug("page exists");
        linkedPages = pageService.getAllLinkedPages(id);

        model.addAttribute("page", page);
        model.addAttribute("linkedPages", linkedPages);
        return "pageInfo";
    }

    public String displayPage() {
        return null;
    }
}
