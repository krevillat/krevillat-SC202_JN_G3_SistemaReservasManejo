package com.mycompany.sistemareservas;

import javax.swing.JOptionPane;

public class SistemaReservas {
    
    public static void main(String[] args) {
        
        //Matrices: Almacenan información de usuarios
        static String[] nombres = new String[10];
        static String[] cedulas = new String[10];
        static String[] correos = new String[10];
        static String[] telefonos = new String[10];
        static String[] direcciones = new String[10];
        static String[] usuarios = new String[10];
        static String[] contrasenas = new String[10];

        static int cantidadUsuarios = 4; // Habrán 4 usuarios previamente definidos

        //Método: Carga los usuarios predefinidos
        public static void cargarUsuariosFijos() {
        
        nombres[0] = "Marvin Vargas";
        cedulas[0] = "1234";
        correos[0] = "mvargas@ufide.com";
        telefonos[0] = "17181920";
        direcciones[0] = "Alajuela";
        usuarios[0] = "mvargas";
        contrasenas[0] = "fide123";
        
        nombres[1] = "Gerald Lizano";
        cedulas[1] = "5678";
        correos[1] = "glizano@ufide.com";
        telefonos[1] = "12345678";
        direcciones[1] = "Heredia";
        usuarios[1] = "glizano";
        contrasenas[1] = "fide123";
        
        nombres[2] = "Valeria Lizano";
        cedulas[2] = "9101";
        correos[2] = "vlizano@ufide.com";
        telefonos[2] = "91001112";
        direcciones[2] = "Heredia";
        usuarios[2] = "vlizano";
        contrasenas[2] = "fide123";
        
        nombres[3] = "Karina Revillat";
        cedulas[3] = "1112";
        correos[3] = "krevillat@ufide.com";
        telefonos[3] = "13141516";
        direcciones[3] = "San Jose";
        usuarios[3] = "krevillat";
        contrasenas[3] = "fide123";
    }

        //Método: Registro de usuarios
        public static void registrarUsuario() {
            JOptionPane.showMessageDialog(null, "Registro de nuevo usuario", "Registro", JOptionPane.INFORMATION_MESSAGE);

            nombres[cantidadUsuarios] = JOptionPane.showInputDialog(null, "Digite su nombre completo:", "Registro",JOptionPane.QUESTION_MESSAGE);
            cedulas[cantidadUsuarios] = JOptionPane.showInputDialog(null, "Digite su cédula:", "Registro",JOptionPane.QUESTION_MESSAGE);
            correos[cantidadUsuarios] = JOptionPane.showInputDialog(null, "Digite su correo electrónico:", "Registro",JOptionPane.QUESTION_MESSAGE);
            telefonos[cantidadUsuarios] = JOptionPane.showInputDialog(null, "Digite su número telefónico:", "Registro",JOptionPane.QUESTION_MESSAGE);
            direcciones[cantidadUsuarios] = JOptionPane.showInputDialog(null, "Digite su dirección:", "Registro",JOptionPane.QUESTION_MESSAGE);
            usuarios[cantidadUsuarios] = JOptionPane.showInputDialog(null, "Digite un nombre de usuario:", "Registro",JOptionPane.QUESTION_MESSAGE);
            contrasenas[cantidadUsuarios] = JOptionPane.showInputDialog(null, "Digite una contraseña:", "Registro",JOptionPane.QUESTION_MESSAGE);

            JOptionPane.showMessageDialog(null, "Usuario registrado con éxito", "Registro",JOptionPane.INFORMATION_MESSAGE);
            cantidadUsuarios++;
    }

        //Método: Inicio de sesión
        public static boolean iniciarSesion() {
            boolean acceso = false;

            while (!acceso) {
                String user = JOptionPane.showInputDialog(null, "Ingrese nombre de usuario:", "Inicio de sesión", JOptionPane.QUESTION_MESSAGE);
                String pass = JOptionPane.showInputDialog(null, "Ingrese contraseña:", "Inicio de sesión",JOptionPane.QUESTION_MESSAGE);

                for (int i = 0; i < cantidadUsuarios; i++) {
                    if (usuarios[i] != null && usuarios[i].equals(user) && contrasenas[i].equals(pass)) {
                        JOptionPane.showMessageDialog(null, "Bienvenido " + nombres[i], "Acceso concedido", JOptionPane.INFORMATION_MESSAGE);
                        acceso = true;
                        break;
                }
            }

                if (!acceso) {
                    JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos \nIntente de nuevo", "Error de credenciales", JOptionPane.ERROR_MESSAGE);
            }
        }

            return acceso;
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
