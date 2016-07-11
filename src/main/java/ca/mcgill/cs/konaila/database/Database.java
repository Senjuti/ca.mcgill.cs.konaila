package ca.mcgill.cs.konaila.database;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import org.apache.commons.io.FileUtils;

import ca.mcgill.cs.konaila.Source;
import ca.mcgill.cs.konaila.chopper.SelectionUnit;
import ca.mcgill.cs.konaila.data.DatabasePopulateStackOverflowCodeFragments;
import ca.mcgill.cs.konaila.database.androidGuide.DatabasePopulateAndroid;
import ca.mcgill.cs.konaila.database.eclipse.DatabasePopulateEclipse;
import ca.mcgill.cs.konaila.database.eclipse.DatabasePopulateEclipseGoldStandard;
import ca.mcgill.cs.konaila.selection.DatabaseJoinEclipseLinesWithSelectionUnits;
import ca.mcgill.cs.konaila.selection.PopulateConfigurationBasedPredictions;
import ca.mcgill.cs.konaila.selection.SelectConfigurationBasedSignature;
import ca.mcgill.cs.konaila.selection.SelectRuleBasedStatement;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseCodeFragmentCategory;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseCodeFragmentCategoryFeatures;
import ca.mcgill.cs.konaila.selection.features.Property;
import ca.mcgill.cs.konaila.selection.optimization.PopulateKnapsackItemsForContext;
import ca.mcgill.cs.konaila.summarize.SummarizeAndPopulateDatabase;

public class Database {

	protected Connection conn;
	private static Database instance;

	private static String databaseString;

	private static ThreadLocal<Database> store = new ThreadLocal<>();

	public static void setDatabaseString(String databaseString) {
		Database.databaseString = databaseString;
	}

	public static synchronized Database getInstance() throws SQLException,
			IOException {
		Database db = instance; //store.get();
		if (db == null) {
			db = new Database();
			instance = db; //store.set(db);
		}
		return db;
	}

	public Connection getConnection() {
		return conn;
	}
	
