package eu.ttbox.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Strings;

import eu.ttbox.model.supplier.Supplier;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class PersistenceMappingTest {

	Logger log = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	DataSource dataSource;

	@Test
	public void testMappingPersonne() throws Exception {
		Query query = entityManager.createQuery("from Supplier");
		List<Supplier> result = query.getResultList();
		Assert.assertNotNull(result);
		Assert.assertEquals(0, result.size());
		// Check Table less than 32
		List<Map<String, String>> tables = printSqlTableName();
		for (Map<String, String> table : tables) {
			String tableName = table.get("TABLE_NAME");
			log.info("Generated table name {} \t\t\t\t\t with name size {} ", tableName,
					tableName.length());
			// Check that every table name was less than 32 character length
			// for the Oracle compatibility
			Assert.assertEquals(true, 32 >= tableName.length());
		}
		log.info("Number of table generated is {} tables", tables.size());
		// Check Column name less than 32
		List<Map<String, String>> columns =	printSqlTableNameColumn(null);
		for (Map<String, String> column : columns) {
			String columName = column.get( "COLUMN_NAME");
			Assert.assertEquals(true, 32 >= columName.length());
			log.debug("Column {}", column);
		}
	}

	/**
	 * @see http://www.h2database.com/html/grammar.html#information_schema
	 */
	private List<Map<String, String>> printSqlTableName() throws SQLException {
		String sql = "SELECT * FROM INFORMATION_SCHEMA.TABLES where  TABLE_TYPE='TABLE'";
		// HSQLDB String sql = "SELECT * FROM INFORMATION_SCHEMA.SYSTEM_TABLES where  TABLE_TYPE='TABLE'";
		Connection conn = dataSource.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<Map<String, String>> results = extractResultSeAsMap(rs);
			rs.close();
			stmt.close();
			return results;
		} finally {
			conn.close();
		}

	}

	private List<Map<String, String>> extractResultSeAsMap(ResultSet rs)
			throws SQLException {
		List<Map<String, String>> results = new ArrayList<Map<String, String>>();
		ResultSetMetaData rsMetaData = rs.getMetaData();
		while (rs.next()) {
			Map<String, String> line = new HashMap<String, String>();
			for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
				String columnName = rsMetaData.getColumnName(i);
				String lineValue = rs.getString(i);
				line.put(columnName, lineValue);
			}
			results.add(line);
		}
		return results;
	}

	/**
	 * @see http://www.h2database.com/html/grammar.html#information_schema
	 */
	private List<Map<String, String>> printSqlTableNameColumn(String tableName)
			throws SQLException {
		 
		String sql = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS where TABLE_NAME not like 'SYSTEM_%' ";
//HSQLDB		String sql = "SELECT * FROM INFORMATION_SCHEMA.SYSTEM_COLUMNS where TABLE_NAME not like 'SYSTEM_%' ";
		boolean isTableName = tableName!=null;
		if (isTableName) {
			sql += " and  TABLE_NAME=? ";
		}
		sql += " order by TABLE_NAME";
		// Connection
		Connection conn = dataSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		if (isTableName) {
			stmt.setString(1, tableName);
		}
		ResultSet rs = stmt.executeQuery();
		List<Map<String, String>> results = extractResultSeAsMap(rs);
		rs.close();
		stmt.close();
		conn.close();
		return results;
	}

	/**
	 * @see http://www.h2database.com/html/grammar.html#information_schema
	 */
	public List<Map<String, Object>> printSqlConstraintHsqlDb(
			String constraintName) {
		boolean isConstraintName = !Strings.isNullOrEmpty(constraintName);
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		try {
			// String fkName =
			String sql = "SELECT * FROM INFORMATION_SCHEMA.SYSTEM_CROSSREFERENCE";
			if (isConstraintName) {
				sql += " where FK_NAME=?";
			}
			Connection conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			if (isConstraintName) {
				stmt.setString(1, constraintName);
			}
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData rsMetaData = rs.getMetaData();
			while (rs.next()) {
				Map<String, Object> line = new HashMap<String, Object>();
				for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
					String columnName = rsMetaData.getColumnName(i);
					Object lineValue = rs.getObject(i);
					line.put(columnName, lineValue);
					// System.err.println(columnName + " = " + line);
				}
				results.add(line);
				log.info("Line {} = {}", line.get("FK_NAME"), line);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e1) {
			log.error("Erreor reading Table MetaData : " + e1.getMessage(), e1);
		}
		return results;
	}

}
