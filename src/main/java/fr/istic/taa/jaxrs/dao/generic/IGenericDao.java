package fr.istic.taa.jaxrs.dao.generic;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public interface IGenericDao<K, T extends Serializable> {
 
   T findOne(final K id);
 
   List<T> findAll();
   
  
   void save(final T entity);
 
   T update(final T entity);
 
   void delete(final T entity);
 
   void deleteById(final K entityId);
   
   List<T> findAllExistingElementList(List<K> id);
   
}