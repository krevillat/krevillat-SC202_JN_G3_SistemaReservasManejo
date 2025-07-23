/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemareservas;
    public static void main(String[] args) {
        String[] reservas = new String[5]; // máximo 5 reservas
        int cantidadReservas = 0;
        int opcion = 0;
        String entrada;

        while (opcion != 4) {
            entrada = JOptionPane.showInputDialog(
                "MENÚ DE RESERVAS\n" +
                "1. Hacer una reserva\n" +
                "2. Ver reservas\n" +
                "3. Cambiar reserva (Redirige al módulo reservas)\n" +
                "4. Salir"
            );

            if (entrada == null) break; // salir si cancelan

            if (entrada.equals("1")) {
                if (cantidadReservas < reservas.length) {
                    String nombre = JOptionPane.showInputDialog("Ingrese su nombre para la reserva:");
                    reservas[cantidadReservas] = nombre;
                    cantidadReservas++;
                    JOptionPane.showMessageDialog(null, "Reserva guardada.");
                } else {
                    JOptionPane.showMessageDialog(null, "No hay espacios disponibles.");
                }
            } else if (entrada.equals("2")) {
                String lista = "Reservas actuales:\n";
                for (int i = 0; i < cantidadReservas; i++) {
                    lista += (i + 1) + ". " + reservas[i] + "\n";
                }
                if (cantidadReservas == 0) {
                    lista = "No hay reservas aún.";
                }
                JOptionPane.showMessageDialog(null, lista);
            } else if (entrada.equals("3")) {
                JOptionPane.showMessageDialog(null, "Redirigiendo al módulo de reservas...");
                // Aquí se puede conectar al otro módulo
            } else if (entrada.equals("4")) {
                JOptionPane.showMessageDialog(null, "Gracias por usar el sistema.");
                opcion = 4;
            } else {
                JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }
}

