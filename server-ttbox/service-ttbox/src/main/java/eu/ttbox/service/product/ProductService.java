package eu.ttbox.service.product;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import eu.ttbox.model.product.Product;

@Service("productService")
@Transactional 
public class ProductService  {

    
    Logger log = LoggerFactory.getLogger(getClass());

    
    @PersistenceContext
    EntityManager entityManager;
    
    @Transactional(propagation = Propagation.REQUIRED) 
    public void persist(Product entity) {
    	entityManager.persist(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void merge(Product entity) {
    	entityManager.merge(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(Product entity) {
    	entityManager.remove(entity);
    } 

    @Transactional(propagation = Propagation.REQUIRED)
    public void findById(Long id) {
    	entityManager.find(Product.class, id);
    } 
    
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Product> getAll() {
		Query q = entityManager.createQuery("Select e from Product e"); 
		return q.getResultList(); 
	}
 
	
}
