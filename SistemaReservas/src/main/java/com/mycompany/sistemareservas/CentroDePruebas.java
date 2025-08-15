

//Elaborado por KREVILLAT//
//Esta clase tiene como finalidad gestionar las diferentes cedes y horarios para las pruebas de manejo. //


package com.mycompany.sistemareservas;

import javax.swing.JOptionPane;

public class CentroDePruebas {

    private String nombre;
    private String direccion;

    private String[] dias; // 6 días operativos
    private String[] horasSemana; // L–V
    private String[] horasSabado; // Sábado

    private int[][] cuposPorDiaHora; // [día][hora] cupos disponibles

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

        // Inicializar cupos
        cuposPorDiaHora = new int[dias.length][];
        for (int i = 0; i < dias.length; i++) {
            if (i < 5) { // Lunes a Viernes
                cuposPorDiaHora[i] = new int[horasSemana.length];
                for (int j = 0; j < horasSemana.length; j++) {
                    cuposPorDiaHora[i][j] = obtenerCuposPorHora(Integer.parseInt(horasSemana[j].substring(0, 2)));
                }
            } else { // Sábado
                cuposPorDiaHora[i] = new int[horasSabado.length];
                for (int j = 0; j < horasSabado.length; j++) {
                    cuposPorDiaHora[i][j] = obtenerCuposPorHora(Integer.parseInt(horasSabado[j].substring(0, 2)));
                }
            }
        }
    }

    public void mostrarDisponibilidad() {
        String disponibilidad = "Disponibilidad de " + nombre + ":\n\n";

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

    public boolean agendaReserva(String dia, String hora) {
        for (int i = 0; i < dias.length; i++) {
            if (dias[i].equalsIgnoreCase(dia)) {
                String[] horas = (i < 5) ? horasSemana : horasSabado;
                for (int j = 0; j < horas.length; j++) {
                    if (horas[j].equals(hora) && cuposPorDiaHora[i][j] > 0) {
                        cuposPorDiaHora[i][j]--;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean cancelarReserva(String dia, String hora) {
        for (int i = 0; i < dias.length; i++) {
            if (dias[i].equalsIgnoreCase(dia)) {
                String[] horas = (i < 5) ? horasSemana : horasSabado;
                for (int j = 0; j < horas.length; j++) {
                    if (horas[j].equals(hora)) {
                        cuposPorDiaHora[i][j]++;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int obtenerCuposPorHora(int hora) {
        if (hora >= 7 && hora <= 9) {
            return 5; // Horario de alta demanda
        } else if (hora >= 10 && hora <= 12) {
            return 4;
        } else if (hora >= 13 && hora <= 15) {
            return 3;
        } else {
            return 2;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public static int seleccionarCentro(CentroDePruebas[] centros) {
        String mensaje = "Seleccione un centro:\n";
        for (int i = 0; i < centros.length; i++) {
            mensaje += (i + 1) + ". " + centros[i].getNombre() + "\n";
        }

        int seleccion = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
        if (seleccion < 1 || seleccion > centros.length) {
            JOptionPane.showMessageDialog(null, "Selección inválida");
            return -1;
        }
        return seleccion - 1;
    }
}
