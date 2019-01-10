/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoInterface;

import java.util.List;
import tablas.Persona;

/**
 *
 * @author Michael García A
 */
public interface IPersonaDao {
    Persona getPersonId(int id);
    List<Persona> getAll();
    void insert(Persona p);
    void update(Persona p);
    int delete(int id);
    Persona getPersonNombreAndPassword(String usuario, String password);
    
    
}
