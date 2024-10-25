package lab2_camilo_morales_sebastian_mercado_adalberto_vazques;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Campo {

    private Map<String, Jugador> grafo;  // Mapa que representa el grafo de nodos (jugadores)
    private List<String> jugadoresPorIndice;

    public Campo() {
        this.grafo = new HashMap<>();
        this.jugadoresPorIndice = new ArrayList<>();
    }

    // Agrega un nuevo nodo (jugador) al grafo
    public void agregarJugador(Jugador jugador) {
        grafo.put(jugador.getNombre(), jugador);
        jugadoresPorIndice.add(jugador.getNombre());
    }

    public String obtenerJugadorPorIndice(int indice) {
        if (indice >= 0 && indice < jugadoresPorIndice.size()) {
            return jugadoresPorIndice.get(indice);
        }
        return null;  // Retorna null si el índice es inválido
    }

    // Agrega una conexión entre dos nodos (jugadores)
    public void agregarConexion(String desde, String hacia) {
        Jugador nodoDesde = grafo.get(desde);
        Jugador nodoHacia = grafo.get(hacia);
        if (nodoDesde != null && nodoHacia != null) {
            nodoDesde.agregarConexion(nodoHacia);
        }
    }

    // Método para obtener un nodo a partir del nombre del jugador
    public Jugador obtenerNodo(String nombreJugador) {
        return grafo.get(nombreJugador);
    }

    // Método para establecer la estrategia del equipo
    public void establecerEstrategia(String estrategia) {
        if (estrategia.equalsIgnoreCase("velocidad")
                || estrategia.equalsIgnoreCase("posesion")
                || estrategia.equalsIgnoreCase("remate")) {
            System.out.println("Estrategia establecida: " + estrategia);
        } else {
            System.out.println("Estrategia no reconocida. Usando estrategia por defecto.");
        }
    }

    // Calcula el camino óptimo entre dos nodos basado en una estrategia
    public List<Jugador> calcularCaminoOptimo(String inicio, String estrategia) {
        Jugador jugadorInicio = obtenerJugador(inicio);
        Jugador porteria = obtenerJugador("Porteria");

        if (jugadorInicio == null || porteria == null) {
            System.out.println("Jugador o porteria no encontrado.");
            return new ArrayList<>();
        } else {
            System.out.println("sirve?");
        }

        //Se aplica Dijkstra
        List<Jugador> camino = buscarCaminoDijkstra(jugadorInicio, porteria, estrategia);

        if (camino.isEmpty()) {
            System.out.println("No se encontro un camino.");
        } else {
            System.out.println("Tamanio del camino: " + camino.size());
            for (Jugador jugador : camino) {
                System.out.println("En el camino: " + jugador.getNombre());
            }
        }

        return camino;
    }

    // Implementación de un algoritmo de búsqueda para encontrar el camino óptimo
    private List<Jugador> buscarCaminoDijkstra(Jugador inicio, Jugador porteria, String estrategia) {
        // Mapa para almacenar las distancias mínimas desde el inicio a cada jugador
        Map<Jugador, Double> distancias = new HashMap<>();
        Map<Jugador, Jugador> predecesores = new HashMap<>();
        PriorityQueue<Jugador> cola = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));
        Set<Jugador> visitados = new HashSet<>();

        // Inicializar las distancias: 0 para el inicio, infinito para los demás
        for (Jugador jugador : grafo.values()) {
            distancias.put(jugador, Double.MAX_VALUE);
        }
        distancias.put(inicio, 0.0);
        cola.add(inicio);

        while (!cola.isEmpty()) {
            Jugador actual = cola.poll();

            // Si ya hemos visitado este nodo, lo ignoramos
            if (!visitados.add(actual)) {
                continue;
            }

            // Depuración: Imprimir el jugador que se está procesando
            System.out.println("Procesando jugador: " + actual.getNombre());

            // Si llegamos a la portería, terminamos
            if (actual.equals(porteria)) {
                break;
            }

            // Explorar las conexiones (vecinos) del jugador actual
            for (Jugador vecino : actual.getConexiones()) {
                System.out.println("Vecino: " + vecino.getNombre());  // Depuración

                if (visitados.contains(vecino)) {
                    continue;
                }

                // Calculamos el "peso" de la conexión según la estrategia
                double peso = calcularPeso(actual, vecino, estrategia);

                // Relajación de la arista
                double nuevaDistancia = distancias.get(actual) + peso;
                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    predecesores.put(vecino, actual);
                    cola.add(vecino);

                    System.out.println("Actualizando distancia para " + vecino.getNombre() + " a " + nuevaDistancia);
                    System.out.println("Predecesor de " + vecino.getNombre() + " es " + actual.getNombre());

                }
            }
        }
        System.out.println("Distancias finales:");
        for (Map.Entry<Jugador, Double> entry : distancias.entrySet()) {
            System.out.println("Jugador: " + entry.getKey().getNombre() + ", Distancia: " + entry.getValue());
        }

        System.out.println("Predecesores finales:");
        for (Map.Entry<Jugador, Jugador> entry : predecesores.entrySet()) {
            System.out.println("Jugador: " + entry.getKey().getNombre() + ", Predecesor: "
                    + (entry.getValue() != null ? entry.getValue().getNombre() : "null"));
        }

        // Reconstruir el camino desde la portería hacia el inicio usando el mapa de predecesores
        List<Jugador> camino = new ArrayList<>();
        for (Jugador at = porteria; at != null; at = predecesores.get(at)) {
            camino.add(at);
            System.out.println("Aniadiendo al camino: " + at.getNombre());
        }

        // Si no hay camino, devolver una lista vacía
        if (camino.isEmpty() || camino.get(camino.size() - 1) != inicio) {
            System.out.println("No se pudo reconstruir el camino desde la porteria.");
            return new ArrayList<>();  // No se encontró camino
        }

        // Invertir el camino para que vaya del inicio a la portería
        Collections.reverse(camino);
        return camino;
    }

    private double calcularPeso(Jugador actual, Jugador vecino, String estrategia) {
    switch (estrategia.toLowerCase()) {
        case "velocidad":
            return vecino.getNombre().equals("Porteria") ? 0.5 : (100.0 / vecino.getVelocidad());
        case "posesion":
            return vecino.getNombre().equals("Porteria") ? 0.5 : (100.0 / vecino.getPosesion());
        case "remate":
            return vecino.getNombre().equals("Porteria") ? 0.5 : (100.0 / vecino.getRemate());
        default:
            return 1.0;  // Peso uniforme si la estrategia no coincide
    }
}

    public Jugador obtenerJugador(String nombreJugador) {
        return grafo.get(nombreJugador);  // Devuelve el jugador si existe, o null si no está en el grafo
    }

    //Clase depuradora
    public void imprimirGrafo() {
        for (String nombre : grafo.keySet()) {
            Jugador jugador = grafo.get(nombre);
            System.out.println("Jugador: " + nombre);
            for (Jugador conexion : jugador.getConexiones()) {
                System.out.println("  Conectado a: " + conexion.getNombre());
            }
        }
    }

}
