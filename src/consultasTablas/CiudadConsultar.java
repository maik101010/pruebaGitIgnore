/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultasTablas;

import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tablas.Ciudad;
import tablas.Persona;

/**
 *
 * @author Michael García A
 */
public class CiudadConsultar {

    ResultSet resulset = null;
    Statement statement = null;
    CallableStatement callableStament = null;
    PreparedStatement preparedStament = null;
            

    public List<Ciudad> seleccionarCiudades() throws SQLException {
        List<Ciudad> lista = new ArrayList<>();

        Ciudad c;

        String consulta = "SELECT * FROM ciudad";
        try {
            statement = Conexion.obtenerConexion().createStatement();
            resulset = statement.executeQuery(consulta);
            while (resulset.next()) {
                c = new Ciudad();
                c.setId(resulset.getInt("id"));
                c.setNombre(resulset.getString("nombre"));
                lista.add(c);
            }
            resulset.close();

        } catch (SQLException ex) {
            Logger.getLogger(PersonaConsultar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (Conexion.obtenerConexion() != null) {
                resulset.close();
                Conexion.desconectar();
            }
        }
        return lista;
    }

}
