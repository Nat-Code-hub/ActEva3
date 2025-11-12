import threading
import time
import random

class Cocinero(threading.Thread):
    """Hilo que representa un cocinero que procesa pedidos concurrentemente.""" 

    def __init__(self, nombre, pedidos, log_file, lock):
         
        """Inicializa un cocinero.
        :param nombre: Nombre del cocinero.
        :param pedidos: Lista compartida de pedidos.
        :param log_file: Archivo donde se escribe el log.
        :param lock: Objeto para sincronizar acceso a la lista y log."""
        super().__init__()
        self.nombre = nombre
        self.pedidos = pedidos
        self.log_file = log_file
        self.lock = lock
    
    def run(self):
        """Ejecuta el hilo: toma pedidos, los prepara y los registra."""
        while True:
            pedido_actual = None
            # Acceso sincronizado a la lista de pedidos
            with self.lock:
                if not self.pedidos:
                    break
                pedido_actual = self.pedidos.pop(0)
            
            print(f"-> {self.nombre} está preparando el {pedido_actual.nombre_plato}")
            time.sleep(random.uniform(1, 3))# Simula tiempo de preparación
            
            mensaje = f"Completado: {self.nombre} terminó el {pedido_actual}"
            print(mensaje)
            # Acceso sincronizado al archivo de log
            with self.lock:
                self.log_file.write(mensaje + "\n")
                self.log_file.flush()