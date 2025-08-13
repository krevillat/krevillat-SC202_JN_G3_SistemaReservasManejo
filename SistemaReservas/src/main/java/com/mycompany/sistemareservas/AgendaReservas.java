package com.mycompany.sistemareservas;

import static com.mycompany.sistemareservas.CentroDePruebas.seleccionarCentro;
import javax.swing.JOptionPane;

public class AgendaReservas {

    public static void main(String[] args) {
   mostrarMenu();
   CentroDePruebas[] centros = CentroDePruebas.listaCentros();
}
    public static void mostrarMenu() {
         
        CentroDePruebas[] centros = CentroDePruebas.listaCentros();
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
            
            switch (entrada) {
                case "1": {
                    // 1) Seleccionar centro (método de esta clase)
                    int indexCentro = seleccionarCentro(centros);
                    if (indexCentro == -1) 
                        break;

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

                    // 4) Datos para la reserva y agendar
                    if (cantidadReservas >= reservas.length) {
                        JOptionPane.showMessageDialog(null, "No hay espacios disponibles.");
                        break;
                    }

                        String nombre = JOptionPane.showInputDialog("Ingrese su nombre para la reserva:");
                        if (nombre == null || nombre.trim().isEmpty()) break;

                        String dia = JOptionPane.showInputDialog("Día (Lunes..Sábado):");
                        if (dia == null) break;

                        String hora = JOptionPane.showInputDialog("Hora (ej. 07:00, 12:00, 15:00):");
                        if (hora == null) break;

                        // 5) Intentar agendar (usa agendaReserva de CentroDePruebas)
                        boolean ok = centro.agendaReserva(dia, hora);
                        if (ok) {
                            reservas[cantidadReservas++] =
                                nombre + " | " + centro.getNombre() + " | " + dia + " " + hora;
                            JOptionPane.showMessageDialog(null, "Reserva guardada.");
                        } else {
                            JOptionPane.showMessageDialog(null, "No hay cupos disponibles o datos inválidos.");
                        }
                        break;
                    }

                case "2": {
                    String lista;
                    if (cantidadReservas == 0) {
                        lista = "No hay reservas aún.";
                    } else {
                        listaReserva sb = new listaReserva ("Reservas actuales:\n");
                        for (int i = 0; i < cantidadReservas; i++) {
                            sb.append(i + 1).append(". ").append(reservas[i]).append("\n");
                        }
                        lista = lr.toString();
                    }
                    JOptionPane.showMessageDialog(null, lista);
                    break;
                }

                case "3":
                    JOptionPane.showMessageDialog(null, "Redirigiendo al módulo de reservas...");
                 
                    break;

                case "4":
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema.");
                    opcion = 4;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    break;
            }
        }
    }

    
    

 
          
}

