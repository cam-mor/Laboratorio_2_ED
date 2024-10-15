/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_camilo_morales_sebastian_mercado_adalberto_vazques;

/**
 *
 * @author HP
 */
public class Jugador {
    private String nombre;
    private int velocidad;
    private int remate;
    private int posesion;

    public Jugador(String nombre, int velocidad, int remate, int posesion) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.remate = remate;
        this.posesion = posesion;
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

    @Override
    public String toString() {
        return nombre + " [V: " + velocidad + ", R: " + remate + ", P: " + posesion + "]";
    }
}

