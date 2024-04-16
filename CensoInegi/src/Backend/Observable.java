
package Backend;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cecyl 
 */
public class Observable {
    private List<Observer> observadores = new ArrayList<>();

    public void agregarObservador(Observer observador) {
        observadores.add(observador);
    }

    public void removerObservador(Observer observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores() {
        for (Observer observador : observadores) {
            observador.actualizar();
        }
    }
}
