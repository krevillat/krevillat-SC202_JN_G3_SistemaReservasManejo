package com.mycompany.sistemareservas;

import javax.swing.JOptionPane;

public class CentroDePruebas {

    // Atributos
    private String nombre;
    private String direccion;
    private String[] horas = {"07:00", "08:00", "09:00", "10:00", "11:00", "12:00",
        "13:00", "14:00", "15:00", "16:00"};
    private boolean[][] disponibilidad = new boolean[10][15]; // 10 horas, 15 espacios por hora

    // Constructor
    public CentroDePruebas(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;

        // Inicializar disponibilidad en true
        for (int i = 0; i < disponibilidad.length; i++) {
            for (int j = 0; j < disponibilidad[i].length; j++) {
                disponibilidad[i][j] = true;
            }
        }
    }

    // Mostrar dirección
    public void mostrarDireccion() {
        JOptionPane.showMessageDialog(null, "Dirección: " + direccion);
    }

    // Mostrar horarios disponibles
    public void mostrarDisponibilidad() {
        String mensaje = "Disponibilidad en " + nombre + ":\n";
        for (int i = 0; i < horas.length; i++) {
            int disponibles = 0;
            for (int j = 0; j < disponibilidad[i].length; j++) {
                if (disponibilidad[i][j]) {
                    disponibles++;
                }
            }
            mensaje += horas[i] + ": " + disponibles + " espacios libres\n";
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    // Método para agregar un horario disponible (no reservado aún)
    public void agregarHorarioDisponible(String hora) {
        for (int i = 0; i < contadorHorarios; i++) {
            if (horariosDisponibles[i].equals(hora)) {
                JOptionPane.showMessageDialog(null, "Ese horario ya existe.");
                return;
            }
        }
        if (contadorHorarios < horariosDisponibles.length) {
            horariosDisponibles[contadorHorarios] = hora;
            contadorHorarios++;
            JOptionPane.showMessageDialog(null, "Horario agregado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden agregar más horarios.");
        }
    }

    // Método para eliminar un horario disponible
    public void eliminarHorario(String hora) {
        boolean encontrado = false;

        for (int i = 0; i < contadorHorarios; i++) {
            if (horariosDisponibles[i].equals(hora)) {
                // Desplazar los elementos a la izquierda
                for (int j = i; j < contadorHorarios - 1; j++) {
                    horariosDisponibles[j] = horariosDisponibles[j + 1];
                }
                horariosDisponibles[contadorHorarios - 1] = null;
                contadorHorarios--;
                encontrado = true;
                JOptionPane.showMessageDialog(null, "Horario eliminado correctamente.");
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "El horario ingresado no existe.");
        }
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
