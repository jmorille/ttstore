package eu.ttbox.batch.icecat.dao.sequence;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("insertTableSequenceDAO")
@Scope("singleton")
public class InsertTableSequenceDAOImpl extends NamedParameterJdbcDaoSupport implements SequenceDAO {

	public InsertTableSequenceDAOImpl() {
		super();
	}

	@Autowired
	public InsertTableSequenceDAOImpl(@Qualifier("icecatDS") DataSource sessionFactory) {
		super.setDataSource(sessionFactory);
	}

	public Integer getSidSequenceNextVal() {
		String sequenceSqlName = "icecat.SID_INDEX";
		return getSequenceNextVal(sequenceSqlName);
	}

	public Integer getTidSequenceNextVal() {
		String sequenceSqlName = "icecat.TID_INDEX";
		return getSequenceNextVal(sequenceSqlName);
	}

	private Integer getSequenceNextVal(String sequenceSqlName) {
		String QUERY_INSERT_CARD = "INSERT INTO " + sequenceSqlName + " (dummy) VALUES (:dummy)";

		// this method because we need to retrieve technical id (sequence or
		// increment automatic)
		Map<String, Object> sqlParamMap = new HashMap<String, Object>();
		sqlParamMap.put("dummy", Integer.valueOf(0));
		SqlParameterSource fileParameters = new MapSqlParameterSource(sqlParamMap);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getNamedParameterJdbcTemplate().update(QUERY_INSERT_CARD, fileParameters, keyHolder);

		Integer seqId = Integer.valueOf(keyHolder.getKey().intValue());

		return seqId;
	}

}
