
package Backend;

/**
 *
 * @author Cecyl 
 */
public class Sustento {
    private int idSustento;
    private Vivienda vivienda;
    private String sustento;

    public Sustento(int idSustento, Vivienda vivienda, String sustento) {
        this.idSustento = idSustento;
        this.vivienda = vivienda;
        this.sustento = sustento;
    }

    public int getIdSustento() {
        return idSustento;
    }

    public void setIdSustento(int idSustento) {
        this.idSustento = idSustento;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    public String getSustento() {
        return sustento;
    }

    public void setSustento(String sustento) {
        this.sustento = sustento;
    }
}
