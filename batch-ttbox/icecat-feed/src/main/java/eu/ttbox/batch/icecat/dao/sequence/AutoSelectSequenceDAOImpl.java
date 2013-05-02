package eu.ttbox.batch.icecat.dao.sequence;

import javax.annotation.PostConstruct;

import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("autoSelectSequenceDAO")
public class AutoSelectSequenceDAOImpl implements SequenceDAO {

	@Autowired
	@Qualifier("nextValSequenceDAO")
	SequenceDAO nextValSequenceDAO;

	@Autowired
	@Qualifier("insertTableSequenceDAO")
	SequenceDAO insertTableSequenceDAO;

	@Autowired
	@Qualifier("icecatSessionFactory")
	SessionFactory sessionFactory;

	private SequenceDAO sequenceDAO;

	public AutoSelectSequenceDAOImpl() {
		super();
	}

	@PostConstruct
	protected void initDao() throws Exception {
		final Dialect dialect = ((SessionFactoryImplementor) sessionFactory).getDialect();
		try {
			dialect.getSequenceNextValString("dummySeqName");
			this.sequenceDAO = nextValSequenceDAO;
		} catch (MappingException me) {
			this.sequenceDAO = insertTableSequenceDAO;
		}
	}

	@Override
	public Integer getSidSequenceNextVal() {
		return this.sequenceDAO.getSidSequenceNextVal();
	}

	@Override
	public Integer getTidSequenceNextVal() {
		return this.sequenceDAO.getTidSequenceNextVal();
	}

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
}
