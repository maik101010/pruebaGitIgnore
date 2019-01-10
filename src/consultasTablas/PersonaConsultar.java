package consultasTablas;

import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.StampedLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import tablas.Persona;
import tablas.Ciudad;

/**
 *
 * @author Michael García A
 */
public class PersonaConsultar {

    ResultSet resulset = null;
    Statement statement = null;
    CallableStatement callableStament = null;
    PreparedStatement preparedStament = null;

    public List<Persona> seleccionarPersonas() throws SQLException {
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
            if (Conexion.obtenerConexion() != null) {
                resulset.close();
                Conexion.desconectar();

            }

        }
        return lista;
    }

    public int insertarPersona(Persona miPersona) throws SQLException {
        int resultado = 0;
        String query = "INSERT INTO persona (id, nombre, apellido, edad, telefono) VALUES (?,?,?,?,?)";
        try {
            preparedStament = Conexion.obtenerConexion().prepareStatement(query);
            preparedStament.setInt(1, miPersona.getId());
            preparedStament.setString(2, miPersona.getNombre());
            preparedStament.setString(3, miPersona.getApellido());
            preparedStament.setInt(4, miPersona.getEdad());
            preparedStament.setString(5, miPersona.getTelefeno());

            resultado = preparedStament.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PersonaConsultar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (Conexion.obtenerConexion() != null) {
                preparedStament.close();
                Conexion.desconectar();
            }

        }
        return resultado;
    }

    public int actualizarPersona(Persona miPersona) throws SQLException {
        int resultado = 0;
        String query = "UPDATE persona set nombre=?, apellido=?, telefono=?, edad=?, id_ciudad=? WHERE id =?";
        try {

            preparedStament = Conexion.obtenerConexion().prepareStatement(query);
            preparedStament.setString(1, miPersona.getNombre());
            preparedStament.setString(2, miPersona.getApellido());
            preparedStament.setString(3, miPersona.getTelefeno());
            preparedStament.setInt(4, miPersona.getEdad());
            preparedStament.setInt(5, miPersona.getCiudad().getId());
            preparedStament.setInt(6, miPersona.getId());

            //preparedStament.setInt(6, miPersona.getCiudad().getId());
            resultado = preparedStament.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PersonaConsultar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (Conexion.obtenerConexion() != null) {
                preparedStament.close();
            }

        }
        return resultado;
    }

    public int eliminarPersona(int id_persona) throws SQLException {
        int resultado = 0;
        String query = "DELETE FROM persona WHERE id =?";
        try {
            preparedStament = Conexion.obtenerConexion().prepareStatement(query);
            preparedStament.setInt(1, id_persona);

            resultado = preparedStament.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PersonaConsultar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (Conexion.obtenerConexion() != null) {
                preparedStament.close();
                Conexion.desconectar();
            }
        }
        return resultado;
    }

    public List<Persona> obtenerPersonasProcedure() throws SQLException {

        List<Persona> lista = new LinkedList<>();
        Persona p;
        Ciudad c;

        callableStament = Conexion.obtenerConexion().prepareCall("{call obtener_personas_ciudad()}");
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
            lista.add(p);
        }

        return lista;
    }

    public List<Persona> obtenerPersonasProcedureId(int id) throws SQLException {

        List<Persona> lista = new LinkedList<>();
        Persona p;
        Ciudad c;
        callableStament = Conexion.obtenerConexion().prepareCall("{call obtener_personas_ciudad_id(" + id + ")}");
        resulset = callableStament.executeQuery();
        if (Conexion.obtenerConexion() != null) {
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
                lista.add(p);
            }
        }
        return lista;
    }

    public int insertarPersonaProcedure(Persona p) throws SQLException {
        int resultado = 0;
        try {
            callableStament = Conexion.obtenerConexion().prepareCall("{call insertar_persona(?,?,?,?,?,?)}");

            callableStament.setInt(1, p.getId());
            callableStament.setString(2, p.getNombre());
            callableStament.setString(3, p.getApellido());
            callableStament.setInt(4, p.getEdad());
            callableStament.setString(5, p.getTelefeno());
            callableStament.setInt(6, p.getIdCiudad());

            resultado = callableStament.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PersonaConsultar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (Conexion.obtenerConexion() != null) {
                callableStament.close();
            }

        }
        return resultado;

    }

    public Persona obtenerUsuario(String nombre, String contrasenia) throws SQLException {
        //List<Persona> usuarioLista = new ArrayList<>();
        Persona p = null;
        String sql = "SELECT nombre, contrasenia FROM persona WHERE nombre = '" + nombre + "' AND contrasenia = '" + contrasenia + "'";
        try {

            statement = Conexion.obtenerConexion().createStatement();
            resulset = statement.executeQuery(sql);
            while (resulset.next()) {
                p = new Persona();
                p.setNombre(resulset.getString("nombre"));
                p.setContrasenia(resulset.getString("contrasenia"));
                //usuarioLista.add(p);

            }

            //return p;
        } catch (SQLException ex) {
            Logger.getLogger(PersonaConsultar.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if (Conexion.obtenerConexion() != null) {
                resulset.close();
                Conexion.desconectar();
            }
        }
        return p;
    }

    public static void main(String[] args) throws SQLException {
        PersonaConsultar p = new PersonaConsultar();
        List<Persona> lista = p.seleccionarPersonas();

        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Nombre: " + lista.get(i).getNombre());
            System.out.println("Apellido: "
                    + lista.get(i).getApellido());
            System.out.println("Tel: " + lista.get(i).getTelefeno());
            System.out.println("Edad: " + lista.get(i).getEdad());
            System.out.println("Ciudad: " + lista.get(i).getCiudad().getNombre());
            System.out.println("id: " + lista.get(i).getId());
        }
    }
}
