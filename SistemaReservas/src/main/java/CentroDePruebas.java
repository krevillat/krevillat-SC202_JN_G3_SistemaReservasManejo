
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
    this.horariosDisponibles = new String[10];
    this.contadorHorarios = 0;
}


    // Método para mostrar los horarios
    public void muestraHorarios() {
        for (int i = 0; i < contadorHorarios; i++) {
            System.out.println("Horario " + (i + 1) + ": " + horariosDisponibles[i]);
        }
    }

    // Método para mostrar la dirección
    public void muestraDireccion() {
        System.out.println("Dirección del centro: " + direccion);
    }

    // Método para agregar un horario
    public void agregaHorario(String nuevoHorario) {
        if (contadorHorarios < horariosDisponibles.length) {
            horariosDisponibles[contadorHorarios] = nuevoHorario;
            contadorHorarios++;
        } else {
            System.out.println("No se pueden agregar más horarios.");
        }
    }

    // Método para quitar un horario
    public void quitaHorario(String horario) {
        boolean encontrado = false;
        for (int i = 0; i < contadorHorarios; i++) {
            if (horariosDisponibles[i].equals(horario)) {
                // Mover los elementos hacia la izquierda
                for (int j = i; j < contadorHorarios - 1; j++) {
                    horariosDisponibles[j] = horariosDisponibles[j + 1];
                }
                horariosDisponibles[contadorHorarios - 1] = null;
                contadorHorarios--;
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("El horario no fue encontrado.");
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

    
    // setters
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    
}


