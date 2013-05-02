package eu.ttbox.service.user;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDbServiceTest {

	@Autowired
	DataSource dataSource;

	@Before
	public void setUp() throws Exception {
		// insert data into db
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
	}

	@After
	public void tearDown() throws Exception {
		// insert data into db
		// DatabaseOperation.DELETE_ALL.execute(getConnection(), getDataSet());
	}

	private IDatabaseConnection getConnection() throws Exception {
		// get connection
		Connection con = dataSource.getConnection();
		DatabaseMetaData databaseMetaData = con.getMetaData();
		// oracle schema name is the user name
		IDatabaseConnection connection = new DatabaseConnection(con);
		DatabaseConfig config = connection.getConfig();
		// oracle 10g
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
		// receycle bin
		// config.setFeature(DatabaseConfig.FEATURE_SKIP_ORACLE_RECYCLEBIN_TABLES,
		// Boolean.TRUE);
		return connection;
	}

	private IDataSet getDataSet() throws Exception {
		IDataSet dataSet = null;
		InputStream is = getClass().getResourceAsStream("/data/ttbox-db-export.xml");
		try {
			FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
			builder.setColumnSensing(true);
			dataSet = builder.build(is);
		} finally {
			is.close();
		}
		return dataSet;
	}

}
