package eu.ttbox.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

	Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/helloWorld")
    public ModelAndView helloWorld() {
		log.info("------------------------- Welcome handler");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("helloWorld");
        mav.addObject("message", "Hello World!");
        return mav;
    }
	
}
