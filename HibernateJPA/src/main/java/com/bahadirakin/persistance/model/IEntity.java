package com.bahadirakin.persistance.model;

import java.io.Serializable;

/**
 * All database entities must implements this interface, which represents the
 * minimum requirements to meet.
 * 
 * <p>
 * A database entity always has <code>Integer</code> surrogate key (system ID).
 * It must also be {@link Serializable}, {@link Cloneable} and
 * {@link Comparable}
 * </p>
 * 
 * @author Bahadır AKIN
 * 
 */
public interface IEntity extends Serializable, Cloneable, Comparable<IEntity> {

	public Integer getId();

	public void setId(Integer id);
	
	boolean isNew();
}
