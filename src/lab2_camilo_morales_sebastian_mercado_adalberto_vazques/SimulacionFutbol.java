package lab2_camilo_morales_sebastian_mercado_adalberto_vazques;

import java.io.*;
import java.util.*;

public class SimulacionFutbol {

    public static void main(String[] args) {
        Campo campo = new Campo();
        cargarJugadores("jugadores.csv", campo);
        cargarMatrizDeAdyacencia("matriz.csv", campo);

        System.out.println("Directorio actual: " + new File(".").getAbsolutePath());  // Debug para verificar la ruta de archivos CSV

        Scanner scanner = new Scanner(System.in);
        System.out.println("Simulacion de Movimiento Tactico en un Equipo de Futbol");

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
                System.out.println("No se encontro un camino.");
            } else {
                System.out.println("Camino optimo:");
                for (Jugador jugador : camino) {
                    System.out.println(jugador);
                }
            }

            System.out.print("Desea realizar otra simulacion? (si/no): ");
            String continuar = scanner.nextLine();
            if (!continuar.equalsIgnoreCase("si")) {
                break;
            }
        }

        scanner.close();
    }

    // Método para cargar los jugadores desde un archivo CSV
    private static void cargarJugadores(String archivo, Campo campo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    try {
                        String nombre = datos[0];
                        int velocidad = Integer.parseInt(datos[1]);
                        int remate = Integer.parseInt(datos[2]);
                        int posesion = Integer.parseInt(datos[3]);

                        if (velocidad < 0 || velocidad > 100 || remate < 0 || remate > 100 || posesion < 0 || posesion > 100) {
                            System.out.println("Error: Atributos fuera de rango (0-100) para el jugador: " + nombre);
                            continue;
                        }

                        Jugador jugador = new Jugador(nombre, velocidad, remate, posesion);
                        campo.agregarJugador(jugador);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Formato incorrecto en la línea: " + linea);
                    }
                } else {
                    System.out.println("Error: Línea mal formateada: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar la matriz de adyacencia desde un archivo CSV
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
