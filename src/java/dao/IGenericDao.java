/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Administrateur
 */
public interface IGenericDao {

    boolean delete(long id);

    boolean save(Object o);

    List<Object> selectAll();

    Object selectOne(long id);

    Object selectOne (String name);
    
    int count ();
    
    Object getLast();
    
}
