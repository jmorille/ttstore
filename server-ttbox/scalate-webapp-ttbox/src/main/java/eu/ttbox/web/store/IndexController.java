package eu.ttbox.web.store;

import eu.ttbox.model.ui.store.WebPage;
import eu.ttbox.model.ui.store.WebSite;
import eu.ttbox.model.ui.store.header.WebHeaderLink;
import eu.ttbox.model.ui.store.header.WebHeaderMeta;
import eu.ttbox.service.security.WebSiteSelectorFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class IndexController {

    Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/store/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {
        WebSite webSite =  (WebSite)request.getAttribute(WebSiteSelectorFilter.WEBSITE_KEY);
        if (webSite!=null) {
            model.addAttribute("webSite", webSite);
            List<WebPage> pages =  webSite.getPages();
            model.addAttribute("webPage", pages.get(0));
            log.debug("index retrieve model {}", model);
        }
       // return "render:/WEB-INF/scalate/store/index.scaml";
     //   return "layout:store/index";
//        return "layout:store/index";
        return "store/index";
    }

    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public String content() {
        return "content.scaml";
    }

    @RequestMapping(value = "/content2", method = RequestMethod.GET)
    public String contentScp() {
        return "/WEB-INF/scala/content.ssp";
    }

}
