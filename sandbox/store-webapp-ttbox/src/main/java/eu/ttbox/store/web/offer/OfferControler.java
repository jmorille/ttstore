package eu.ttbox.store.web.offer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.ttbox.model.catalog.Offer;

@Controller
public class OfferControler {

	@RequestMapping("p-{offerId}")
	public String execute(@PathVariable Integer offerId, ModelMap model) {
		Offer offer = new Offer();
		offer.setId(offerId);
		model.addAttribute("offerId", "offerId="+offerId); 
		model.addAttribute("user", "OfferControler"); 
		return "layout:offer";
	}
	
}
