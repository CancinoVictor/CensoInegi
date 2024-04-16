/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author usuario hp
 */
class ObservadorVivienda implements Observer {
    private Vivienda vivienda;

    public ObservadorVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    @Override
    public void actualizar() {
        // Realizar alguna acción cuando la vivienda se actualiza
        System.out.println("La vivienda con ID " + vivienda.getIdVivienda() + " ha sido actualizada.");
    }
}