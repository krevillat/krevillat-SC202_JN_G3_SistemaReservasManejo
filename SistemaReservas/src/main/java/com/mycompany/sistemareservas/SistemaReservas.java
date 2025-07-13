package com.mycompany.sistemareservas;

import javax.swing.JOptionPane;

public class SistemaReservas {

    public static void main(String[] args) {

        // Crear los centros de pruebas
        CentroDePruebas[] centros = new CentroDePruebas[4];
        centros[0] = new CentroDePruebas("La Valencia", "Heredia");
        centros[1] = new CentroDePruebas("La Sabana", "San José");
        centros[2] = new CentroDePruebas("El Coyol", "Alajuela");
        centros[3] = new CentroDePruebas("Limón", "Limón");

        // Selección de centro primero
        String mensajeCentros = "Seleccione un centro de pruebas:\n";
        for (int i = 0; i < centros.length; i++) {
            mensajeCentros += (i + 1) + ". " + centros[i].getNombre() + "\n";
        }

        int seleccion = Integer.parseInt(JOptionPane.showInputDialog(mensajeCentros));
        if (seleccion < 1 || seleccion > centros.length) {
            JOptionPane.showMessageDialog(null, "Selección inválida. Saliendo del sistema.");
            return;
        }

        CentroDePruebas centroSeleccionado = centros[seleccion - 1];

        int opcion = 0;
        while (opcion != 4) {
            String menu = "CENTRO SELECCIONADO: " + centroSeleccionado.getNombre() + "\n\n";
            menu += "1. Ver horarios disponibles\n";
            menu += "2. Agregar horario\n";
            menu += "3. Eliminar horario\n";
            menu += "4. Salir\n";

            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {
                case 1:
                    centroSeleccionado.mostrarDisponibilidad();
                    break;
                case 2:
                    String horaAgregar = JOptionPane.showInputDialog("Ingrese la hora a reservar (ej: 07:00):");
                    centroSeleccionado.agregarHorario(horaAgregar);
                    break;
                case 3:
                    String horaEliminar = JOptionPane.showInputDialog("Ingrese la hora a cancelar (ej: 07:00):");
                    centroSeleccionado.eliminarHorario(horaEliminar);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }
}