	private Connection wrapConnection(final Connection connection){

		return new Connection() {

			public <T> T unwrap(Class<T> iface) throws SQLException {
				return connection.unwrap(iface);
			}

			public boolean isWrapperFor(Class<?> iface) throws SQLException {
				return connection.isWrapperFor(iface);
			}

			public Statement createStatement() throws SQLException {
				return connection.createStatement();
			}

			public PreparedStatement prepareStatement(String sql)
					throws SQLException {
				return connection.prepareStatement(sql);
			}

			public CallableStatement prepareCall(String sql)
					throws SQLException {
				return connection.prepareCall(sql);
			}

			public String nativeSQL(String sql) throws SQLException {
				return connection.nativeSQL(sql);
			}

			public void setAutoCommit(boolean autoCommit) throws SQLException {
				connection.setAutoCommit(autoCommit);
			}

			public boolean getAutoCommit() throws SQLException {
				return connection.getAutoCommit();
			}

			public void commit() throws SQLException {
				synchronized(Database.class){
					connection.commit();
				}
			}

			public void rollback() throws SQLException {
				synchronized(Database.class){
				connection.rollback();
				}
				}

			public void close() throws SQLException {
				connection.close();
			}

			public boolean isClosed() throws SQLException {
				return connection.isClosed();
			}

			public DatabaseMetaData getMetaData() throws SQLException {
				return connection.getMetaData();
			}

			public void setReadOnly(boolean readOnly) throws SQLException {
				connection.setReadOnly(readOnly);
			}

			public boolean isReadOnly() throws SQLException {
				return connection.isReadOnly();
			}

			public void setCatalog(String catalog) throws SQLException {
				connection.setCatalog(catalog);
			}

			public String getCatalog() throws SQLException {
				return connection.getCatalog();
			}

			public void setTransactionIsolation(int level) throws SQLException {
				connection.setTransactionIsolation(level);
			}

			public int getTransactionIsolation() throws SQLException {
				return connection.getTransactionIsolation();
			}

			public SQLWarning getWarnings() throws SQLException {
				return connection.getWarnings();
			}

			public void clearWarnings() throws SQLException {
				connection.clearWarnings();
			}

			public Statement createStatement(int resultSetType,
					int resultSetConcurrency) throws SQLException {
				synchronized(Database.class){
				return connection
						.createStatement(resultSetType, resultSetConcurrency);
				}
				}

			public PreparedStatement prepareStatement(String sql,
					int resultSetType, int resultSetConcurrency)
					throws SQLException {
				synchronized(Database.class){
				return connection.prepareStatement(sql, resultSetType,
						resultSetConcurrency);
				}
				}

			public CallableStatement prepareCall(String sql, int resultSetType,
					int resultSetConcurrency) throws SQLException {
				return connection.prepareCall(sql, resultSetType,
						resultSetConcurrency);
			}

			public Map<String, Class<?>> getTypeMap() throws SQLException {
				return connection.getTypeMap();
			}

			public void setTypeMap(Map<String, Class<?>> map)
					throws SQLException {
				connection.setTypeMap(map);
			}

			public void setHoldability(int holdability) throws SQLException {
				connection.setHoldability(holdability);
			}

			public int getHoldability() throws SQLException {
				return connection.getHoldability();
			}

			public Savepoint setSavepoint() throws SQLException {
				return connection.setSavepoint();
			}

			public Savepoint setSavepoint(String name) throws SQLException {
				return connection.setSavepoint(name);
			}

			public void rollback(Savepoint savepoint) throws SQLException {
				connection.rollback(savepoint);
			}

			public void releaseSavepoint(Savepoint savepoint)
					throws SQLException {
				connection.releaseSavepoint(savepoint);
			}

			public Statement createStatement(int resultSetType,
					int resultSetConcurrency, int resultSetHoldability)
					throws SQLException {
				synchronized(Database.class){
				return connection.createStatement(resultSetType,
						resultSetConcurrency, resultSetHoldability);
				}			}

			public PreparedStatement prepareStatement(String sql,
					int resultSetType, int resultSetConcurrency,
					int resultSetHoldability) throws SQLException {
				synchronized(Database.class){
				return connection.prepareStatement(sql, resultSetType,
						resultSetConcurrency, resultSetHoldability);
				}
				}

			public CallableStatement prepareCall(String sql, int resultSetType,
					int resultSetConcurrency, int resultSetHoldability)
					throws SQLException {
				synchronized(Database.class){
				return connection.prepareCall(sql, resultSetType,
						resultSetConcurrency, resultSetHoldability);
				}			}

			public PreparedStatement prepareStatement(String sql,
					int autoGeneratedKeys) throws SQLException {
				synchronized(Database.class){
				return connection.prepareStatement(sql, autoGeneratedKeys);
				}			}

			public PreparedStatement prepareStatement(String sql,
					int[] columnIndexes) throws SQLException {
				synchronized(Database.class){
		return connection.prepareStatement(sql, columnIndexes);
				}			}

			public PreparedStatement prepareStatement(String sql,
					String[] columnNames) throws SQLException {
				return connection.prepareStatement(sql, columnNames);
			}

			public Clob createClob() throws SQLException {
				return connection.createClob();
			}

			public Blob createBlob() throws SQLException {
				return connection.createBlob();
			}

			public NClob createNClob() throws SQLException {
				return connection.createNClob();
			}

			public SQLXML createSQLXML() throws SQLException {
				return connection.createSQLXML();
			}

			public boolean isValid(int timeout) throws SQLException {
				return connection.isValid(timeout);
			}

			public void setClientInfo(String name, String value)
					throws SQLClientInfoException {
				connection.setClientInfo(name, value);
			}

			public void setClientInfo(Properties properties)
					throws SQLClientInfoException {
				connection.setClientInfo(properties);
			}

			public String getClientInfo(String name) throws SQLException {
				return connection.getClientInfo(name);
			}

			public Properties getClientInfo() throws SQLException {
				return connection.getClientInfo();
			}

			public Array createArrayOf(String typeName, Object[] elements)
					throws SQLException {
				return connection.createArrayOf(typeName, elements);
			}

			public Struct createStruct(String typeName, Object[] attributes)
					throws SQLException {
				return connection.createStruct(typeName, attributes);
			}

			public void setSchema(String schema) throws SQLException {
				connection.setSchema(schema);
			}

			public String getSchema() throws SQLException {
				return connection.getSchema();
			}

			public void abort(Executor executor) throws SQLException {
				synchronized(Database.class){
				connection.abort(executor);
				}			}

			public void setNetworkTimeout(Executor executor, int milliseconds)
					throws SQLException {
				connection.setNetworkTimeout(executor, milliseconds);
			}

			public int getNetworkTimeout() throws SQLException {
				return connection.getNetworkTimeout();
			}
		};
	}

