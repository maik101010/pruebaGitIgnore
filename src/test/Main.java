package test;

import conexion.Conexion;
import consultasTablas.PersonaConsultar;
import daoImplements.PersonaDaoImpl;
import java.sql.SQLException;
import java.util.List;
import tablas.Ciudad;
import tablas.Persona;

/**
 *
 * @author Michael García A
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        Persona miPersona = new Persona();
        PersonaDaoImpl personaConsultada = new PersonaDaoImpl();
        Ciudad ciudad = new Ciudad();
        

        //int respuesta = personaConsultada.insertarPersonaProcedure(miPersona);
//
//        if (respuesta==1) {
//            System.out.println("Persona insertada");
//        }else{
//            System.out.println("Persona no insertada");
//        }
        //List<Persona> listaRetornada = personaConsultada.getPersonId(1);
        String usuario = "juanita";
        String password = "1234";
        Persona persona = personaConsultada.getPersonNombreAndPassword(usuario, password);

        //stream
        //listaRetornada.stream().filter(l->l.getNombre().startsWith("O")).forEach(l->System.out.println(l));
        //for (int i = 0; i < listaRetornada i++) {
        if (persona.getNombre() == null || persona.getContrasenia() == null) {
            System.out.println("No logueado");
        }else if (persona.getNombre().equals(usuario) && persona.getContrasenia().equals(password)){
        
            System.out.println("Logueado");
        }

        //}
//        
//        for (Persona persona : listaRetornada) {
//            System.out.println(persona);
//        }
    }

}
