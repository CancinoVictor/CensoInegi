
package Backend;

/**
 *
 * @author Cecyl 
 */
public class Material {
    private int idMaterial;
    private String material;

    public Material(int idMaterial, String material) {
        this.idMaterial = idMaterial;
        this.material = material;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