	public void initConnection(String database) throws SQLException,
			IOException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new Error(e);
		}
//		conn = wrapConnection(DriverManager.getConnection(database));
		setDatabaseString(database);
		conn = DriverManager.getConnection(database);
	}

	public void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	public void initDB(String sqlFile) throws SQLException, IOException {
		conn.setAutoCommit(false);
		conn.setReadOnly(false);
		Statement stat = conn.createStatement();

		String[] commands = FileUtils.readFileToString(new File(sqlFile))
				.split("\n");

		for (String command : commands) {
			stat.executeUpdate(command);
		}
		conn.commit();
		stat.close();
	}

	public void addUnits(Collection<SelectionUnit> units) throws SQLException,
			IOException {

		System.out.println("Adding units...");

		DatabaseAddUnits.addUnits(conn, units);
		DatabaseEnclosingRelationships.populateEnclosingRelationships(conn);
		DatabaseEnclosingRelationships
				.populateEnclosingRelationshipsForCallUnits(conn);

	}

	public void addEllipses(Collection<SelectionUnit> ellipses)
			throws SQLException, IOException {

		System.out.println("Adding Ellipses...");

		DatabaseAddUnits.addEllipses(conn, ellipses);
	}

	public void addProperties(Collection<Property> units) throws SQLException,
			IOException {
		DatabaseAddProperties.addProperties(conn, units);
	}

	public void consolidateFeatures() throws SQLException, IOException {

		System.out.println("Populating features...");

		conn.setAutoCommit(false);

		DatabaseFeatures.populateMatchedProperties(conn);
		DatabaseFeatures.populateNodeType(conn);
		DatabaseFeatures.consolidateCallFeatures(conn);
		DatabaseFeatures.consolidateLocalVariableDeclarationFeatures(conn);
		DatabaseFeatures.consolidateConstructorFeatures(conn);
		DatabaseFeatures.consolidateComments(conn);
		DatabaseFeatures.cleanup(conn);

		DatabaseFeatures.populateFeatureInfo(conn);

		DatabaseFeaturesDataflow.populate(conn, 0);

		conn.commit();
	}

	public void consolidateFeaturesDataflow(int cid, int server) throws SQLException,
			IOException {
		conn.setAutoCommit(false);
		DatabaseFeaturesDataflow.populate(conn, cid, server);
		conn.commit();
	}

	public void consolidateFeaturesExceptDataFlow() throws SQLException,
			IOException {

		System.out.println("Populating features...");

		conn.setAutoCommit(false);

		DatabaseFeatures.populateMatchedProperties(conn);
		DatabaseFeatures.populateNodeType(conn);
		DatabaseFeatures.consolidateCallFeatures(conn);
		DatabaseFeatures.consolidateLocalVariableDeclarationFeatures(conn);
		DatabaseFeatures.consolidateConstructorFeatures(conn);
		DatabaseFeatures.consolidateComments(conn);
		DatabaseFeatures.cleanup(conn);

		DatabaseFeatures.populateFeatureInfo(conn);

		conn.commit();
	}

	public void createTrainingData() throws SQLException, IOException {

		System.out.println("Training data...");

		DatabaseJoinEclipseLinesWithSelectionUnits.joinGoldStandard(conn);
	}

	public void populateAndroid() throws SQLException, IOException {

		System.out.println("Populate Android...");

		DatabasePopulateAndroid.populate(conn);
	}

	public void populateEclipse() throws SQLException, IOException {

		System.out.println("Populate Eclipse...");

		DatabasePopulateEclipse.populate(conn);
		DatabasePopulateEclipseGoldStandard.populate(conn);
	}

	public void populateStackOverflow() throws SQLException, IOException {

		System.out.println("Populate StackOverflow...");

		DatabasePopulateStackOverflowCodeFragments.populate(conn);
	}

	public List<Integer> getStudyCids() throws SQLException, IOException {
		return DatabasePopulateStudy.getSortedCidsForStudy(conn);
	}

	public String getCodeFragment(int cid) throws SQLException, IOException {
		return DatabaseGetCodeFragments.getCodeFragment(conn, cid);
	}

	public String getQuery(int cid) throws SQLException, IOException {
		return DatabaseGetCodeFragments.getQuery(conn, cid);
	}

	public Map<Integer, String> getCodeFragments(String source)
			throws SQLException, IOException {
		return DatabaseGetCodeFragments.getCodeFragments(conn, source);
	}

	public Map<Integer, String> getQueries(String source) throws SQLException,
			IOException {
		return DatabaseGetCodeFragments.getQueries(conn, source);
	}

	public void writeParsingOK(int cid, String parseStructure)
			throws SQLException, IOException {
		DatabaseChopperStats.writeToDb(conn, cid, parseStructure);
	}

	public void writeNoTree(int cid) throws SQLException, IOException {
		DatabaseChopperStats.writeToDb(conn, cid, "cannot parse");
	}

	public void writeTooShort(int cid) throws SQLException, IOException {
		DatabaseChopperStats
				.writeToDb(conn, cid, "original fragment too short");
	}

	public void rebuild() throws SQLException, IOException {
		System.out.println("Rebuilding database...");

		DatabaseRebuild.rebuild();
	}

	public void populateEclipsePredictions() throws SQLException, IOException {

		System.out.println("Populating predictions...");
		// DatabaseEclipsePopulatePredictions.populatePredictions(conn);
		// DatabaseJoinEclipseLinesWithSelectionUnits.joinPredictions(conn);
		SelectRuleBasedStatement.populateScores(conn);
		SelectConfigurationBasedSignature.populateScores(conn, Source.Eclipse);
	}

	public void populateCodeFragmentCategorization() throws SQLException,
			IOException {

		System.out.println("Populating code fragment categorization...");
		DatabaseCodeFragmentCategoryFeatures.populate(conn);
		DatabaseCodeFragmentCategory.classifyCodeFragments(conn);
	}

	public void populateConfigurationBasedPredictions() throws SQLException,
			IOException {

		System.out.println("Populating configuration based predictions...");

		PopulateConfigurationBasedPredictions.populateScores(conn);
		PopulateKnapsackItemsForContext.populateItems(conn);
	}

	public void summarize() throws SQLException, IOException {

		System.out.println("Summarize...");

		SummarizeAndPopulateDatabase.summarizeMultipleSettingsInDatabase(conn);
	}

	public void summarize(int cid) throws SQLException, IOException {

		System.out.println("Summarization " + cid + "...");

		SummarizeAndPopulateDatabase.summarizeMultipleSettingsInDatabase(conn,
				cid);
	}
}
