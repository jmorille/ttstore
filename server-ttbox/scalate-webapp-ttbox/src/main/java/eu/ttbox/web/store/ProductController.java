package eu.ttbox.web.store;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.ttbox.model.product.Product;

@Controller
public class ProductController {

	Logger log = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	EntityManager entityManager;

	@Transactional(readOnly = true)
	@RequestMapping(value = "/store/p-{productId}", method = RequestMethod.GET)
	public String product(@PathVariable Long productId, Model model) {
		log.debug("Display product page {}", productId);
		Product product = entityManager.find(Product.class, productId);
		if (product != null) {
			model.addAttribute("product", product);
		}
		return "/WEB-INF/scalate/store/product.scaml";
	}
}
