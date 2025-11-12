import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
/**
 * Hilo que representa un cocinero que procesa pedidos de forma concurrente.
 */
public class Cocinero extends Thread {
    private final String nombre;// Nombre del cocinero
    private final List<Pedido> pedidos;// Lista compartida de pedidos
    private final PrintWriter log;// Registro de acciones completadas
    private final Object lock;// Objeto para sincronización
    private static final Random random = new Random();

    /**
     * Crea un cocinero.
     * @param nombre nombre del cocinero
     * @param pedidos lista compartida de pedidos
     * @param log salida donde se escriben logs
     * @param lock objeto para sincronizar acceso a la lista
     */
    public Cocinero(String nombre, List<Pedido> pedidos, PrintWriter log, Object lock) {
        this.nombre = nombre;
        this.pedidos = pedidos;
        this.log = log;
        this.lock = lock;
    }

    /** Ejecuta el hilo: toma pedidos, los prepara y los registra. */
    @Override
    public void run() {
        while (true) {
            Pedido pedidoActual;
            
            synchronized (lock) {
                if (pedidos.isEmpty()) break;
                pedidoActual = pedidos.remove(0);
            }

            System.out.println("-> " + nombre + " está preparando el " + pedidoActual.getNombrePlato());

            try {
                Thread.sleep(random.nextInt(2000) + 1000);//Dejamos solo el Thread ya que el catch se enfoca solo en ver la interrupcion de este.
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("El cocinero " + nombre + " fue interrumpido.");
                break;
            }   
                            
            String mensaje = "Completado: " + nombre + " terminó el " + pedidoActual;
                System.out.println(mensaje);

             synchronized (lock) {
                    log.println(mensaje);
                    log.flush();
            }
        }
    }
}

