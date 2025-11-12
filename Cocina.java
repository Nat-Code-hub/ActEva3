import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/**
 * Simula el funcionamiento de una cocina con múltiples cocineros trabajando en paralelo.
 * Genera pedidos aleatorios, los distribuye entre hilos (cocineros) y registra el progreso en un log.
 */
public class Cocina {
    private static final String[] MENU = {
        "Pizza Funghi", "Lasaña de Carne", "Ensalada Griega",
        "Sopa de Cebolla", "Tacos de Cochinita", "Risotto de Setas",
        "Hamburguesa con Queso", "Pollo Tikka Masala", "Ramen Tonkotsu",
        "Filete Mignon", "Sushi Variado", "Paella Valenciana"
    };

    /**
     * Punto de entrada del programa. Crea pedidos, lanza cocineros e inicia la simulación.
     */
    public static void main(String[] args) {
        Random random = new Random();
        
        // Genera aleatoriamente entre 6 y 12 pedidos
        int cantidadPedidos = random.nextInt(7) + 6;
        
        // Genera aleatoriamente entre 3 y 4 cocineros
        int cantidadCocineros = random.nextInt(2) + 3;

        List<Pedido> listaPedidos = Collections.synchronizedList(new ArrayList<>());
        Object lock = new Object(); // Lock compartido por todos los cocineros

        System.out.println("=== APERTURA DE COCINA ===");
        System.out.println("Pedidos a preparar: " + cantidadPedidos);
        System.out.println("Cocineros disponibles: " + cantidadCocineros + "\n");

        // Genera pedidos aleatorios
        for (int i = 0; i < cantidadPedidos; i++) {
            String platoAleatorio = MENU[random.nextInt(MENU.length)];
            listaPedidos.add(new Pedido(i + 1, platoAleatorio));
        }

        // Mostrar pedidos pendientes
        System.out.println("Pedidos pendientes:");
        for (Pedido p : listaPedidos) {
            System.out.println("   - " + p);
        }
        System.out.println();

        try (PrintWriter log = new PrintWriter(new FileWriter("log_pedidos.txt"))) {
            log.println("--- INICIO DEL SERVICIO ---");
            log.println("Total de pedidos: " + cantidadPedidos);
            log.println("Total de cocineros: " + cantidadCocineros);
            log.println();

            
            // Crear y arrancar cocineros dinámicamente
            Cocinero[] cocineros = new Cocinero[cantidadCocineros];
            for (int i = 0; i < cantidadCocineros; i++) {
                cocineros[i] = new Cocinero("Chef"+i, listaPedidos, log, lock);
                cocineros[i].start();
            }

            // Esperar a que terminen todos
            for (Cocinero cocinero : cocineros) {
                cocinero.join();
            }

            System.out.println("\n=== TODOS LOS PEDIDOS COMPLETADOS ===");
            log.println();
            log.println("--- FIN DEL SERVICIO ---");

        } catch (IOException e) {
            System.err.println("Error al manejar el archivo de log: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("El hilo principal fue interrumpido.");
        }
    }
}
