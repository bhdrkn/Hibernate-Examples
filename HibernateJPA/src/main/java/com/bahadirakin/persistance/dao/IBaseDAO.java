package com.bahadirakin.persistance.dao;

import java.util.List;

import com.bahadirakin.persistance.model.IEntity;

/**
 * Base Data Access Interface
 * 
 * @author Bahadır AKIN
 * 
 * @param IEntity
 *            Class
 */
public interface IBaseDAO<T extends IEntity> {

	/**
	 * Insert a new entity;
	 * 
	 * @param entity
	 *            - detached entity object
	 */
	public void save(T entity);

	/**
	 * Inserts a new detached entity or updates if entity already exists
	 * 
	 * @param entity
	 *            - entity object to be inserted or updated
	 */
	public void saveOrUpdate(T entity);

	/**
	 * Deletes entity from persistence store, AKA. Database
	 * 
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * Remove this instance from session cache
	 * 
	 * @param entity
	 */
	public void detach(T entity);

	/**
	 * Reloads an entity from persistence store
	 * 
	 * @param entity
	 */
	public void refresh(T entity);

	/**
	 * 
	 * Gets entity by its Id.
	 * 
	 * <p>
	 * If there is other access to persistence store make synchronize
	 * <code>true</code>. Otherwise requested object may not be found.
	 * </p>
	 * 
	 * @param id
	 *            Id of entity
	 * @param synchronize
	 *            True if there is other access to persistence store
	 * @return
	 */
	T getById(Integer id, boolean synchronize);

	/**
	 * Gets all the entities from persistence store.
	 * 
	 * <p>
	 * If there is other access to persistence store make synchronize
	 * <code>true</code>. Otherwise some of entities may not be found.
	 * </p>
	 * 
	 * @param synchronize
	 *            True if there is other access to persistence store
	 * @return
	 */
	List<T> getAll(boolean synchronize);

	/**
	 * Gets an Entity by executing given SQL query.
	 * 
	 * <p>
	 * Returns and Runs Unique Results
	 * </p>
	 * 
	 * @param query
	 *            SQL Query
	 * @param synchronize
	 *            True if there is other access to persistence store
	 * @return Unique Result
	 */
	T getBySql(String query, boolean synchronize);

	/**
	 * Gets All Entities by executing given SQL query.
	 * 
	 * <p>
	 * Returns and Runs list;
	 * </p>
	 * 
	 * @param query
	 *            SQL Query
	 * @param synchronize
	 *            True if there is other access to persistence store
	 * @return List of Result
	 */
	List<T> getAllBySql(String query, boolean synchronize);

	/**
	 * Executes given Sql query.
	 * 
	 * <p>
	 * Use with DELETE and UPDATE
	 * </p>
	 * 
	 * @param query
	 */
	void executeSQLQuery(String query);
}
