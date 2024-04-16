
package Backend;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario hp
 */
public class Vivienda extends Observable {
    private int idVivienda;
    private Localidad localidad;
    private Material material;
    private String attributePisos;
    private List<Habitante> habitantes = new ArrayList<>();
    private List<Sustento> sustentos = new ArrayList<>();

    public Vivienda(int idVivienda, Localidad localidad, Material material, String attributePisos) {
        this.idVivienda = idVivienda;
        this.localidad = localidad;
        this.material = material;
        this.attributePisos = attributePisos;
    }

    public int getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(int idVivienda) {
        this.idVivienda = idVivienda;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
        notificarObservadores(); // Notificar a los observadores de cambios
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
        notificarObservadores(); // Notificar a los observadores de cambios
    }

    public String getAttributePisos() {
        return attributePisos;
    }

    public void setAttributePisos(String attributePisos) {
        this.attributePisos = attributePisos;
        notificarObservadores(); // Notificar a los observadores de cambios
    }

    public List<Habitante> getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(List<Habitante> habitantes) {
        this.habitantes = habitantes;
    }

    public List<Sustento> getSustentos() {
        return sustentos;
    }

    public void setSustentos(List<Sustento> sustentos) {
        this.sustentos = sustentos;
    }

    public void agregarHabitante(Habitante habitante) {
        habitantes.add(habitante);
    }

    public void agregarSustento(Sustento sustento) {
        sustentos.add(sustento);
        notificarObservadores(); // Notificar a los observadores de cambios
    }
}
