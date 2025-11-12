import random
import threading
from Pedido import Pedido
from Cocinero import Cocinero

MENU = [
    "Pizza Funghi", "Lasaña de Carne", "Ensalada Griega",
    "Sopa de Cebolla", "Tacos de Cochinita", "Risotto de Setas",
    "Hamburguesa con Queso", "Pollo Tikka Masala", "Ramen Tonkotsu",
    "Filete Mignon", "Sushi Variado", "Paella Valenciana"
]

NOMBRES_COCINEROS = [
    "Chef Ana", "Chef Bruno", "Chef Carla", "Chef Diego"
]

def main():

#Punto de entrada: genera pedidos, lanza cocineros e inicia la simulación.
    cantidad_pedidos = random.randint(6, 12)
    cantidad_cocineros = random.randint(3, 4)
    
    lista_pedidos = []
    lock = threading.Lock()
    
    print("=== APERTURA DE COCINA ===")
    print(f"Pedidos a preparar: {cantidad_pedidos}")
    print(f"Cocineros disponibles: {cantidad_cocineros}\n")
    # Generar pedidos aleatorios
    for i in range(cantidad_pedidos):
        plato_aleatorio = random.choice(MENU)
        lista_pedidos.append(Pedido(i + 1, plato_aleatorio))
    
    print("Pedidos pendientes:")
    for pedido in lista_pedidos:
        print(f"   - {pedido}")
    print()
    # Iniciar log y lanzar cocineros
    with open("log_pedidos.txt", "w", encoding="utf-8") as log_file:
        log_file.write("--- INICIO DEL SERVICIO ---\n")
        log_file.write(f"Total de pedidos: {cantidad_pedidos}\n")
        log_file.write(f"Total de cocineros: {cantidad_cocineros}\n\n")
        log_file.flush()
        
        cocineros = []
        for i in range(cantidad_cocineros):
            cocinero = Cocinero(NOMBRES_COCINEROS[i], lista_pedidos, log_file, lock)
            cocineros.append(cocinero)
            cocinero.start()
        
        for cocinero in cocineros:
            cocinero.join()
        
        print("\n=== TODOS LOS PEDIDOS HAN SIDO PROCESADOS ===")
        log_file.write("\n--- FIN DEL SERVICIO ---\n")

if __name__ == "__main__":
    main()