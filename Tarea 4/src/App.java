import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Grafos grafo = new Grafos(5);

        // Prueba agregarArista
        System.out.println("🔹 Prueba agregarArista");
        grafo.agregarArista(0, 1, 10);
        grafo.agregarArista(0, 2, 3);
        grafo.agregarArista(1, 2, 1);
        grafo.agregarArista(1, 3, 2);
        grafo.agregarArista(2, 1, 4);
        grafo.agregarArista(2, 3, 8);
        grafo.agregarArista(2, 4, 2);
        grafo.agregarArista(3, 4, 7);
        validarMatriz(grafo.obtenerMatriz(), new int[][] {
            {0, 10, 3, 0, 0},
            {0, 0, 1, 2, 0},
            {0, 4, 0, 8, 2},
            {0, 0, 0, 0, 7},
            {0, 0, 0, 0, 0}
        });

        // Prueba eliminarArista
        System.out.println("\n🔹 Prueba eliminarArista");
        grafo.eliminarArista(2, 4);
        validarMatriz(grafo.obtenerMatriz(), new int[][] {
            {0, 10, 3, 0, 0},
            {0, 0, 1, 2, 0},
            {0, 4, 0, 8, 0}, // (2,4) eliminado
            {0, 0, 0, 0, 7},
            {0, 0, 0, 0, 0}
        });

        // Prueba BFS
        System.out.println("\n🔹 Prueba BFS desde nodo 0");
        validarLista(grafo.bfs(0), Arrays.asList(0, 1, 2, 3, 4));

        // Prueba DFS
        System.out.println("\n🔹 Prueba DFS desde nodo 0");
        validarLista(grafo.dfs(0), Arrays.asList(0, 1, 2, 3, 4));

        // Prueba Dijkstra
        System.out.println("\n🔹 Prueba Dijkstra desde nodo 0");
        validarArray(grafo.dijkstra(0), new int[]{0, 7, 3, 9, 16});

        // Prueba Bellman-Ford
        System.out.println("\n🔹 Prueba Bellman-Ford desde nodo 0");
        validarArray(grafo.bellmanFord(0), new int[]{0, 7, 3, 9, 16});

        // Prueba Floyd-Warshall
        System.out.println("\n🔹 Prueba Floyd-Warshall");
        validarMatriz(grafo.floydWarshall(), new int[][] {
            {0, 7, 3, 9, 16},
            {Integer.MAX_VALUE, 0, 1, 2, 9},
            {Integer.MAX_VALUE, 4, 0, 6, 13},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 7},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        });

        System.out.println("\n✅ TODAS LAS PRUEBAS PASARON EXITOSAMENTE ✅");
    }

    // Métodos auxiliares para validación
    private static void validarMatriz(int[][] obtenida, int[][] esperada) {
        if (Arrays.deepEquals(obtenida, esperada)) {
            System.out.println("✔️ Matriz correcta");
        } else {
            System.out.println("❌ ERROR: Matriz incorrecta");
            System.out.println("Esperado: " + Arrays.deepToString(esperada));
            System.out.println("Obtenido: " + Arrays.deepToString(obtenida));
        }
    }

    private static void validarLista(List<Integer> obtenida, List<Integer> esperada) {
        if (obtenida.equals(esperada)) {
            System.out.println("✔️ Lista correcta: " + obtenida);
        } else {
            System.out.println("❌ ERROR: Lista incorrecta");
            System.out.println("Esperado: " + esperada);
            System.out.println("Obtenido: " + obtenida);
        }
    }

    private static void validarArray(int[] obtenido, int[] esperado) {
        if (Arrays.equals(obtenido, esperado)) {
            System.out.println("✔️ Distancias correctas: " + Arrays.toString(obtenido));
        } else {
            System.out.println("❌ ERROR: Distancias incorrectas");
            System.out.println("Esperado: " + Arrays.toString(esperado));
            System.out.println("Obtenido: " + Arrays.toString(obtenido));
        }
    }
}
