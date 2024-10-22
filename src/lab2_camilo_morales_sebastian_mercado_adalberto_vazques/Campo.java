package lab2_camilo_morales_sebastian_mercado_adalberto_vazques;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

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
    public List<Jugador> calcularCaminoOptimo(String inicio, String objetivo, String estrategia) {
        Jugador nodoInicio = grafo.get(inicio);
        Jugador nodoObjetivo = grafo.get(objetivo);
        if (nodoInicio == null || nodoObjetivo == null) {
            System.out.println("Jugadores no encontrados.");
            return new ArrayList<>();
        }

        return buscarCamino(nodoInicio, nodoObjetivo, estrategia);
    }

    // Implementación de un algoritmo de búsqueda para encontrar el camino óptimo
    private List<Jugador> buscarCamino(Jugador inicio, Jugador objetivo, String estrategia) {
        // Mapa para guardar las distancias mínimas de cada jugador desde el nodo de inicio
        Map<Jugador, Integer> distancias = new HashMap<>();
        Map<Jugador, Jugador> predecesores = new HashMap<>();
        PriorityQueue<Jugador> colaPrioridad = new PriorityQueue<>(Comparator.comparingInt(distancias::get));

        // Inicializar las distancias con valores altos (infinito)
        for (Jugador jugador : grafo.values()) {
            distancias.put(jugador, Integer.MAX_VALUE);
        }
        distancias.put(inicio, 0);
        colaPrioridad.add(inicio);

        while (!colaPrioridad.isEmpty()) {
            Jugador actual = colaPrioridad.poll();

            if (actual.equals(objetivo)) {
                break;  // Camino encontrado
            }

            for (Jugador vecino : actual.getConexiones()) {
                int nuevoCosto = distancias.get(actual) + calcularCosto(actual, vecino, estrategia);

                if (nuevoCosto < distancias.get(vecino)) {
                    distancias.put(vecino, nuevoCosto);
                    predecesores.put(vecino, actual);
                    colaPrioridad.add(vecino);
                }
            }
        }

        // Reconstruir el camino desde el nodo objetivo hacia el nodo de inicio
        List<Jugador> camino = new ArrayList<>();
        Jugador paso = objetivo;
        while (paso != null) {
            camino.add(0, paso);  // Añadimos al principio del camino
            paso = predecesores.get(paso);
        }

        return camino;
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
}
