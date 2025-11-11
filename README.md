# 🍳 Simulador de Cocina con Concurrencia

Proyecto académico de **Programación de Servicios y Procesos** que simula el funcionamiento de una cocina con múltiples cocineros trabajando en paralelo. Implementado tanto en **Java** como en **Python** para demostrar el uso de hilos (threads) y sincronización de recursos compartidos.

## 📋 Descripción

Este proyecto simula una cocina donde varios cocineros procesan pedidos de forma concurrente. Los cocineros compiten por tomar pedidos de una lista compartida, los preparan (con un tiempo de espera aleatorio) y registran su trabajo en un archivo de log.

### Características principales

- **Generación aleatoria**: Entre 6-12 pedidos y 3-4 cocineros por ejecución
- **Procesamiento concurrente**: Múltiples hilos trabajando simultáneamente
- **Sincronización**: Control de acceso a recursos compartidos (lista de pedidos y archivo log)
- **Registro de actividad**: Log detallado de todas las operaciones
- **Simulación realista**: Tiempos de preparación aleatorios (1-3 segundos)

## 🏗️ Estructura del Proyecto

```
proyecto-cocina/
├── Java/
│   ├── Cocina.java      # Clase principal (punto de entrada)
│   ├── Cocinero.java    # Hilo que representa un cocinero
│   └── Pedido.java      # Modelo de datos para pedidos
├── Python/
│   ├── Cocina.py        # Módulo principal
│   ├── Cocinero.py      # Clase Thread para cocineros
│   └── Pedido.py        # Clase modelo de pedidos
└── README.md
```

## 🚀 Ejecución

### Versión Java

**Compilación:**
```bash
javac Cocina.java Cocinero.java Pedido.java
```

**Ejecución:**
```bash
java Cocina
```

### Versión Python

**Ejecución directa:**
```bash
python Cocina.py
```

**Requisitos:** Python 3.6 o superior (sin dependencias externas)

## 📊 Salida del Programa

### Salida por consola

```
=== APERTURA DE COCINA ===
Pedidos a preparar: 8
Cocineros disponibles: 3

Pedidos pendientes:
   - Pedido #1 (Pizza Funghi)
   - Pedido #2 (Ramen Tonkotsu)
   - Pedido #3 (Paella Valenciana)
   ...

-> Chef0 está preparando el Pizza Funghi
-> Chef1 está preparando el Ramen Tonkotsu
-> Chef2 está preparando el Paella Valenciana
Completado: Chef1 terminó el Pedido #2 (Ramen Tonkotsu)
Completado: Chef0 terminó el Pedido #1 (Pizza Funghi)
...

=== TODOS LOS PEDIDOS COMPLETADOS ===
```

### Archivo log_pedidos.txt

```
--- INICIO DEL SERVICIO ---
Total de pedidos: 8
Total de cocineros: 3

Completado: Chef1 terminó el Pedido #2 (Ramen Tonkotsu)
Completado: Chef0 terminó el Pedido #1 (Pizza Funghi)
Completado: Chef2 terminó el Pedido #3 (Paella Valenciana)
...

--- FIN DEL SERVICIO ---
```

## 🔧 Conceptos Técnicos Implementados

### Concurrencia
- **Java**: Uso de `Thread` y herencia de la clase
- **Python**: Uso de `threading.Thread` como clase base

### Sincronización
- **Java**: 
  - Bloque `synchronized` con objeto lock compartido
  - `Collections.synchronizedList()` para la lista de pedidos
- **Python**: 
  - Context manager `with lock` para sincronización
  - Lock explícito con `threading.Lock()`

### Recursos Compartidos
1. **Lista de pedidos**: Acceso sincronizado para evitar condiciones de carrera
2. **Archivo de log**: Escritura sincronizada para mantener integridad
3. **Consola**: Salida estándar para monitoreo en tiempo real

## 🎯 Objetivos de Aprendizaje

Este proyecto demuestra:

✅ Creación y gestión de múltiples hilos  
✅ Sincronización de acceso a recursos compartidos  
✅ Prevención de condiciones de carrera (race conditions)  
✅ Manejo de excepciones en contextos multihilo  
✅ Uso de locks y bloqueos para coordinación  
✅ Escritura segura en archivos desde múltiples hilos  
✅ Implementación equivalente en dos lenguajes diferentes  

## 📝 Detalles de Implementación

### Clase Pedido
Representa un pedido con:
- `id`: Identificador único numérico
- `nombrePlato`: Nombre del plato del menú

### Clase Cocinero (Thread)
Cada cocinero:
1. Toma un pedido de la lista compartida (con lock)
2. Lo "prepara" (sleep aleatorio de 1-3 segundos)
3. Registra la finalización en el log (con lock)
4. Repite hasta que no quedan pedidos

### Clase Cocina (Main)
Orquesta la simulación:
1. Genera cantidad aleatoria de pedidos y cocineros
2. Crea la lista compartida de pedidos
3. Inicia todos los hilos (cocineros)
4. Espera a que terminen (`join()`)
5. Cierra el archivo de log

## 🎲 Menú de Platos

El simulador incluye 12 platos variados:
- Pizza Funghi
- Lasaña de Carne
- Ensalada Griega
- Sopa de Cebolla
- Tacos de Cochinita
- Risotto de Setas
- Hamburguesa con Queso
- Pollo Tikka Masala
- Ramen Tonkotsu
- Filete Mignon
- Sushi Variado
- Paella Valenciana

## 🔍 Diferencias entre Implementaciones

| Aspecto | Java | Python |
|---------|------|--------|
| **Herencia** | `extends Thread` | `threading.Thread` |
| **Lock** | `synchronized(lock)` | `with lock:` |
| **Lista sincronizada** | `Collections.synchronizedList()` | Lock manual |
| **Sleep** | `Thread.sleep(ms)` | `time.sleep(seconds)` |
| **Nombres cocineros** | `"Chef" + i` | Lista predefinida de nombres |
| **Try-with-resources** | Sí (PrintWriter) | Context manager (with) |

## 👨‍💻 Autor

**Entornito** - Alumno de 2º DAM  
Asignatura: Programación de Servicios y Procesos

## 📄 Licencia

Proyecto académico con fines educativos.

---

⭐ **Nota**: Cada ejecución produce resultados diferentes debido a la aleatoriedad en la generación de pedidos, asignación de cocineros y tiempos de preparación.
