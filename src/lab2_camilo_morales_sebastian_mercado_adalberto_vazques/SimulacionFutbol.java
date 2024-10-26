package lab2_camilo_morales_sebastian_mercado_adalberto_vazques;

import Interfaz.Inicio;
import java.io.*;
import java.util.*;

public class SimulacionFutbol {

    public static void main(String[] args) {
        Inicio n = new Inicio();
        n.setVisible(true);

        Campo campo = new Campo();
        cargarJugadores("jugadores.csv", campo);
        cargarMatrizDeAdyacencia("matriz.csv", campo);
        agregarPorteria(campo);  // Añadir nodo "Porteria" y conexiones
        campo.imprimirGrafo();

        System.out.println("Directorio actual: " + new File(".").getAbsolutePath());  // Debug para verificar la ruta de archivos CSV

    }
    // Método para cargar los jugadores desde un archivo CSV

    public static void cargarJugadores(String archivo, Campo campo) {

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {

                    String nombre = datos[0];
                    int velocidad = Integer.parseInt(datos[1]);
                    int remate = Integer.parseInt(datos[2]);
                    int posesion = Integer.parseInt(datos[3]);

                    // Crear y agregar el jugador al campo
                    Jugador jugador = new Jugador(nombre, velocidad, remate, posesion);
                    campo.agregarJugador(jugador);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar la matriz de adyacencia desde un archivo CSV
    public static void cargarMatrizDeAdyacencia(String archivo, Campo campo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String jugador = datos[0];  // El primer elemento es el nombre del jugador

                for (int i = 1; i < datos.length; i++) {
                    if (datos[i].equals("1")) {
                        String nombreVecino = campo.obtenerJugadorPorIndice(i - 1);
                        campo.agregarConexion(jugador, nombreVecino);

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void agregarPorteria(Campo campo) {
        Jugador porteria = new Jugador("Porteria", 0, 0, 0);  // Nodo especial sin atributos
        campo.agregarJugador(porteria);
        campo.agregarConexion("Procyon", "Porteria");
        campo.agregarConexion("Porteria", "Procyon");
        campo.agregarConexion("Archenar", "Porteria");
        campo.agregarConexion("Porteria", "Archenar");
    }
}
