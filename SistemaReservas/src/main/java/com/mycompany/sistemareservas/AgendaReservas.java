package com.mycompany.sistemareservas;

import javax.swing.JOptionPane;

public class AgendaReservas {
    static String[] reservas = new String[5]; //maximo 5 reservas 
    static int contador = 0;

    public static void main(String[] args) {
        mostrarMenu();
    }

    public static void mostrarMenu() {
        int opcion;
        do {
            String menu = "AGENDA RESERVAS\n" +
                          "1. Crear reserva\n" +
                          "2. Ver reservas\n" +
                          "3. Modificar reserva\n" +
                          "4. Salir";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch(opcion) {
                case 1:
                    crearReserva();
                    break;
                case 2:
                    verReservas();
                    break;
                case 3:
                    modificarReserva();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        } while (opcion != 4);
    }

    private static void crearReserva() {
        if (contador >= reservas.length) {
            JOptionPane.showMessageDialog(null, "No hay espacio para más reservas");
            return;
        }
        String reserva = JOptionPane.showInputDialog("Ingrese los datos de la reserva:");
        reservas[contador] = reserva;
        contador++;
        JOptionPane.showMessageDialog(null, "Reserva creada con éxito");
    }

    private static void verReservas() {
        if (contador == 0) {
            JOptionPane.showMessageDialog(null, "No hay reservas");
            return;
        }
        String texto = "Lista de Reservas:\n";
        for (int i = 0; i < contador; i++) {
            texto = texto + (i + 1) + ". " + reservas[i] + "\n";
        }
        JOptionPane.showMessageDialog(null, texto);
    }

    private static void modificarReserva() {
        if (contador == 0) {
            JOptionPane.showMessageDialog(null, "No hay reservas para modificar");
            return;
        }
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la reserva a modificar (1 a " + contador + "):"));
        if (numero < 1 || numero > contador) {
            JOptionPane.showMessageDialog(null, "Número inválido");
            return;
        }
        String nuevaReserva = JOptionPane.showInputDialog("Ingrese los nuevos datos:", reservas[numero - 1]);
        reservas[numero - 1] = nuevaReserva;
        JOptionPane.showMessageDialog(null, "Reserva modificada con éxito");
    }
}
