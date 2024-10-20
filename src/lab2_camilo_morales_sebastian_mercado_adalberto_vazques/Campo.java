package lab2_camilo_morales_sebastian_mercado_adalberto_vazques;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println("Estrategia establecida: " + estrategia);
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
        // Aquí puedes implementar la lógica de búsqueda del camino según la estrategia
        // Esto puede usar BFS, DFS, o un algoritmo que tome en cuenta los atributos (velocidad, remate, posesión)
        // Por ahora se implementará una búsqueda básica.

        // Simulación de búsqueda
        List<Jugador> camino = new ArrayList<>();
        camino.add(inicio);  // Añadimos el nodo inicial al camino
        camino.add(objetivo);  // Añadimos el nodo objetivo al camino (simplificado)

        return camino;  // Devuelve el camino (aquí se puede mejorar)
    }
}