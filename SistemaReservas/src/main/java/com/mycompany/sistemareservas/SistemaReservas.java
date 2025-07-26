package com.mycompany.sistemareservas;

import javax.swing.JOptionPane;

public class SistemaReservas {
    
    public static void main(String[] args) {
        
        //Se cargan usuarios predefinidos
        Usuario.cargarUsuariosFijos();

        //Decisión: Para determinar cual método se debe llamar primero
        String opcionInicio = JOptionPane.showInputDialog(null, "Bienvenido \n\n¿Tiene una cuenta? \n1. Sí \n2. No", "Cuenta", JOptionPane.QUESTION_MESSAGE);

        if (opcionInicio != null && opcionInicio.equals("2")) {
            Usuario.registrarUsuario(); //Llama método de registro
        }

        boolean sesionIniciada = Usuario.iniciarSesion(); //Llama método de inicio de sesión

        //Si se registra correctamente, pasa al menú principal
        if (sesionIniciada) {
            String opcion = "";

            //**************Agregar un break para el look o cambiar a Switch************

            while (!opcion.equals("4")) {
                opcion = JOptionPane.showInputDialog(null,"Seleccione una opción:\n1. Gestión de reservas \n2. Horarios disponibles \n3. Gestión de usuarios\n4. Salir", "Menú principal\n", JOptionPane.QUESTION_MESSAGE);

                if (opcion == null) {
                    break; // Usuario cerró la ventana
                }

                if (opcion.equals("1")) {
                    JOptionPane.showMessageDialog(null, "Sección en construcción", "Información", JOptionPane.WARNING_MESSAGE);
                } else if (opcion.equals("2")) {
                    JOptionPane.showMessageDialog(null, "Sección en construcción", "Información", JOptionPane.WARNING_MESSAGE);
                } else if (opcion.equals("3")) {
                    JOptionPane.showMessageDialog(null, "Sección en construcción", "Información", JOptionPane.WARNING_MESSAGE);
                } else if (opcion.equals("4")) {
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema", "Salida del sistema", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
                }
            }
        } 
        
        // Crear los centros de pruebas (Karina R.)
        CentroDePruebas[] centros = new CentroDePruebas[4];
        centros[0] = new CentroDePruebas("La Valencia", "La Valencia, Heredia. Frente al Walmart de Heredia.");
        centros[1] = new CentroDePruebas("La Sabana", "San José. Costado oeste del Estadio Nacional, La Sabana.");
        centros[2] = new CentroDePruebas("El Coyol", "Zona Industrial El Coyol, Alajuela. Contiguo a planta Dos Pinos.");
        centros[3] = new CentroDePruebas("Limón", "Centro de Limón. 200 metros norte del Parque Vargas, calle principal.");


        // Mostrar centros disponibles al usuario (Karina R.)
        String mensajeCentros = "Seleccione un centro de pruebas:\n";
        for (int i = 0; i < centros.length; i++) {
            mensajeCentros += (i + 1) + ". " + centros[i].getNombre() + "\n";
        }

        int seleccion = Integer.parseInt(JOptionPane.showInputDialog(mensajeCentros));

        if (seleccion < 1 || seleccion > centros.length) {
            JOptionPane.showMessageDialog(null, "Selección inválida. Saliendo del sistema.");
            return;
        }

        // Obtener el centro seleccionado (Karina R.)
        CentroDePruebas centroSeleccionado = centros[seleccion - 1];

        JOptionPane.showMessageDialog(
                null,
                "DETALLE DE SU SELECCION:\n\n"
                + "Nombre: " + centroSeleccionado.getNombre() + "\n"
                + "Dirección: " + centroSeleccionado.getDireccion(),
                "Centro de Pruebas Seleccionado",
                JOptionPane.INFORMATION_MESSAGE
        );


        // Mostrar disponibilidad en formato matriz (Karina R.)
        centroSeleccionado.mostrarDisponibilidad();

        // A partir de aquí va el menú principal
    }
}
