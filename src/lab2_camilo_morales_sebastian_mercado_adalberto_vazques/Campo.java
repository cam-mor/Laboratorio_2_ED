package lab2_camilo_morales_sebastian_mercado_adalberto_vazques;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Campo {

    private Map<String, Jugador> grafo;  // Mapa que representa el grafo de nodos (jugadores)

    public Campo() {
        this.grafo = new HashMap<>();
    }

    // Agrega un nuevo nodo (jugador) al grafo
    public void agregarJugador(Jugador jugador) {
        grafo.put(jugador.getNombre(), jugador);
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
        }

        // Lógica para calcular el camino óptimo (se puede usar BFS, DFS o Dijkstra dependiendo de la estrategia)
        // Aquí puedes implementar una versión básica de BFS como ejemplo
        return buscarCamino(jugadorInicio, porteria, estrategia);
    }

    // Implementación de un algoritmo de búsqueda para encontrar el camino óptimo
    private List<Jugador> buscarCamino(Jugador inicio, Jugador objetivo, String estrategia) {
        // Mapa para guardar las distancias mínimas de cada jugador desde el nodo de inicio
        Queue<Jugador> cola = new LinkedList<>();
        Map<Jugador, Jugador> predecesor = new HashMap<>();
        Set<Jugador> visitado = new HashSet<>();

        cola.add(inicio);
        visitado.add(inicio);

        while (!cola.isEmpty()) {
            Jugador actual = cola.poll();

            if (actual.equals(objetivo)) {
                // Se encontró el camino
                List<Jugador> camino = new ArrayList<>();
                for (Jugador at = objetivo; at != null; at = predecesor.get(at)) {
                    camino.add(at);
                }
                Collections.reverse(camino);  // Invertir para obtener el camino desde el inicio
                return camino;
            }

            for (Jugador vecino : actual.getConexiones()) {
                if (!visitado.contains(vecino)) {
                    predecesor.put(vecino, actual);
                    visitado.add(vecino);
                    cola.add(vecino);
                }
            }
        }
        return new ArrayList<>();  // Si no se encuentra camino
    }

    private int calcularCosto(Jugador actual, Jugador vecino, String estrategia) {
        // Calculamos el costo entre dos nodos basado en la estrategia seleccionada
        switch (estrategia.toLowerCase()) {
            case "velocidad":
                return 100 - vecino.getVelocidad();  // Prioriza la velocidad más alta
            case "remate":
                return 100 - vecino.getRemate();  // Prioriza la habilidad de remate más alta
            case "posesion":
                return 100 - vecino.getPosesion();  // Prioriza la posesión más alta
            default:
                return 1;  // Si no hay estrategia definida, se usa un costo fijo
        }
    }

    public Jugador obtenerJugador(String nombreJugador) {
        return grafo.get(nombreJugador);  // Devuelve el jugador si existe, o null si no está en el grafo
    }
}
