package com.mycompany.sistemareservas;

import javax.swing.JOptionPane;

public class SistemaReservas {

    public static void main(String[] args) {

        // Crear los 4 centros de pruebas
        CentroDePruebas[] centros = new CentroDePruebas[4];
        centros[0] = new CentroDePruebas("La Valencia", "Heredia");
        centros[1] = new CentroDePruebas("La Sabana", "San José");
        centros[2] = new CentroDePruebas("El Coyol", "Alajuela");
        centros[3] = new CentroDePruebas("Limón", "Limón");

        // Menú de selección de centro
        String mensajeCentros = "Seleccione un centro de pruebas:\n";
        for (int i = 0; i < centros.length; i++) {
            mensajeCentros += (i + 1) + ". " + centros[i].getNombre() + "\n";
        }

        String entrada = JOptionPane.showInputDialog(mensajeCentros);
        if (entrada == null || !entrada.matches("[1-4]")) {
            JOptionPane.showMessageDialog(null, "Selección inválida. Saliendo del sistema.");
            return;
        }

        int seleccion = Integer.parseInt(entrada); //convierte la entrada a un valor entero
        CentroDePruebas centroSeleccionado = centros[seleccion - 1];

        // Menú del centro seleccionado
        int opcion = 0;
        while (opcion != 5) {
            String menu = "CENTRO SELECCIONADO: " + centroSeleccionado.getNombre() + "\n\n";
            menu += "1. Ver dirección del centro\n";
            menu += "2. Ver horarios disponibles\n";
            menu += "3. Agregar un horario\n";
            menu += "4. Quitar un horario\n";
            menu += "5. Salir\n";

            String entradaOpcion = JOptionPane.showInputDialog(menu);
            if (entradaOpcion == null || !entradaOpcion.matches("[1-5]")) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido entre 1 y 5.");
                continue;
            }

            opcion = Integer.parseInt(entradaOpcion);

            switch (opcion) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Dirección: " + centroSeleccionado.getDireccion());
                    break;

                case 2:
                    centroSeleccionado.mostrarDisponibilidad();
                    break;

                case 3:
                    String horaAgregar = JOptionPane.showInputDialog("Ingrese el nuevo horario a habilitar (ej: 07:00, 08:00, ..., 16:00):");
                    centroSeleccionado.agregarHorario(horaAgregar);
                    break;

                case 4:
                    String horaQuitar = JOptionPane.showInputDialog("Ingrese el horario a eliminar (ej: 07:00):");
                    centroSeleccionado.eliminarHorario(horaQuitar);
                    break;

                case 5:
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema.");
                    break;
            }
        }
    }
}
