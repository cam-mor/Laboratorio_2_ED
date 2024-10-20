package lab2_camilo_morales_sebastian_mercado_adalberto_vazques;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    private String nombre;
    private int velocidad;
    private int remate;
    private int posesion;
    private List<Jugador> conexiones;  // Lista de conexiones (vecinos) de este nodo

    public Jugador(String nombre, int velocidad, int remate, int posesion) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.remate = remate;
        this.posesion = posesion;
        this.conexiones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getRemate() {
        return remate;
    }

    public int getPosesion() {
        return posesion;
    }

    public void agregarConexion(Jugador jugador) {
        conexiones.add(jugador);
    }

    public List<Jugador> getConexiones() {
        return conexiones;
    }

    @Override
    public String toString() {
        return nombre + " [Velocidad: " + velocidad + ", Remate: " + remate + ", Posesion: " + posesion + "]";
    }
}
