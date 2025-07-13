
public class CentroDePruebas {
 // Atributos
    private String nombre;
    private String direccion;
    private String[] horariosDisponibles;
    private int contadorHorarios;

    // Constructor
    public CentroDePruebas(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.horariosDisponibles = new String[9];  //9hrs
        this.contadorHorarios = 0;
    }

    // Muestra todos los horarios disponibles en consola
    public void muestraHorarios() {
        if (contadorHorarios == 0) {
            System.out.println("No hay horarios disponibles actualmente.");
        } else {
            for (int i = 0; i < contadorHorarios; i++) {
                System.out.println("Horario " + (i + 1) + ": " + horariosDisponibles[i]);
            }
        }
    }

    // Muestra la dirección del centro de pruebas
    public void muestraDireccion() {
        System.out.println("Dirección del centro: " + direccion);
    }

    // Agrega un nuevo horario si hay espacio
    public void agregaHorario(String nuevoHorario) {
        if (contadorHorarios < horariosDisponibles.length) {
            horariosDisponibles[contadorHorarios] = nuevoHorario;
            contadorHorarios++;
            System.out.println("Horario agregado correctamente.");
        } else {
            System.out.println("No se pueden agregar más horarios. Capacidad máxima alcanzada.");
        }
    }

    // Elimina un horario si existe en la lista
    public void quitaHorario(String horario) {
        boolean encontrado = false;
        for (int i = 0; i < contadorHorarios; i++) {
            if (horariosDisponibles[i].equals(horario)) {
                // Mover los elementos hacia la izquierda para llenar el espacio
                for (int j = i; j < contadorHorarios - 1; j++) {
                    horariosDisponibles[j] = horariosDisponibles[j + 1];
                }
                horariosDisponibles[contadorHorarios - 1] = null;
                contadorHorarios--;
                encontrado = true;
                System.out.println("Horario eliminado correctamente.");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("El horario no fue encontrado en la lista.");
        }
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getCantidadHorarios() {
        return contadorHorarios;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}   

