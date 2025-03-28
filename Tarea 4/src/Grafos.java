import java.util.*;

class Arista {
    int origen, destino, peso;
    public Arista(int origen, int destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
}

public class Grafos {
    private static final int INF = Integer.MAX_VALUE;;
    private final int[][] matriz;
    private final int numVertices;
    private final List<Arista> aristas;

    public Grafos(int numVertices) {
        this.numVertices = numVertices;
        this.matriz = new int[numVertices][numVertices];
        this.aristas = new ArrayList<>();
        
        for (int i = 0; i < numVertices; i++) {
            Arrays.fill(matriz[i], INF);
            matriz[i][i] = 0; // Distancia de un nodo a sí mismo es 0
        }
    }
    

    public void agregarArista(int origen, int destino, int peso) {
        if (origen >= 0 && origen < numVertices && destino >= 0 && destino < numVertices) {
            matriz[origen][destino] = peso;
            aristas.add(new Arista(origen, destino, peso));
        } else {
            throw new IllegalArgumentException("Índices fuera de rango.");
        }
    }

    public void eliminarArista(int origen, int destino) {
        if (origen >= 0 && origen < numVertices && destino >= 0 && destino < numVertices) {
            matriz[origen][destino] = INF;
            aristas.removeIf(arista -> arista.origen == origen && arista.destino == destino);
        } else {
            throw new IllegalArgumentException("Índices fuera de rango.");
        }
    }
    

    public int[][] obtenerMatriz() {
        return matriz;
    }

    public List<Integer> bfs(int inicio) {
        if (inicio < 0 || inicio >= numVertices) {
            throw new IllegalArgumentException("Nodo de inicio fuera de rango.");
        }
        boolean[] visitado = new boolean[numVertices];
        Queue<Integer> cola = new LinkedList<>();
        List<Integer> recorrido = new ArrayList<>();
        visitado[inicio] = true;
        cola.add(inicio);

        while (!cola.isEmpty()) {
            int nodo = cola.poll();
            recorrido.add(nodo);
            for (int i = 0; i < numVertices; i++) {
                if (matriz[nodo][i] != 0 && !visitado[i]) {
                    visitado[i] = true;
                    cola.add(i);
                }
            }
        }
        return recorrido;
    }

    public List<Integer> dfs(int inicio) {
        if (inicio < 0 || inicio >= numVertices) {
            throw new IllegalArgumentException("Nodo de inicio fuera de rango.");
        }
        boolean[] visitado = new boolean[numVertices];
        List<Integer> recorrido = new ArrayList<>();
        dfsAux(inicio, visitado, recorrido);
        return recorrido;
    }

    private void dfsAux(int nodo, boolean[] visitado, List<Integer> recorrido) {
        visitado[nodo] = true;
        recorrido.add(nodo);
        for (int i = 0; i < numVertices; i++) {
            if (matriz[nodo][i] != 0 && !visitado[i]) {
                dfsAux(i, visitado, recorrido);
            }
        }
    }

    public int[] dijkstra(int inicio) {
        if (inicio < 0 || inicio >= numVertices) {
            throw new IllegalArgumentException("Nodo de inicio fuera de rango.");
        }
        int[] distancias = new int[numVertices];
        boolean[] visitado = new boolean[numVertices];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[inicio] = 0;

        for (int i = 0; i < numVertices; i++) {
            int u = obtenerMinimo(distancias, visitado);
            if (u == -1) break;
            visitado[u] = true;

            for (int v = 0; v < numVertices; v++) {
                if (!visitado[v] && matriz[u][v] != INF && distancias[u] != Integer.MAX_VALUE) {
                    int nuevaDistancia = distancias[u] + matriz[u][v];
                    if (nuevaDistancia < distancias[v]) {
                        distancias[v] = nuevaDistancia;
                    }
                }
            }
        }
        return distancias;
    }

    private int obtenerMinimo(int[] distancias, boolean[] visitado) {
        int minDistancia = Integer.MAX_VALUE, minIndice = -1;
        for (int i = 0; i < numVertices; i++) {
            if (!visitado[i] && distancias[i] < minDistancia) {
                minDistancia = distancias[i];
                minIndice = i;
            }
        }
        return minIndice;
    }

    public int[] bellmanFord(int inicio) {
        if (inicio < 0 || inicio >= numVertices) {
            throw new IllegalArgumentException("Nodo de inicio fuera de rango.");
        }
        int[] distancias = new int[numVertices];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[inicio] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            for (Arista arista : aristas) {
                if (distancias[arista.origen] != Integer.MAX_VALUE &&
                    distancias[arista.origen] + arista.peso < distancias[arista.destino]) {
                    distancias[arista.destino] = distancias[arista.origen] + arista.peso;
                }
            }
        }

        for (Arista arista : aristas) {
            if (distancias[arista.origen] != Integer.MAX_VALUE && 
                distancias[arista.origen] + arista.peso < distancias[arista.destino]) {
                throw new IllegalStateException("¡Ciclo negativo detectado!");
            }
        }
        return distancias;
    }

    public int[][] floydWarshall() {
        int[][] distancias = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            System.arraycopy(matriz[i], 0, distancias[i], 0, numVertices);
        }

        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (distancias[i][k] != INF && distancias[k][j] != INF &&
                        distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                    }
                }
            }
        }
        
        return distancias;
    }

    public void mostrarMatriz() {
        System.out.println("Matriz de Adyacencia:");
        for (int[] fila : matriz) {
            for (int valor : fila) {
                System.out.print((valor == INF ? "INF" : valor) + "\t");
            }
            System.out.println();
        }
    }
    
}


