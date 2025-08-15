package com.mycompany.sistemareservas;

import javax.swing.JOptionPane;

public class CentroDePruebas {

    private String nombre;
    private String direccion;

    private String[] dias;         // 6 días operativos
    private String[] horasSemana;  // L–V (07:00 a 16:00)
    private String[] horasSabado;  // Sábado (07:00 a 12:00)

    // cuposPorDiaHora[día][hora] -> cupos disponibles
    private int[][] cuposPorDiaHora;

    // -------------------- Constructor --------------------
    public CentroDePruebas(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;

        // Crear arreglos
        dias = new String[6];
        horasSemana = new String[10];
        horasSabado = new String[6];

        // Llenar arreglo de días
        dias[0] = "Lunes";
        dias[1] = "Martes";
        dias[2] = "Miércoles";
        dias[3] = "Jueves";
        dias[4] = "Viernes";
        dias[5] = "Sábado";

        // Llenar arreglo de horas de lunes a viernes
        horasSemana[0] = "07:00";
        horasSemana[1] = "08:00";
        horasSemana[2] = "09:00";
        horasSemana[3] = "10:00";
        horasSemana[4] = "11:00";
        horasSemana[5] = "12:00";
        horasSemana[6] = "13:00";
        horasSemana[7] = "14:00";
        horasSemana[8] = "15:00";
        horasSemana[9] = "16:00";

        // Llenar arreglo de horas del sábado
        horasSabado[0] = "07:00";
        horasSabado[1] = "08:00";
        horasSabado[2] = "09:00";
        horasSabado[3] = "10:00";
        horasSabado[4] = "11:00";
        horasSabado[5] = "12:00";

        // Inicializar cupos por día y hora
        cuposPorDiaHora = new int[dias.length][];
        for (int i = 0; i < dias.length; i++) {
            if (i < 5) { // Lunes a Viernes
                cuposPorDiaHora[i] = new int[horasSemana.length];
                for (int j = 0; j < horasSemana.length; j++) {
                    int horaEntera = Integer.parseInt(horasSemana[j].substring(0, 2));
                    cuposPorDiaHora[i][j] = obtenerCuposPorHora(horaEntera);
                }
            } else { // Sábado
                cuposPorDiaHora[i] = new int[horasSabado.length];
                for (int j = 0; j < horasSabado.length; j++) {
                    int horaEntera = Integer.parseInt(horasSabado[j].substring(0, 2));
                    cuposPorDiaHora[i][j] = obtenerCuposPorHora(horaEntera);
                }
            }
        }
    }

    // -------------------- Getters --------------------
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }

    // -------------------- Mostrar disponibilidad --------------------
    public void mostrarDisponibilidad() {
        String disponibilidad = "Disponibilidad de " + nombre + ":\n" + direccion + "\n\n";

        for (int i = 0; i < dias.length; i++) {
            disponibilidad += dias[i] + ":\n";
            if (i < 5) { // Lunes a Viernes
                for (int j = 0; j < horasSemana.length; j++) {
                    disponibilidad += horasSemana[j] + " -> " + cuposPorDiaHora[i][j] + " cupos\n";
                }
            } else { // Sábado
                for (int j = 0; j < horasSabado.length; j++) {
                    disponibilidad += horasSabado[j] + " -> " + cuposPorDiaHora[i][j] + " cupos\n";
                }
            }
            disponibilidad += "\n";
        }

        JOptionPane.showMessageDialog(null, disponibilidad, "Disponibilidad", JOptionPane.INFORMATION_MESSAGE);
    }

    // -------------------- Reservar --------------------
    public boolean agendaReserva(String dia, String hora) {
        for (int i = 0; i < dias.length; i++) {
            if (dias[i].equalsIgnoreCase(dia)) {
                String[] horas = (i < 5) ? horasSemana : horasSabado;
                for (int j = 0; j < horas.length; j++) {
                    if (horas[j].equals(hora)) {
                        if (cuposPorDiaHora[i][j] > 0) {
                            cuposPorDiaHora[i][j]--; // descuenta cupo
                            JOptionPane.showMessageDialog(null, "Reserva confirmada para " + dia + " a las " + hora);
                            return true;
                        } else {
                            JOptionPane.showMessageDialog(null, "No hay cupos disponibles en " + dia + " " + hora + ".");
                            return false;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Hora no válida para " + dias[i] + ".");
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "Día no válido.");
        return false;
    }

    // -------------------- Cancelar --------------------
    public boolean cancelarReserva(String dia, String hora) {
        for (int i = 0; i < dias.length; i++) {
            if (dias[i].equalsIgnoreCase(dia)) {
                String[] horas = (i < 5) ? horasSemana : horasSabado;
                for (int j = 0; j < horas.length; j++) {
                    if (horas[j].equals(hora)) {
                        int horaEntera = Integer.parseInt(hora.substring(0, 2));
                        int maxCupo = obtenerCuposPorHora(horaEntera);
                        if (cuposPorDiaHora[i][j] < maxCupo) {
                            cuposPorDiaHora[i][j]++; // devuelve cupo
                            JOptionPane.showMessageDialog(null, "Reserva cancelada para " + dia + " a las " + hora);
                            return true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Ese horario ya tiene el máximo de cupos.");
                            return false;
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Hora no válida para " + dias[i] + ".");
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "Día no válido.");
        return false;
    }

    // -------------------- Política de cupos por hora --------------------
    private int obtenerCuposPorHora(int horaEntera) {
        // Ajusta estas reglas si tu profe/cliente pide otra política
        if (horaEntera >= 7 && horaEntera <= 9) return 5;   // Alta demanda
        if (horaEntera >= 10 && horaEntera <= 12) return 4;
        if (horaEntera >= 13 && horaEntera <= 15) return 3;
        return 2; // resto (por ejemplo, 16:00)
    }

    // -------------------- Utilidades estáticas --------------------
    // Devuelve la lista predeterminada de centros (para AgendaReservas)
    public static CentroDePruebas[] listaCentros() {
        CentroDePruebas[] centros = new CentroDePruebas[4];

        centros[0] = new CentroDePruebas(
            "La Valencia",
            "La Valencia, Heredia. Frente al Walmart de Heredia."
        );
        centros[1] = new CentroDePruebas(
            "La Sabana",
            "San José. Costado oeste del Estadio Nacional, La Sabana."
        );
        centros[2] = new CentroDePruebas(
            "El Coyol",
            "Zona Industrial El Coyol, Alajuela. Contiguo a planta Dos Pinos."
        );
        centros[3] = new CentroDePruebas(
            "Limón",
            "Centro de Limón. 200 metros norte del Parque Vargas, calle principal."
        );

        return centros;
    }

    // Selector de centro (usado desde menús)
    public static int seleccionarCentro(CentroDePruebas[] centros) {
        if (centros == null || centros.length == 0) {
            JOptionPane.showMessageDialog(null, "No hay centros disponibles.");
            return -1;
        }

        String mensaje = "Seleccione un centro:\n";
        for (int i = 0; i < centros.length; i++) {
            mensaje += (i + 1) + ". " + centros[i].getNombre() + "\n";
        }

        String sel = JOptionPane.showInputDialog(mensaje);
        if (sel == null || sel.isEmpty()) return -1;

        // Validación numérica simple
        for (int k = 0; k < sel.length(); k++) {
            char c = sel.charAt(k);
            if (c < '0' || c > '9') return -1;
        }

        int idx = Integer.parseInt(sel) - 1;
        if (idx < 0 || idx >= centros.length) return -1;
        return idx;
    }
}
