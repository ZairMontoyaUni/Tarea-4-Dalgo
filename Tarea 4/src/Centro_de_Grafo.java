import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Centro_de_Grafo {

    public static void main(String[] args) throws Exception {
        try (InputStreamReader is = new InputStreamReader(System.in); BufferedReader br = new BufferedReader(is);) {
            // Leer el número de casos
            String line = br.readLine();
            String[] partes = line.trim().split(" ");
            int N = Integer.parseInt(partes[0]); // Número de nodos
            int M = Integer.parseInt(partes[1]); // Número de aristas

            Grafos grafo = new Grafos(N);
            // Procesar cada caso
            for (int i = 0; i < M; i++) {
                line = br.readLine();
                if (line == null || line.trim().isEmpty()) {
                    continue; // Saltar líneas vacías
                }

                // Dividir la línea en partes
                String[] parts = line.trim().split(" ");
                int u = Integer.parseInt(parts[0]); // n es el el  valor
                int v = Integer.parseInt(parts[1]); // v es el segundo valor
                int w = Integer.parseInt(parts[2]); // m es el tercer valor
                
                // Agregar la arista al grafo
                grafo.agregarArista(u, v, w);

                 // Crear un nuevo grafo con los valores leídos

                // Llamar a la función y luego mostrar el resultado
                  
            }
            // Leer el nodo de inicio
            System.out.println("Ingrese el centro de inicio:");
            String ingreso = br.readLine();
            if (ingreso == null || ingreso.trim().isEmpty()) {
                System.out.println((Integer) null);
                return; // Salir si la entrada es nula o vacía
            }
            int inicio = Integer.parseInt(ingreso.trim());
            System.out.println("Ingrese el centro de fin:");
            String ingreso2 = br.readLine();
            if (ingreso2 == null || ingreso2.trim().isEmpty()) {
                System.out.println((Integer) null);
                return; // Salir si la entrada es nula o vacía
            }
            int fin = Integer.parseInt(ingreso2.trim());
            try {
                int resultado = Centro_Grafo(grafo, inicio, fin);
                System.out.println(resultado);
            } catch (Exception e) {
                System.out.println((Integer) null);
            }
        } 
    }
    
    public static int Centro_Grafo(Grafos grafo, Integer inicio , Integer fin ){
        grafo.mostrarMatriz();
        int[] rutas = grafo.dijkstra(inicio);
        return rutas[fin] ;
    }
}
