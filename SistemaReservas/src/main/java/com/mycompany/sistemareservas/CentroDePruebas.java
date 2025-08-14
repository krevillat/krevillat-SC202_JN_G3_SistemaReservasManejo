

//Elaborado por KREVILLAT//
//Esta clase tiene como finalidad gestionar las diferentes cedes y horarios para las pruebas de manejo. //


package com.mycompany.sistemareservas;

import javax.swing.JOptionPane;

public class CentroDePruebas {

    
    private String nombre;
    private String direccion;

    // Matriz que guarda los cupos por día y hora
    private int[][] cuposPorDiaHora;

    // Arreglo con los días disponibles (lunes a sábado)
    private String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};

    // Horarios disponibles de lunes a viernes (de 7:00 a.m. a 4:00 p.m.)
    private String[] horasSemana = {
        "07:00", "08:00", "09:00", "10:00", "11:00",
        "12:00", "13:00", "14:00", "15:00", "16:00"
    };

    // Horarios disponibles el sábado (de 7:00 a.m. a 12:00 m.d.)
    private String[] horasSabado = {
        "07:00", "08:00", "09:00", "10:00", "11:00", "12:00"
    };

    // Constructor: inicializa el centro y asigna los cupos por hora
    public CentroDePruebas(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;

        // Se inicializa la matriz de cupos con tantas filas como días
        cuposPorDiaHora = new int[dias.length][];

        // Recorremos cada día para asignar los horarios y cupos
        for (int i = 0; i < dias.length; i++) {
            if (i == 5) { // Sábado
                cuposPorDiaHora[i] = new int[horasSabado.length];
                for (int j = 0; j < horasSabado.length; j++) {
                    int hora = Integer.parseInt(horasSabado[j].substring(0, 2)); // Toma los dos primeros digitos 
                    cuposPorDiaHora[i][j] = obtenerCuposPorHora(hora); //Asigna la cantidad de cupos disponibles para el día i y la hora j
                }
            } else { // Lunes a Viernes
                cuposPorDiaHora[i] = new int[horasSemana.length];
                for (int j = 0; j < horasSemana.length; j++) {
                    int hora = Integer.parseInt(horasSemana[j].substring(0, 2));
                    cuposPorDiaHora[i][j] = obtenerCuposPorHora(hora);
                }
            }
        }
    }

    // Este método asigna la cantidad de cupos según la hora del día
    public int obtenerCuposPorHora(int hora) {
        if (hora >= 7 && hora <= 11) return 15; // mañana
        if (hora >= 12 && hora <= 14) return 8; // medio día
        if (hora == 15) return 4;               // tarde
        if (hora == 16) return 7;               // cierre
        return 0;
    }

    // Muestra la disponibilidad  
    public void mostrarDisponibilidad() {
        String mensaje = String.format("%-8s", "Hora");

        // Encabezado con los días de la semana
        for (int i = 0; i < dias.length; i++) {
            mensaje += String.format("%-10s", dias[i]);
        }
        mensaje += "\n";

        // Recorrer todas las horas de lunes a viernes
        for (int h = 0; h < horasSemana.length; h++) {
            mensaje += String.format("%-8s", horasSemana[h]);

            for (int d = 0; d < dias.length; d++) {
                String[] horas = (d == 5) ? horasSabado : horasSemana;

                // Si el horario existe para ese día, mostramos los cupos
                if (h < horas.length) {
                    mensaje += String.format("%-10d", cuposPorDiaHora[d][h]);
                } else {
                    // Si no hay ese horario (por ejemplo, sábado en la tarde), mostramos un guion
                    mensaje += String.format("%-10s", "-");
                }
            }

            mensaje += "\n";
        }

        // Mostrar la tabla en un cuadro de diálogo
        JOptionPane.showMessageDialog(null, mensaje);
    }

    // Método para reservar un cupo si hay disponibilidad
    public boolean agendaReserva(String dia, String hora) {
        int diaIndex = -1;
        // Buscar el índice del día
        for (int i = 0; i < dias.length; i++) {
            if (dias[i].equals(dia)) {
                diaIndex = i;
            }
        }

        if (diaIndex == -1) {
            JOptionPane.showMessageDialog(null, "Día no válido.");
            return false;
        }

        // Seleccionar el arreglo de horas según el día
        String[] horas = (diaIndex == 5) ? horasSabado : horasSemana;

        int horaIndex = -1;
        // Buscar el índice de la hora
        for (int j = 0; j < horas.length; j++) {
            if (horas[j].equals(hora)) {
                horaIndex = j;
            }
        }

        if (horaIndex == -1) {
            JOptionPane.showMessageDialog(null, "Hora no válida.");
            return false;
        }

        // Verificar disponibilidad
        if (cuposPorDiaHora[diaIndex][horaIndex] > 0) {
            cuposPorDiaHora[diaIndex][horaIndex]--; // reservar: se descuenta un cupo
            JOptionPane.showMessageDialog(null, "Reserva confirmada para " + dia + " a las " + hora);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No hay cupos disponibles.");
            return false;
        }
    }

    // Método para cancelar una reserva y devolver el cupo
    public boolean cancelarReserva(String dia, String hora) {
        int diaIndex = -1;
        // Buscar el índice del día
        for (int i = 0; i < dias.length; i++) {
            if (dias[i].equals(dia)) {
                diaIndex = i;
            }
        }

        if (diaIndex == -1) {
            JOptionPane.showMessageDialog(null, "Día no válido.");
            return false;
        }

        // Seleccionar el arreglo de horas correcto
        String[] horas = (diaIndex == 5) ? horasSabado : horasSemana;

        int horaIndex = -1;
        // Buscar el índice de la hora
        for (int j = 0; j < horas.length; j++) {
            if (horas[j].equals(hora)) {
                horaIndex = j;
            }
        }

        if (horaIndex == -1) {
            JOptionPane.showMessageDialog(null, "Hora no válida.");
            return false;
        }

        // Verificamos que no se pase del cupo máximo
        int horaNumerica = Integer.parseInt(hora.substring(0, 2));
        int maxCupo = obtenerCuposPorHora(horaNumerica);

        if (cuposPorDiaHora[diaIndex][horaIndex] < maxCupo) {
            cuposPorDiaHora[diaIndex][horaIndex]++; // se devuelve el cupo
            JOptionPane.showMessageDialog(null, "Reserva cancelada para " + dia + " a las " + hora);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Ese horario ya tiene el máximo de cupos.");
            return false;
        }
    }

    // Métodos para obtener el nombre y dirección del centro
    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }
    
       
    public static CentroDePruebas[] centros;

    public static CentroDePruebas[] listaCentros() {
        if (centros == null) {
            centros = new CentroDePruebas[4];
            centros[0] = new CentroDePruebas("La Valencia", "La Valencia, Heredia. Frente al Walmart de Heredia.");
            centros[1] = new CentroDePruebas("La Sabana", "San José. Costado oeste del Estadio Nacional, La Sabana.");
            centros[2] = new CentroDePruebas("El Coyol", "Zona Industrial El Coyol, Alajuela. Contiguo a planta Dos Pinos.");
            centros[3] = new CentroDePruebas("Limón", "Centro de Limón. 200 metros norte del Parque Vargas, calle principal.");
        }
        return centros;
    }

    public static int seleccionarCentro(CentroDePruebas[] centros) {
        while (true) {
            String mensaje = "Seleccione un centro de pruebas:\n";
            for (int i = 0; i < centros.length; i++) {
                mensaje += (i + 1) + ". " + centros[i].getNombre() + "\n";
                }

            String sel = JOptionPane.showInputDialog(mensaje);
            if (sel == null) return -1; 



            int index = Integer.parseInt(sel) - 1;
            if (index < 0 || index >= centros.length) {
                JOptionPane.showMessageDialog(null, "Opción fuera de rango.");
                continue;
                }
            return index;
            }
    }

    
  

    
}
