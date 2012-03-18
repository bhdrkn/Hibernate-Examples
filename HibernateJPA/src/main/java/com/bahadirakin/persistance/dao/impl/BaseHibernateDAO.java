/*
*   Copyright 2012 Bahadır AKIN
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*/
package com.bahadirakin.persistance.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bahadirakin.persistance.HibernateUtil;
import com.bahadirakin.persistance.dao.IBaseDAO;
import com.bahadirakin.persistance.model.IEntity;

/**
 * Contains common operations for all Entities DAO
 * 
 * @author Bahadır AKIN
 * 
 * @param IEntity
 *            Class
 */
@SuppressWarnings("unchecked")
public abstract class BaseHibernateDAO<T extends IEntity> implements
		IBaseDAO<T> {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory
			.getLogger(BaseHibernateDAO.class);

	private HibernateUtil hibernateUtil;
	private Class<T> persistentClass;

	public BaseHibernateDAO() {
		super();
		hibernateUtil = HibernateUtil.getInstance();
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected Session getCurrentSession() {
		return hibernateUtil.getCurrentSession();
	}

	protected void synchronize() {
		hibernateUtil.synchronize();
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void save(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Entity must not be null");
		}

		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.save(entity);
			transaction.commit();
		} catch (HibernateException e) {
			LOG.error("Error while saving Entity. M: " + e.getMessage()
					+ " C: " + e.getCause());
		}
	}

	public void saveOrUpdate(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Entity must not be null");
		}

		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(entity);
			transaction.commit();
		} catch (HibernateException e) {
			LOG.error("Error while saveOrUpdate Entity. M: " + e.getMessage()
					+ " C: " + e.getCause());
		}
	}

	public void delete(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Entity Must not be Null");
		}

		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.delete(entity);
			transaction.commit();
		} catch (HibernateException e) {
			LOG.error("Error while delete Entity. M: " + e.getMessage()
					+ " C: " + e.getCause());
		}
	}

	public void detach(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Entity Must not be null");
		}

		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.evict(entity);
			transaction.commit();
		} catch (Exception e) {
			LOG.error("Error while detach Entity. M: " + e.getMessage()
					+ " C: " + e.getCause());
		}
	}

	public void refresh(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Entity Must not be null");
		}

		try {
			Session session = this.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.refresh(entity);
			transaction.commit();
		} catch (Exception e) {
			LOG.error("Error while refresh Entity. M: " + e.getMessage()
					+ " C: " + e.getCause());
		}
	}

	public T getById(Integer id, boolean synchronize) {
		T entity = null;
		try {
			if (synchronize) {
				this.synchronize();
			}
			Session session = this.getCurrentSession();
			session.beginTransaction();
			entity = (T) session.load(getPersistentClass(), id);
		} catch (Exception e) {
			LOG.error("Error while getById Entity. M: " + e.getMessage()
					+ " C: " + e.getCause());
		}
		return entity;
	}

	public List<T> getAll(boolean synchronize) {
		try {
			if (synchronize) {
				this.synchronize();
			}
			Session session = this.getCurrentSession();
			session.beginTransaction();
			List<T> list = session.createCriteria(getPersistentClass()).list();
			return list;
		} catch (Exception e) {
			LOG.error("Error while getAll Entities. M: " + e.getMessage()
					+ " C: " + e.getCause());
		}
		return null;
	}

	public T getBySql(String query, boolean synchronize) {
		T entity = null;
		try {
			if (synchronize) {
				this.synchronize();
			}
			Session session = this.getCurrentSession();
			session.beginTransaction();
			entity = (T) session.createSQLQuery(query)
					.addEntity(getPersistentClass()).uniqueResult();
		} catch (Exception e) {
			LOG.error("Error while getWithSql Entity. M: " + e.getMessage()
					+ " C: " + e.getCause() + " SQL: " + query);
		}
		return entity;
	}

	public List<T> getAllBySql(String query, boolean synchronize) {
		try {
			if (synchronize) {
				this.synchronize();
			}
			Session session = this.getCurrentSession();
			session.beginTransaction();
			return session.createSQLQuery(query)
					.addEntity(getPersistentClass()).list();
		} catch (Exception e) {
			LOG.error("Error while getAllWithSql Entities. M: "
					+ e.getMessage() + " C: " + e.getCause() + " SQL: " + query);
		}
		return null;
	}

	public void executeSQLQuery(String query) {
		try {
			Session session = this.getCurrentSession();
			session.beginTransaction();
			session.createSQLQuery(query).addEntity(getPersistentClass())
					.executeUpdate();
		} catch (Exception e) {
			LOG.error("Error while executeSQLQuery Entities. M: "
					+ e.getMessage() + " C: " + e.getCause() + " SQL: " + query);
		}
	}

	protected List<T> findAllByCriteria(boolean synchronize,
			Criterion... criterions) {
		try {
			if (synchronize) {
				this.synchronize();
			}
			Session session = this.getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(getPersistentClass());
			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}
			return criteria.list();
		} catch (Exception e) {
			LOG.error("Error while findAllByCriteria Entities. M: "
					+ e.getMessage() + " C: " + e.getCause());
		}
		return null;
	}

	protected T findByCriteria(boolean synchronize, Criterion... criterions) {
		try {
			if (synchronize) {
				this.synchronize();
			}
			Session session = this.getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(getPersistentClass());
			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}
			return (T) criteria.uniqueResult();
		} catch (Exception e) {
			LOG.error("Error while findByCriteria Entities. M: "
					+ e.getMessage() + " C: " + e.getCause());
		}
		return null;
	}

}
