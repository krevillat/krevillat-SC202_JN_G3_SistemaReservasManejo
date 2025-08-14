package com.mycompany.sistemareservas;

import javax.swing.JOptionPane;

public class SistemaReservas {
    
    public static void main(String[] args) {
        
        //Se cargan usuarios predefinidos
        Usuario.cargarUsuariosFijos();

        //Decisión: Para determinar cual método se debe llamar primero
               boolean sesionIniciada = false;
        while (!sesionIniciada) {
            String opcionInicio = JOptionPane.showInputDialog(
                null,
                "Bienvenido \n\n¿Tiene una cuenta?\n1. Sí\n2. No\n3. Salir",
                "Cuenta",
                JOptionPane.QUESTION_MESSAGE
            );
            if (opcionInicio == null || "3".equals(opcionInicio)) return;

            switch (opcionInicio) {
                case "1":
                    sesionIniciada = Usuario.iniciarSesion();
                    if (!sesionIniciada) {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña inválidos.");
                    }
                    break;
                case "2":
                    Usuario.registrarUsuario();
                    sesionIniciada = Usuario.iniciarSesion();
                    if (!sesionIniciada) {
                        JOptionPane.showMessageDialog(null, "Credenciales inválidas tras el registro.");
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        

            //**************Agregar un break para el look o cambiar a Switch************
            String opcion = "";

            while (!opcion.equals("4")) {
                opcion = JOptionPane.showInputDialog(null,"Seleccione una opción:\n1. Gestión de reservas \n2. Gestión de usuarios\n3. Salir", "Menú principal\n", JOptionPane.QUESTION_MESSAGE);

                if (opcion == null) {
                    break; // Usuario cerró la ventana
                }

                switch (opcion) {
                    case "1":
                        AgendaReservas.mostrarMenu();
                        break;
                    case "2":
                        Usuario.editarUsuario();
                        break;
                    case "3":
                        JOptionPane.showMessageDialog(null, "Gracias por usar el sistema", "Salida del sistema", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida.");
                        break;
                }
            }
        } 
        
        
    }
}
