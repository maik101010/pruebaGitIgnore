/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImplements;

import conexion.Conexion;
import consultasTablas.PersonaConsultar;
import daoInterface.IPersonaDao;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tablas.Ciudad;
import tablas.Persona;

/**
 *
 * @author Michael García A
 */
public class PersonaDaoImpl implements IPersonaDao {

    ResultSet resulset = null;
    Statement statement = null;
    CallableStatement callableStament = null;
    PreparedStatement preparedStament = null;


    @Override
    public Persona getPersonId(int id) {
        Persona p = null;
        Ciudad c = null;

        try {
            callableStament = Conexion.obtenerConexion().prepareCall("{call obtener_personas_ciudad_id(" + id + ")}");
            resulset = callableStament.executeQuery();

            while (resulset.next()) {
                p = new Persona();
                c = new Ciudad();
                p.setId(resulset.getInt("id"));
                p.setNombre(resulset.getString("nombre"));
                p.setApellido(resulset.getString("apellido"));
                p.setEdad(resulset.getInt("edad"));
                p.setTelefeno(resulset.getString("telefono"));
                c.setNombre(resulset.getString("ciudad"));
                //Agregamos nuestra ciudad al objeto Persona
                p.setCiudad(c);
                //Agregamos la Persona a la Lista

            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public List<Persona> getAll() {
        List<Persona> lista = new ArrayList<>();
        Persona p;
        Ciudad c;
        String consulta = "SELECT * FROM persona INNER JOIN ciudad ON ciudad.id = persona.id_ciudad";
        try {
            statement = Conexion.obtenerConexion().createStatement();
            resulset = statement.executeQuery(consulta);
            while (resulset.next()) {
                p = new Persona();
                c = new Ciudad();
                p.setNombre(resulset.getString("nombre"));
                p.setApellido(resulset.getString("apellido"));
                p.setTelefeno(resulset.getString("telefono"));
                p.setEdad(resulset.getInt("edad"));
                p.setId(resulset.getInt("id"));
                //Llamamos al metodo setId de la clase Ciudad
                //c.setId(resulset.getInt("id_ciudad"));
                c.setNombre(resulset.getString("ciudad.nombre"));
                p.setCiudad(c);
                lista.add(p);
            }
            resulset.close();

        } catch (SQLException ex) {
            Logger.getLogger(PersonaConsultar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                
                    resulset.close();
                    Conexion.desconectar();
                
            } catch (SQLException ex) {
                Logger.getLogger(PersonaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public void insert(Persona p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Persona p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona getPersonNombreAndPassword(String usuario, String password) {
        Persona p = new Persona();
        String sql = "SELECT nombre, contrasenia FROM persona WHERE nombre = '" + usuario + "' AND contrasenia = '" + password + "'";
        try {
            statement = Conexion.obtenerConexion().createStatement();
            resulset = statement.executeQuery(sql);
            while (resulset.next()) {
                p.setNombre(resulset.getString("nombre"));
                p.setContrasenia(resulset.getString("contrasenia"));

            }
            //return p;

        } catch (SQLException ex) {
            Logger.getLogger(PersonaConsultar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
