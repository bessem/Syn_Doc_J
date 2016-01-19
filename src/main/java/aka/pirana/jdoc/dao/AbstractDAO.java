/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aka.pirana.jdoc.dao;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

/**
 *
 * @author aka
 * CRUD AND STUFF "ABSTRACT"
 */
public abstract class AbstractDAO<T> {
    	protected Class<T> entityClass;

	@Inject
	private EntityManager em;

	public AbstractDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected EntityManager getEntityManager() {
		return this.em;
	}

	/**
	 * Retrieves the meta-model for a certain entity.
	 * 
	 * @return the meta-model of a certain entity.
	 */
	protected EntityType<T> getMetaModel() {
		return getEntityManager().getMetamodel().entity(entityClass);
	}

	@Transactional
	public void create(T entity) {
		getEntityManager().persist(entity);
	}

	@Transactional
	public void update(T entity) {
		getEntityManager().merge(entity);
	}

	@Transactional
	public void remove(Long entityId) {
		T entity = find(entityId);

		if (entity != null)
			remove(entity);
	}

	@Transactional
	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	public T find(Object id) {
		return getEntityManager().find(entityClass, id);
	}

	public List<T> findAll() {
		CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
		        .createQuery(entityClass);
		cq.select(cq.from(entityClass));

		return getEntityManager().createQuery(cq).getResultList();
	}

	public List<T> findRange(int[] range) {
		CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder()
		        .createQuery(entityClass);
		cq.select(cq.from(entityClass));

		javax.persistence.Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);

		return q.getResultList();
	}

	public int count() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<T> root = cq.from(entityClass);
		cq.select(cb.count(root));
		Long count = getEntityManager().createQuery(cq).getSingleResult();

		return count.intValue();
	}

}
