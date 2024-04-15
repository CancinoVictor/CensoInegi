
package Backend;

/**
 *
 * @author Cecyl 
 */
public class Habitante {
    private String ine;
    private int idVivienda;
    private String nombre;
    private String apellido;
    private int telefono;

    public Habitante(String ine, int idVivienda, String nombre, String apellido, int telefono) {
        this.ine = ine;
        this.idVivienda = idVivienda;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public String getIne() {
        return ine;
    }

    public void setIne(String ine) {
        this.ine = ine;
    }

    public int getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(int idVivienda) {
        this.idVivienda = idVivienda;
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    } 
}
