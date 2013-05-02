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

import javax.sql.DataSource;

import junit.framework.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Strings;

public abstract class AbstractPersistenceMappingTest {

	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	DataSource dataSource;
	
	protected void checkTableNameSize(List<Map<String, String>> tables, int maxSizeExpected) {
		for (Map<String, String> table : tables) {
			String tableName = table.get("TABLE_NAME"); 
			Assert.assertEquals(true, maxSizeExpected >= tableName.length());
		}
	}

	protected void checkTableColumnNameSize(List<Map<String, String>> columns, int maxSizeExpected) {
		for (Map<String, String> column : columns) {
			String columName = column.get( "COLUMN_NAME");
			Assert.assertEquals(true, 32 >= columName.length());
			//log.debug("Column {}", column);
		}
	}

	
	/**
	 * @see http://www.h2database.com/html/grammar.html#information_schema
	 */
	protected List<Map<String, String>> printSqlTableName() throws SQLException {
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

	protected List<Map<String, String>> extractResultSeAsMap(ResultSet rs)
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
	protected List<Map<String, String>> printSqlTableNameColumn(String tableName)
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
	protected List<Map<String, Object>> printSqlConstraintHsqlDb(
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
