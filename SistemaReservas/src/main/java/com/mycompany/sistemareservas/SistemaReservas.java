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

        int opcionAdmin = 0;
        while (opcionAdmin != 4) {
            String menuAdmin = "ADMINISTRACIÓN DE CENTRO: " + centroSeleccionado.getNombre() + "\n\n";
            menuAdmin += "1. Ver horarios disponibles\n";
            menuAdmin += "2. Habilitar nuevo horario\n";
            menuAdmin += "3. Eliminar horario\n";
            menuAdmin += "4. Salir\n";

            opcionAdmin = Integer.parseInt(JOptionPane.showInputDialog(menuAdmin));

            switch (opcionAdmin) {
                case 1:
                    centroSeleccionado.mostrarDisponibilidad();
                    break;
                case 2:
                    String nuevoHorario = JOptionPane.showInputDialog("Ingrese el nuevo horario a habilitar (ej: 15:00):");
                    centroSeleccionado.habilitarHorario(nuevoHorario);
                    break;
                case 3:
                    String horarioEliminar = JOptionPane.showInputDialog("Ingrese el horario a eliminar (ej: 15:00):");
                    centroSeleccionado.eliminarHorario(horarioEliminar);
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
