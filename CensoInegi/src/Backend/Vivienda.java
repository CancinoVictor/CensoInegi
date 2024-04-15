
package Backend;

import java.util.List;

/**
 *
 * @author usuario hp
 */
public class Vivienda {
    private int idVivienda;
    private Localidad localidad; 
    private Material material;
    private String attributePisos;
    private List<Habitante> habitantes;
    private List<Sustento> sustentos;

    public Vivienda(int idVivienda, Localidad localidad, Material material, String attributePisos,
                    List<Habitante> habitantes, List<Sustento> sustentos) {
        this.idVivienda = idVivienda;
        this.localidad = localidad;
        this.material = material;
        this.attributePisos = attributePisos;
        this.habitantes = habitantes;
        this.sustentos = sustentos;
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
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getAttributePisos() {
        return attributePisos;
    }

    public void setAttributePisos(String attributePisos) {
        this.attributePisos = attributePisos;
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
}
