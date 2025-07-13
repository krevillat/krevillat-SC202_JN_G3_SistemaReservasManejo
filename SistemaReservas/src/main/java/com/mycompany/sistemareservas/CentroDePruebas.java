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
// Constructor que se inicializa cuando se crea un objeto de Centro de Oruebas
    public CentroDePruebas(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.cuposPorHora = new int[horas.length];
        //bucle inicializa cada posición del arreglo con el valor 15.
        for (int i = 0; i < cuposPorHora.length; i++) {
            cuposPorHora[i] = 15; 
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void mostrarDisponibilidad() {
        String info = "Horarios disponibles:\n";  // Esta variable concatena los textos. 
        //En cada vuelta del for, se agrega a info una nueva línea con la hora y la cantidad de espacios.
        for (int i = 0; i < horas.length; i++) {
            info += horas[i] + " → " + cuposPorHora[i] + " espacios\n";
        }
        JOptionPane.showMessageDialog(null, info);
}

//    public void agregarHorario(String hora) {
//        for (int i = 0; i < horas.length; i++) {
//            if (horas[i].equals(hora)) {
//                if (cuposPorHora[i] > 0) {
//                    cuposPorHora[i]--;
//                    JOptionPane.showMessageDialog(null, "Nuevo horario agregadocon éxito en " + hora);
//                } else {
//                    JOptionPane.showMessageDialog(null, "No hay espacios disponibles en ese horario.");
//                }
//                return;
//            }
//        }
//        JOptionPane.showMessageDialog(null, "Horario no válido.");
//    }
//
//    public void eliminarHorario(String hora) {
//        for (int i = 0; i < horas.length; i++) {
//            if (horas[i].equals(hora)) {
//                if (cuposPorHora[i] < 15) {
//                    cuposPorHora[i]++;
//                    JOptionPane.showMessageDialog(null, "Eliminación exitosa para el horario " + hora);
//                } else {
//                    JOptionPane.showMessageDialog(null, "No hay reservas previas en ese horario.");
//                }
//                return;
//            }
//        }
//        JOptionPane.showMessageDialog(null, "Horario no válido.");
   
}
