package lab2_camilo_morales_sebastian_mercado_adalberto_vazques;

import java.io.*;
import java.util.*;

public class SimulacionFutbol {

    public static void main(String[] args) {
        Campo campo = new Campo();
        cargarJugadores("jugadores.csv", campo);
        cargarMatrizDeAdyacencia("matriz.csv", campo);

        System.out.println("-----------------------------------");
        System.out.println("Directorio actual: " + new File(".").getAbsolutePath());  // Debug para verificar la ruta de archivos CSV
        System.out.println("-----------------------------------");
        System.out.println("");
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------");
        System.out.println("Simulacion de Movimiento Tactico en un Equipo de Futbol");
        System.out.println("-----------------------------------");
        System.out.println("");

        while (true) {
            String inicio;
            String objetivo;
            String estrategia;
            String continuar;

            // Validación para el jugador inicial
            while (true) {
                System.out.print("Ingrese el nombre del jugador inicial: ");
                inicio = scanner.nextLine();
                if (campo.obtenerJugador(inicio) != null) {  // Verificar que el jugador exista
                    break;
                } else {
                    System.out.println("Jugador no encontrado. Por favor, ingrese un nombre valido.");
                }
            }

            // Validación para el jugador objetivo
            while (true) {
                System.out.print("Ingrese el nombre del jugador objetivo: ");
                objetivo = scanner.nextLine();
                if (campo.obtenerJugador(objetivo) != null) {  // Verificar que el jugador exista
                    break;
                } else {
                    System.out.println("Jugador no encontrado. Por favor, ingrese un nombre valido.");
                }
            }

            // Validación para la estrategia
            while (true) {
                System.out.print("Ingrese la estrategia (velocidad, posesion, remate): ");
                estrategia = scanner.nextLine();
                if (estrategia.equalsIgnoreCase("velocidad")
                        || estrategia.equalsIgnoreCase("posesion")
                        || estrategia.equalsIgnoreCase("remate")) {
                    break;
                } else {
                    System.out.println("Estrategia no valida. Por favor, ingrese una de las siguientes opciones: velocidad, posesion, remate.");
                }
            }

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

            // Validación para continuar o finalizar la simulación
            while (true) {
                System.out.print("Desea realizar otra simulacion? (si/no): ");
                continuar = scanner.nextLine();
                if (continuar.equalsIgnoreCase("si") || continuar.equalsIgnoreCase("no")) {
                    break;
                } else {
                    System.out.println("Respuesta no valida. Por favor, ingrese 'si' o 'no'.");
                }
            }

            if (continuar.equalsIgnoreCase("no")) {
                break;  // Finalizar el ciclo si la respuesta es "no"
            }
        }
    }
    // Método para cargar los jugadores desde un archivo CSV

    private static void cargarJugadores(String archivo, Campo campo) {
        //Colores
        String ANSI_GREEN = "\033[32;1m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_BLUE = "\u001B[34m";
        String reset = "\033[0m";
        String ANSI_MAGENTA = "\u001B[35m";
        String ANSI_YELLOW = "\u001B[33m";
        //

        System.out.println("Lista de jugadores disponibles:");

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
                        // Validación de rango de los atributos
                        if (velocidad < 0 || velocidad > 100 || remate < 0 || remate > 100 || posesion < 0 || posesion > 100) {
                            System.out.println("Error: Atributos fuera de rango (0-100) para el jugador: " + nombre);
                            continue;
                        }
                        // Crear y agregar el jugador al campo
                        Jugador jugador = new Jugador(nombre, velocidad, remate, posesion);
                        campo.agregarJugador(jugador);

                        // Imprimir en consola los detalles del jugador
                        System.out.println("Jugador: " + nombre + " [" + ANSI_RED + "| Velocidad: " + velocidad + " | " + reset + "- " + ANSI_YELLOW + "| Remate: " + remate + " | " + reset + "- " + ANSI_GREEN + "| Posesion: " + posesion + " | " + reset + "]");

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
