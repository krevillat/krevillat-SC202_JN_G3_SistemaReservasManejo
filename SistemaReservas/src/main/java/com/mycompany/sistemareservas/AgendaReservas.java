package com.mycompany.sistemareservas;

import javax.swing.JOptionPane;

public class AgendaReservas {
    static String[] reservas = new String[5]; // máximo 5 reservas
    static int cantidadReservas = 0; 
    static CentroDePruebas[] centros = CentroDePruebas.listaCentros(); // lista de centros

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
        // 1) Seleccionar centro
        int indexCentro = CentroDePruebas.seleccionarCentro(centros);
        if (indexCentro == -1) return; // cancelado

        // 2) Instancia seleccionada
        CentroDePruebas centro = centros[indexCentro];

        // 3) Mostrar disponibilidad
        JOptionPane.showMessageDialog(
            null,
            "Centro: " + centro.getNombre() + "\nDirección: " + centro.getDireccion(),
            "Centro de Pruebas Seleccionado",
            JOptionPane.INFORMATION_MESSAGE
        );
        centro.mostrarDisponibilidad();

        // 4) Verificar espacio
        if (cantidadReservas >= reservas.length) {
            JOptionPane.showMessageDialog(null, "No hay espacios disponibles.");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese su nombre para la reserva:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        String dia = JOptionPane.showInputDialog("Día (Lunes..Sábado):");
        if (dia == null) return;

        String hora = JOptionPane.showInputDialog("Hora (ej. 07:00, 12:00, 15:00):");
        if (hora == null) return;

        // 5) Intentar agendar
        boolean ok = centro.agendaReserva(dia, hora);
        if (ok) {
            reservas[cantidadReservas++] = nombre + " | " + centro.getNombre() + " | " + dia + " " + hora;
            JOptionPane.showMessageDialog(null, "Reserva guardada.");
        } else {
            JOptionPane.showMessageDialog(null, "No hay cupos disponibles o datos inválidos.");
        }
    }

    private static void verReservas() {
        if (cantidadReservas == 0) {
            JOptionPane.showMessageDialog(null, "No hay reservas");
            return;
        }
        String texto = "Lista de Reservas:\n";
        for (int i = 0; i < cantidadReservas; i++) {
            texto += (i + 1) + ". " + reservas[i] + "\n";
        }
        JOptionPane.showMessageDialog(null, texto);
    }

    private static void modificarReserva() {
        if (cantidadReservas == 0) {
            JOptionPane.showMessageDialog(null, "No hay reservas para modificar");
            return;
        }
        int numero = Integer.parseInt(JOptionPane.showInputDialog(
            "Ingrese el número de la reserva a modificar (1 a " + cantidadReservas + "):"
        ));
        if (numero < 1 || numero > cantidadReservas) {
            JOptionPane.showMessageDialog(null, "Número inválido");
            return;
        }
        String nuevaReserva = JOptionPane.showInputDialog("Ingrese los nuevos datos:", reservas[numero - 1]);
        if (nuevaReserva != null && !nuevaReserva.trim().isEmpty()) {
            reservas[numero - 1] = nuevaReserva;
            JOptionPane.showMessageDialog(null, "Reserva modificada con éxito");
        }
    }
} 
