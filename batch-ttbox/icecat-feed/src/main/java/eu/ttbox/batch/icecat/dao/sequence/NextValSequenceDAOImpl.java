package eu.ttbox.batch.icecat.dao.sequence;

import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.Dialect;
import org.hibernate.impl.SessionFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigInteger;

@Repository("nextValSequenceDAO")
@Scope("singleton")
public class NextValSequenceDAOImpl implements SequenceDAO {

	Logger log = LoggerFactory.getLogger(getClass());

	String tidNextValQueryString;
	String sidNextValQueryString;

	@Autowired
	@Qualifier("icecatSessionFactory")
	SessionFactory sessionFactory;

	public NextValSequenceDAOImpl() {
		super();
	}

	@PostConstruct
	protected void initDao() throws Exception {

		final Dialect dialect = ((SessionFactoryImpl) sessionFactory).getDialect();
		// A BEtter way ?Dialect dialect =
		// Dialect.getDialect(getHibernateTemplate().getConfiguration().getProperties());
		try {
			// icecat.seq_sid, / sid_index_tid_seq
			this.tidNextValQueryString = dialect.getSequenceNextValString("tid_index_tid_seq");
			log.debug("Tid next val request id {}", tidNextValQueryString);
			// icecat.seq_sid / tid_index_sid_seq
			this.sidNextValQueryString = dialect.getSequenceNextValString("sid_index_sid_seq");
			log.debug("Sid next val request id {}", sidNextValQueryString);
		} catch (MappingException e) {
			log.warn("Ignore NextVal Sequence Provider, Is not applicable for this database configuration");
		}

	}

	@Override
	public Integer getSidSequenceNextVal() {
		return getSequenceNextVal(this.sidNextValQueryString);
	}

	@Override
	public Integer getTidSequenceNextVal() {
		return getSequenceNextVal(this.tidNextValQueryString);
	}

	private Integer getSequenceNextVal(String sqlQueryString) {
		Query query = getCurrentSession().createSQLQuery(sqlQueryString);
		BigInteger res = (BigInteger) query.uniqueResult();
		return res.intValue();
	}

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

}
