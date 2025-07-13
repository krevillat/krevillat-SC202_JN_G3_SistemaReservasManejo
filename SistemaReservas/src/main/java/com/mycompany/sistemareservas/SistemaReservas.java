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

        int opcionPrincipal = 0;

        while (opcionPrincipal != 3) {
            String menuPrincipal = "MENÚ PRINCIPAL\n\n";
            menuPrincipal += "1. Reservar o cancelar en un centro\n";
            menuPrincipal += "2. Administrar horarios de un centro\n";
            menuPrincipal += "3. Salir\n";

            opcionPrincipal = Integer.parseInt(JOptionPane.showInputDialog(menuPrincipal));

            if (opcionPrincipal == 1 || opcionPrincipal == 2) {
                // Menú de selección de centro
                String mensajeCentros = "Seleccione un centro de pruebas:\n";
                for (int i = 0; i < centros.length; i++) {
                    mensajeCentros += (i + 1) + ". " + centros[i].getNombre() + "\n";
                }

                int seleccion = Integer.parseInt(JOptionPane.showInputDialog(mensajeCentros));
                if (seleccion < 1 || seleccion > centros.length) {
                    JOptionPane.showMessageDialog(null, "Selección inválida.");
                    continue;
                }

                CentroDePruebas centroSeleccionado = centros[seleccion - 1];

            }
        }
    }
}
