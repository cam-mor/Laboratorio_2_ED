/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_camilo_morales_sebastian_mercado_adalberto_vazques;

/**
 *
 * @author HP
 */
import java.io.*;
import java.util.*;

public class SimulacionFutbol {
    public static void main(String[] args) {
        Campo campo = new Campo();
        cargarJugadores("jugadores.csv", campo);
        cargarMatrizDeAdyacencia("matriz.csv", campo);
        
        
                System.out.println("Directorio actual: " + new File(".").getAbsolutePath()); //Debug donde se están colocando los archivos CSV


        Scanner scanner = new Scanner(System.in);
        System.out.println("Simulación de Movimiento Táctico en un Equipo de Fútbol");

        while (true) {
            System.out.print("Ingrese el nombre del jugador inicial: ");
            String inicio = scanner.nextLine();
            System.out.print("Ingrese el nombre del jugador objetivo: ");
            String objetivo = scanner.nextLine();
            System.out.print("Ingrese la estrategia (velocidad, posesion, remate): ");
            String estrategia = scanner.nextLine();

            campo.establecerEstrategia(estrategia);
            List<Jugador> camino = campo.calcularCaminoOptimo(inicio, objetivo, estrategia);

            if (camino.isEmpty()) {
                System.out.println("No se encontró un camino.");
            } else {
                System.out.println("Camino óptimo:");
                for (Jugador jugador : camino) {
                    System.out.println(jugador);
                }
            }

            System.out.print("¿Desea realizar otra simulación? (si/no): ");
            String continuar = scanner.nextLine();
            if (!continuar.equalsIgnoreCase("si")) {
                break;
            }
        }

        scanner.close();
    }

    private static void cargarJugadores(String archivo, Campo campo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                int velocidad = Integer.parseInt(datos[1]);
                int remate = Integer.parseInt(datos[2]);
                int posesion = Integer.parseInt(datos[3]);
                Jugador jugador = new Jugador(nombre, velocidad, remate, posesion);
                campo.agregarJugador(jugador);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cargarMatrizDeAdyacencia(String archivo, Campo campo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String jugador = datos[0];
                for (int i = 1; i < datos.length; i++) {
                    if (datos[i].equals("1")) {
                        campo.agregarConexion(jugador, "Jugador" + i);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


