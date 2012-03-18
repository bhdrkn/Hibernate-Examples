package com.bahadirakin.persistance;

import java.io.File;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;

/**
 * All DAO test must be implement this interface.
 * 
 * <p>
 * This interface uses DBUNIT to test DAOs.
 * </p>
 * 
 * @author Bahadır AKIN
 * 
 */
public interface IBaseDAOTest {

	/**
	 * Connection provided by JPA (Hibernate etc.)
	 * 
	 * @return Connection to the Database for DBUNIT
	 * @throws Exception
	 */
	IDatabaseConnection getConnection() throws Exception;

	/**
	 * XML Datasets for DBUNIT.
	 * 
	 * <p>
	 * There are some rule for Dataset xml files. Developers may also needs a
	 * dtd file. <a href="http://www.dbunit.org/faq.html">More Information</a>
	 * </p>
	 * 
	 * @return
	 * @throws Exception
	 */
	IDataSet getDataSet() throws Exception;

	/**
	 * Writing DTD files is tough. Developer can extract DTD files from
	 * connection.
	 * 
	 * @throws Exception
	 */
	void extractDTD(String outputFilePath) throws Exception;

	/**
	 * Each test must call this method at their SetUp.
	 * 
	 * <p>
	 * If this method not called in Setup(Before) an Exception may occurred
	 * </p>
	 * 
	 * @throws Exception
	 */
	void initialize() throws Exception;

	/**
	 * Each test must call this method at ThearDown(After)
	 */
	void destroy();

	/**
	 * File object for FlatXmlDataSet.xml file.
	 * 
	 * <p>
	 * There is some kind of error while creating *.xml and *.dtd files. The i
	 * and ı letters are like the letters in turkish. You must use I all the
	 * time. If you use i, the letter may turn into İ and the application cannot
	 * find columns or tables. Because none of tables and columns starts with İ
	 * or ı.
	 * </p>
	 * 
	 * <p>
	 * Table and Column names are not Case Sensitive. But you must use same
	 * notation in *.dtd and *.xml file. On the other hand there will be some
	 * error if lower case letters used.
	 * </p>
	 * 
	 * <p>
	 * <b>The Best practice, USE UPPER CASE IN COLUMN AND TABLE names all the
	 * time.</b>
	 * </p>
	 * 
	 * @return Dataset.xml file object
	 */
	File getDataSetFile();
}
