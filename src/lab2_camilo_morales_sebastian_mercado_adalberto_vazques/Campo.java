/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2_camilo_morales_sebastian_mercado_adalberto_vazques;

/**
 *
 * @author HP
 */
import java.util.*;

public class Campo {
    private Map<Jugador, List<Jugador>> grafo;
    private Map<String, Jugador> nombreJugadorMap;

    public Campo() {
        this.grafo = new HashMap<>();
        this.nombreJugadorMap = new HashMap<>();
    }

    public void agregarJugador(Jugador jugador) {
        grafo.putIfAbsent(jugador, new ArrayList<>());
        nombreJugadorMap.put(jugador.getNombre(), jugador);
    }

    public void agregarConexion(String desde, String hacia) {
        Jugador jugadorDesde = nombreJugadorMap.get(desde);
        Jugador jugadorHacia = nombreJugadorMap.get(hacia);
        if (jugadorDesde != null && jugadorHacia != null) {
            grafo.get(jugadorDesde).add(jugadorHacia);
        }
    }

    public void establecerEstrategia(String estrategia) {
        System.out.println("Estrategia establecida: " + estrategia);
    }

    public List<Jugador> calcularCaminoOptimo(String inicio, String objetivo, String estrategia) {
        Jugador jugadorInicio = nombreJugadorMap.get(inicio);
        Jugador jugadorObjetivo = nombreJugadorMap.get(objetivo);
        if (jugadorInicio == null || jugadorObjetivo == null) {
            System.out.println("Jugadores no encontrados en el campo.");
            return Collections.emptyList();
        }

        return buscarCamino(jugadorInicio, jugadorObjetivo, estrategia);
    }

    private List<Jugador> buscarCamino(Jugador inicio, Jugador objetivo, String estrategia) {
        Set<Jugador> visitados = new HashSet<>();
        Queue<List<Jugador>> cola = new LinkedList<>();
        List<Jugador> caminoInicial = new ArrayList<>();
        caminoInicial.add(inicio);
        cola.add(caminoInicial);

        while (!cola.isEmpty()) {
            List<Jugador> camino = cola.poll();
            Jugador ultimo = camino.get(camino.size() - 1);

            if (ultimo.equals(objetivo)) {
                return camino;
            }

            visitados.add(ultimo);

            for (Jugador vecino : grafo.get(ultimo)) {
                if (!visitados.contains(vecino)) {
                    List<Jugador> nuevoCamino = new ArrayList<>(camino);
                    nuevoCamino.add(vecino);
                    cola.add(nuevoCamino);
                }
            }
        }

        return Collections.emptyList();
    }
}


