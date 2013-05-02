package eu.ttbox.core.listener;

import java.io.Serializable;

import javax.persistence.PrePersist;

import org.granite.util.UUIDUtil;

import eu.ttbox.model.IBoxUUID;


public class BoxUUIDInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

    @PrePersist
    public void onPostPersist(Object entity) {
		if (entity instanceof IBoxUUID) {
			IBoxUUID entityUUID = (IBoxUUID) entity;
			if (entityUUID.getUid() == null || entityUUID.getUid().length()<1 ) { 
				 String uid = UUIDUtil.randomUUID();
				 entityUUID. setUid(uid);
			} 
		} 
	}

	 
}
