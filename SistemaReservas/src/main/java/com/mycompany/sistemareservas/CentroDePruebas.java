package com.mycompany.sistemareservas;

import javax.swing.JOptionPane;

public class CentroDePruebas {

    private String nombre;
    private String direccion;

    // Días: Lunes a Viernes
    private String[] dias;
    // Horas reducidas: 07:00 a 14:00
    private String[] horasSemana;

    // cuposPorDiaHora[día][hora] -> cupos disponibles
    private int[][] cuposPorDiaHora;

    // Constructor --------------------
    public CentroDePruebas(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;

        // Días: L-V
        dias = new String[5];
        dias[0] = "Lunes";
        dias[1] = "Martes";
        dias[2] = "Miércoles";
        dias[3] = "Jueves";
        dias[4] = "Viernes";

        // Horas: 07:00 a 14:00
        horasSemana = new String[8];
        horasSemana[0] = "07:00";
        horasSemana[1] = "08:00";
        horasSemana[2] = "09:00";
        horasSemana[3] = "10:00";
        horasSemana[4] = "11:00";
        horasSemana[5] = "12:00";
        horasSemana[6] = "13:00";
        horasSemana[7] = "14:00";

        // Inicializar cupos por día y hora
        cuposPorDiaHora = new int[dias.length][horasSemana.length];
        for (int i = 0; i < dias.length; i++) {
            for (int j = 0; j < horasSemana.length; j++) {
                String hs = horasSemana[j];
                int horaEntera = Integer.parseInt(hs.substring(0, 2));
                cuposPorDiaHora[i][j] = obtenerCuposPorHora(horaEntera);
            }
        }
    }

    // -------------------- Getters --------------------
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }

    // disponibilidad --------------------
    public void mostrarDisponibilidad() {
        String disponibilidad = "Disponibilidad de " + nombre + ":\n" + direccion + "\n\n";
        for (int i = 0; i < dias.length; i++) {
            disponibilidad += dias[i] + ":\n";
            for (int j = 0; j < horasSemana.length; j++) {
                disponibilidad += horasSemana[j] + " -> " + cuposPorDiaHora[i][j] + " cupos\n";
            }
            disponibilidad += "\n";
        }
        JOptionPane.showMessageDialog(null, disponibilidad, "Disponibilidad", JOptionPane.INFORMATION_MESSAGE);
    }

    //  Reservar --------------------
    public boolean agendaReserva(String dia, String hora) {
        for (int i = 0; i < dias.length; i++) {
            if (dias[i].equalsIgnoreCase(dia)) {
                for (int j = 0; j < horasSemana.length; j++) {
                    if (horasSemana[j].equals(hora)) {
                        if (cuposPorDiaHora[i][j] > 0) {
                            cuposPorDiaHora[i][j]--;
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

    //  Cancelar --------------------
    public boolean cancelarReserva(String dia, String hora) {
        for (int i = 0; i < dias.length; i++) {
            if (dias[i].equalsIgnoreCase(dia)) {
                for (int j = 0; j < horasSemana.length; j++) {
                    if (horasSemana[j].equals(hora)) {
                        int horaEntera = Integer.parseInt(hora.substring(0, 2));
                        int maxCupo = obtenerCuposPorHora(horaEntera);
                        if (cuposPorDiaHora[i][j] < maxCupo) {
                            cuposPorDiaHora[i][j]++;
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

 
    private int obtenerCuposPorHora(int horaEntera) {
        // Ajusta la regla si lo necesitas
        if (horaEntera >= 7 && horaEntera <= 9) return 5;
        if (horaEntera >= 10 && horaEntera <= 12) return 4;
        if (horaEntera >= 13 && horaEntera <= 14) return 3;
        return 2;
    }

    public static CentroDePruebas[] listaCentros() {
        CentroDePruebas[] centros = new CentroDePruebas[4];
        centros[0] = new CentroDePruebas("La Valencia", "La Valencia, Heredia. Frente al Walmart de Heredia.");
        centros[1] = new CentroDePruebas("La Sabana", "San José. Costado oeste del Estadio Nacional, La Sabana.");
        centros[2] = new CentroDePruebas("El Coyol", "Zona Industrial El Coyol, Alajuela. Contiguo a planta Dos Pinos.");
        centros[3] = new CentroDePruebas("Limón", "Centro de Limón. 200 metros norte del Parque Vargas, calle principal.");
        return centros;
    }

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
        for (int k = 0; k < sel.length(); k++) {
            char c = sel.charAt(k);
            if (c < '0' || c > '9') return -1;
        }
        int idx = Integer.parseInt(sel) - 1;
        if (idx < 0 || idx >= centros.length) return -1;
        return idx;
    }
}
