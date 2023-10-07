package ca.zakismail.crawlerbackend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
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

    public String displayPage() {
        return null;
    }
}
