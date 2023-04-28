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
   
   
   /**
    * Search for each if in the given list, the related entry
    * @param id, the list of id 
    * @return a list of the entry that has been fund
    */
   List<T> findAllExistingElementList(List<K> id);
   
   /**
    * This method will allow to check if the entry with the given ID can be deleted (which means it has no relation with any other object)
    * @param id, the id of the entry that will be deleted
    * @return true if the entry can be deleted or false if not
    */
   Boolean canBeDeleted(final K id);
   
}