package tablas;

/**
 *
 * @author Michael García A
 */
public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private String telefeno;
    private int edad;
    private Ciudad ciudad;
    private String contrasenia;

    public Persona(){}
    public Persona(int id, String nombre, String apellido, String telefeno, int edad, String contrasenia, Ciudad ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefeno = telefeno;
        this.edad = edad;
        this.contrasenia = contrasenia;
        this.ciudad = ciudad;
    }


    public int getId() {
        return id;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    
    public int getIdCiudad(){
        return getCiudad().getId();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefeno() {
        return telefeno;
    }

    public void setTelefeno(String telefeno) {
        this.telefeno = telefeno;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", telefeno=" + telefeno + ", edad=" + edad +", " +ciudad.getNombre()+'}';
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public String getContrasenia(){
        return contrasenia;
    }
    
}
