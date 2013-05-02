package eu.ttbox.service.order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import eu.ttbox.model.order.Order;

@Service("orderService")
@Transactional  
public class OrderService {

    
    Logger log = LoggerFactory.getLogger(getClass());

    
    @PersistenceContext
    EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Order order) {
        entityManager.persist(order);
    }

}
