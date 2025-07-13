package com.mycompany.sistemareservas;

import javax.swing.JOptionPane;

public class CentroDePruebas {
    
    private String nombre;
    private String direccion;
    private int[] cuposPorHora; // 15 cupos por cada hora
    private String[] horas = {
        "07:00", "08:00", "09:00", "10:00", "11:00",
        "12:00", "13:00", "14:00", "15:00", "16:00"
    };

    public CentroDePruebas(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.cuposPorHora = new int[horas.length];
        for (int i = 0; i < cuposPorHora.length; i++) {
            cuposPorHora[i] = 15; // Inicialmente 15 espacios por hora
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void mostrarDisponibilidad() {
        StringBuilder info = new StringBuilder("Horarios disponibles:\n");
        for (int i = 0; i < horas.length; i++) {
            info.append(horas[i]).append(" → ").append(cuposPorHora[i]).append(" espacios\n");
        }
        JOptionPane.showMessageDialog(null, info.toString());
    }

    public void reservarHorario(String hora) {
        for (int i = 0; i < horas.length; i++) {
            if (horas[i].equals(hora)) {
                if (cuposPorHora[i] > 0) {
                    cuposPorHora[i]--;
                    JOptionPane.showMessageDialog(null, "Reserva realizada con éxito en " + hora);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay espacios disponibles en ese horario.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Horario no válido.");
    }

    public void cancelarHorario(String hora) {
        for (int i = 0; i < horas.length; i++) {
            if (horas[i].equals(hora)) {
                if (cuposPorHora[i] < 15) {
                    cuposPorHora[i]++;
                    JOptionPane.showMessageDialog(null, "Cancelación exitosa para el horario " + hora);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay reservas previas en ese horario.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Horario no válido.");
    }
}
