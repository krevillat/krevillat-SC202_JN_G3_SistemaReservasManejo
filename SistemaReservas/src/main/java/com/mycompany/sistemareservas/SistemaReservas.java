package com.mycompany.sistemareservas;

import javax.swing.JOptionPane;

public class SistemaReservas {
    
    public static void main(String[] args) {
        
        //Se cargan usuarios predefinidos
        Usuario.cargarUsuariosFijos();

        //Decisión: Para determinar cual método se debe llamar primero
        String opcionInicio = "0";
        
        while (opcionInicio != "1" && opcionInicio != "2") {  
            opcionInicio = JOptionPane.showInputDialog(null, "Bienvenido \n\n¿Tiene una cuenta? \n1. Sí \n2. No \n3. Salir", "Cuenta", JOptionPane.QUESTION_MESSAGE);
           
            if (opcionInicio.equals("1")) {
                Usuario.iniciarSesion();
             
            }else if (opcionInicio.equals("2")) {
                Usuario.registrarUsuario(); //Llama método de registro
                
            }else if (opcionInicio.equals("3")) {
                System.exit(0);
                
            
            }else{
                JOptionPane.showMessageDialog(null, "Opción inválida.");
                    
            }
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
                    Usuario.editarUsuario();
                } else if (opcion.equals("4")) {
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema", "Salida del sistema", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
                }
            }
        } 
        
        
    }
}
