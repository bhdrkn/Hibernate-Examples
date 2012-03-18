package com.bahadirakin.persistance;

import java.io.FileOutputStream;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;

/**
 * {@link IBaseDAOTest} implemented for using with Hibernate and HSQLDB
 * 
 * 
 * @author Bahadır AKIN
 * 
 */
public abstract class BaseDAOTestHibernateImpl implements IBaseDAOTest {

	/**
	 * Hibernate {@link Session} for DBUNIT insert and clean operations
	 */
	private Session session;
	/**
	 * Hibernate {@link Transaction} for DBUNIT insert and clean operations
	 */
	private Transaction transaction;
	/**
	 * The Test is initialized or not
	 */
	private boolean initialized = false;
	
	@Before
	public void setUp() throws Exception {
		initialize();
	}

	@After
	public void tearDown() {
		destroy();
	}

	public void initialize() throws Exception {
		session = getSessionFactory().openSession();
		transaction = session.beginTransaction();
		initialized = true;
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
		transaction.commit();
		transaction.begin();
	}

	public void destroy() {
		transaction.commit();
		session.close();
		initialized = false;
	}

	@SuppressWarnings("deprecation")
	public IDatabaseConnection getConnection() throws Exception {
		if (!initialized)
			throw new Exception("Initialize method must be called during SetUp(Before)");
		return new DatabaseConnection(session.connection());
	}

	public IDataSet getDataSet() throws Exception {
		return new FlatXmlDataFileLoader().getBuilder().build(getDataSetFile());
	}

	public void extractDTD(String outputFilePath) throws Exception {
		FlatDtdDataSet.write(getConnection().createDataSet(),
				new FileOutputStream(outputFilePath));
	}

	/**
	 * Providing Hibernate {@link SessionFactory} may be differ from test to
	 * test.
	 * 
	 * @return
	 */
	public abstract SessionFactory getSessionFactory();

}
