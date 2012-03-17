package com.bahadirakin.persistance;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hibernate Utility
 * 
 * @author Bahadır AKIN
 * 
 */
public class HibernateUtil {

	private static final int SESSION_FACTORY_EXPIRE_IN_HOUR = 5;
	
	private static final Logger LOG = LoggerFactory.getLogger(HibernateUtil.class);

	/**
	 * One and Only SessionFacotry
	 */
	private SessionFactory factory = null;

	/**
	 * If there is no AccessTo sessionFactory in
	 * {@link #SESSION_FACTORY_EXPIRE_IN_HOUR} factory is re initialized.
	 */
	private Date lastAccess;

	/**
	 * Singleton Pattern
	 */
	private static HibernateUtil hibernateUtil = null;

	public static HibernateUtil getInstance() {
		if (hibernateUtil == null) {
			hibernateUtil = new HibernateUtil();
		}
		return hibernateUtil;
	}

	private HibernateUtil() {
		lastAccess = new Date(System.currentTimeMillis());
	}

	public SessionFactory getFactory() {
		try {
			if (factory == null) {
				factory = new AnnotationConfiguration().configure()
						.buildSessionFactory();
			} else if (factory.isClosed()) {
				factory = new AnnotationConfiguration().configure()
						.buildSessionFactory();
			} else if (isLastAccessTimeNotOK()) {
				factory = new AnnotationConfiguration().configure()
						.buildSessionFactory();
			}
		} catch (Exception e) {
			LOG.error("Exception in getFactory while creating new Factory M:"
							+ e.getMessage()
							+ " C: "
							+ e.getCause()
							+ " ST: "
							+ e.getStackTrace());
		}
		lastAccess = new Date(System.currentTimeMillis());
		return factory;
	}

	public Session getCurrentSession() {
		try {
			return getFactory().getCurrentSession();
		} catch (Exception e) {
			LOG.error("Exception in getCurrentSession M: "
					+ e.getMessage() + " C: " + e.getCause());
		}
		return null;
	}

	/**
	 * Synchronize database and hibernate session. Use with caution.
	 * 
	 * <p>
	 * If your database has access from other application you will need
	 * synchronize with database.
	 * </p>
	 * 
	 */
	public void synchronize() {
		try {
			Session session = this.getCurrentSession();
			session.beginTransaction().commit();
		} catch (Exception e) {
			LOG.error("Error while syncronizing with Database. M: "
					+ e.getMessage() + " C: " + e.getCause());
		}
	}

	/**
	 * Checks if session Factory is Alive or Not. Session Factory become dead if
	 * last access time past 3 hours
	 * 
	 * @return True if SessionFactory is dead
	 */
	private synchronized boolean isLastAccessTimeNotOK() {
		try {
			Date now = new Date(System.currentTimeMillis());
			Period period = new Period(now.getTime(), lastAccess.getTime());
			period = period.normalizedStandard(PeriodType.hours());
			return (period.getHours() > SESSION_FACTORY_EXPIRE_IN_HOUR)
					| (period.getHours() < (SESSION_FACTORY_EXPIRE_IN_HOUR * -1));
		} catch (Exception e) {
			LOG.error("Exception in isLastAccessTimeNotOK M:"
					+ e.getMessage() + " C: " + e.getCause() + " ST: "
					+ e.getStackTrace());
		}
		return false;
	}

}
