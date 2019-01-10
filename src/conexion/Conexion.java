/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import consultasTablas.PersonaConsultar;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class Conexion {

    public static Connection con;
    ResultSet resulset = null;
    Statement statement = null;
    CallableStatement callableStament = null;
    PreparedStatement preparedStament = null;

    private Conexion() {
    }

    public static Connection obtenerConexion() throws SQLException {
        try {
            //Llamamos al driver utilizado

            if (con == null) {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datosclases", "root", "");
                System.out.println("Entramos al if");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }

    public static void desconectar() throws SQLException {
        con = null;
    }
    private List<String> paises = null;

    public List<String> getPaises() {
        if (paises == null) {
            paises = new ArrayList<>();
            paises.add("VENEZUELA");
            paises.add("COLOMBIA");
            paises.add("PERU");
            paises.add("HONDURAS");
            System.out.println("Entramos en paises");
        }
        return paises;
    }

    public Persona getPerson1(String nombre, String contrasenia) {
        Persona p = null;
        String sql = "SELECT nombre, contrasenia FROM persona WHERE nombre = '" + nombre + "' AND contrasenia = '" + contrasenia + "'";
        try {
            //if (Conexion.obtenerConexion() != null) {
                statement = Conexion.obtenerConexion().createStatement();
                resulset = statement.executeQuery(sql);
                while (resulset.next()) {
                    p = new Persona();
                    p.setNombre(resulset.getString("nombre"));
                    p.setContrasenia(resulset.getString("contrasenia"));
                    //usuarioLista.add(p);

                }

            }
         catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    public Persona getPerson2(String nombre, String contrasenia) {
        Persona p = null;
        String sql = "SELECT nombre, contrasenia FROM persona WHERE nombre = ? AND contrasenia = ?";
        try {
//            if (Conexion.obtenerConexion() != null) {
                preparedStament = Conexion.obtenerConexion().prepareStatement(sql);
                preparedStament.setString(1, nombre);
                preparedStament.setString(2, contrasenia);
                resulset = preparedStament.executeQuery();
                while (resulset.next()) {
                    p = new Persona();
                    p.setNombre(resulset.getString("nombre"));
                    p.setContrasenia(resulset.getString("contrasenia"));
                    //usuarioLista.add(p);

                }

            }
         catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    public Persona getPerson3(String nombre, String contrasenia) {
        Persona p = null;
        String sql = "SELECT nombre, contrasenia FROM persona WHERE nombre = ? AND contrasenia = ?";
        try {
            //if (Conexion.obtenerConexion() != null) {
                preparedStament = Conexion.obtenerConexion().prepareStatement(sql);
                preparedStament.setString(1, nombre);
                preparedStament.setString(2, contrasenia);
                resulset = preparedStament.executeQuery();
                while (resulset.next()) {
                    p = new Persona();
                    p.setNombre(resulset.getString("nombre"));
                    p.setContrasenia(resulset.getString("contrasenia"));
                    //usuarioLista.add(p);

                }

            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    public int insertarPersona(Persona miPersona) throws SQLException {
        int resultado = 0;
        String query = "INSERT INTO persona (id, nombre, apellido, edad, telefono) VALUES (?,?,?,?,?)";
        try {
            if (Conexion.obtenerConexion() != null) {

                preparedStament = Conexion.obtenerConexion().prepareStatement(query);
                preparedStament.setInt(1, miPersona.getId());
                preparedStament.setString(2, miPersona.getNombre());
                preparedStament.setString(3, miPersona.getApellido());
                preparedStament.setInt(4, miPersona.getEdad());
                preparedStament.setString(5, miPersona.getTelefeno());

                resultado = preparedStament.executeUpdate();

            }
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

    public static void main(String[] args) throws SQLException {
        Conexion c = new Conexion();
        Persona pi = c.getPerson1("juanita", "1234");
        System.out.println(pi.getContrasenia() + " " + pi.getNombre());
        System.out.println("************");
        System.out.println(pi.getContrasenia() + " " + pi.getNombre());
        System.out.println("***********");
        List<Persona> lista = c.seleccionarPersonas();
        for (Persona persona : lista) {
            System.out.println(persona);
        }
        System.out.println("********");
        Persona pee = c.getPerson2("juanita", "1234");
        System.out.println(pee.getNombre());
        System.out.println("********");
        Persona pee2 = c.getPerson3("juanita", "1234");
        System.out.println(pee2.getContrasenia());

//        Ciudad city = new Ciudad(1, "boto");
//        Persona persona = new Persona(8, "Omar", "Gomez", "1234", 19, "hola123", city);
//        
//        int res = c.insertarPersona(persona);
//        if (res==1) {
//            System.out.println("Insertado");
//        }else{
//            System.out.println("No insertado");
//        }
        //System.out.println(paises);
        //System.out.println("*********");
//        for (String paises : c.getPaises()) {
//            System.out.println(paises);
//        }
    }

}
