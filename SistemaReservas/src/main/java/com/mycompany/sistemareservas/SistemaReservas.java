package com.mycompany.sistemareservas;

import javax.swing.JOptionPane;

public class SistemaReservas {
    public static void main(String[] args) {
        // Crear centros de pruebas disponibles
        CentroDePruebas[] nombres = new CentroDePruebas[4];
        nombres[0] = new CentroDePruebas("La Valencia", "Heredia");
        nombres[1] = new CentroDePruebas("La Sabana", "San José");
        nombres[2] = new CentroDePruebas("El Coyol", "Alajuela");
        nombres[3] = new CentroDePruebas("Limón", "Limón");

        CentroDePruebas centroSeleccionado = null;

        // Seleccionar centro
        String seleccion = JOptionPane.showInputDialog(
            "Seleccione un centro de pruebas:\n" +
            "1. La Valencia\n" +
            "2. La Sabana\n" +
            "3. El Coyol\n" +
            "4. Limón\n" +
            "5. Salir"
        );

        int opcionCentro = Integer.parseInt(seleccion);

        if (opcionCentro >= 1 && opcionCentro <= 4) {
            centroSeleccionado = nombres[opcionCentro - 1];
        } else {
            JOptionPane.showMessageDialog(null, "Gracias por usar el sistema.");
            return;
        }

        // Menú de opciones para el centro seleccionado
        int opcion = 0;

        while (opcion != 5) {
            String menu = "CENTRO SELECCIONADO: " + centroSeleccionado.getNombre() + "\n\n";
            menu += "1. Ver dirección del centro\n";
            menu += "2. Ver horarios disponibles\n";
            menu += "3. Agregar un horario\n";
            menu += "4. Quitar un horario\n";
            menu += "5. Salir";

            String entrada = JOptionPane.showInputDialog(menu);
            opcion = Integer.parseInt(entrada);

            if (opcion == 1) {
                centroSeleccionado.muestraDireccion();
            } else if (opcion == 2) {
                centroSeleccionado.muestraHorarios();
            } else if (opcion == 3) {
                String nuevoHorario = JOptionPane.showInputDialog("Ingrese el nuevo horario:");
                centroSeleccionado.agregaHorario(nuevoHorario);
            } else if (opcion == 4) {
                String horarioEliminar = JOptionPane.showInputDialog("Ingrese el horario a eliminar:");
                centroSeleccionado.quitaHorario(horarioEliminar);
            } else if (opcion == 5) {
                JOptionPane.showMessageDialog(null, "Gracias por usar el sistema.");
            } else {
                JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }
}
