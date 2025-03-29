import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Tarea_4 {

    public static void main(String[] args) throws Exception {
        try (InputStreamReader is = new InputStreamReader(System.in); BufferedReader br = new BufferedReader(is);) {
            // Leer el número de casos
            String line = br.readLine();
            int casos = Integer.parseInt(line.trim());

            // Procesar cada caso
            for (int i = 0; i < casos; i++) {
                line = br.readLine();
                if (line == null || line.trim().isEmpty()) {
                    continue; // Saltar líneas vacías
                }

                // Dividir la línea en partes
                String[] parts = line.trim().split(" ");
                int n = Integer.parseInt(parts[0]); // n es el primer valor
                int j = Integer.parseInt(parts[1]); // j es el segundo valor
                int m = Integer.parseInt(parts[2]); // m es el tercer valor

                // Extraer el arreglo arr (los valores restantes en la línea)
                int[] arr = new int[n];
                for (int k = 0; k < n; k++) {
                    arr[k] = Integer.parseInt(parts[3 + k]);
                }

                // Llamar a la función y luego mostrar el resultado
                try {
                    Integer resultado = 0;
                    System.out.println(resultado);
                } catch (Exception e) {
                    System.out.println((Integer) null);
                }
                
            
            }
        } 
    }
    

    public void Centro_Grafo(){

    }

    public void Conexcion_Computadores(){

    }

    public void Ordenamiento_Tareas(){

    }

    public void Tiempos_Transporte(){

    }
}
