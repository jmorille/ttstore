package eu.ttbox.service.order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.granite.messaging.amf.io.util.externalizer.annotation.IgnoredProperty;
import org.granite.messaging.service.annotations.RemoteDestination;
import org.granite.tide.data.DataEnabled;
import org.granite.tide.data.DataEnabled.PublishMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import eu.ttbox.core.listener.ObserveAllPublishAll;
import eu.ttbox.model.order.Order;

@Service("orderService")
@Transactional 
@RemoteDestination(id = "orderService", source = "orderService",  channel = "graniteamf")
@DataEnabled(topic = "dataTopic", params = ObserveAllPublishAll.class, publish = PublishMode.ON_SUCCESS)
public class OrderService {

    @IgnoredProperty
    Logger log = LoggerFactory.getLogger(getClass());

    @IgnoredProperty
    @PersistenceContext
    EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRED)
    public void add(Order order) {
        entityManager.persist(order);
    }

}
