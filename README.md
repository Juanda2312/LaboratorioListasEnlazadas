# Laboratorio: Listas Enlazadas
**Estructuras de Datos — Universidad del Quindío**  
**Juan David Tapiero Montoya**

---

## Descripción general

Este laboratorio implementa cuatro escenarios prácticos de listas enlazadas en Java, cada uno con su propia estructura construida desde cero, sin usar `ArrayList`, `LinkedList`, `List`, `Queue`, `Set` ni `Map` del API de Java.

Cada escenario incluye:
- Análisis del problema
- Selección y justificación de la estructura
- Implementación completa (`Node<T>`, clase Lista, agregar, eliminar, buscar, mostrar)
- Reflexión sobre la elección

---

## Estructura del proyecto

```
src/main/java/co/edu/uniquindio/
├── panaderia/           Escenario 1 — Lista simple
├── historialweb/        Escenario 2 — Lista doble
├── reproductor/         Escenario 3 — Lista circular simple
└── juegoporturnos/      Escenario 4 — Lista circular doble
```

---

## Escenario 1: Sistema de turnos en una panadería

### Descripción
Una panadería organiza la atención de sus clientes por orden de llegada. El sistema registra clientes al final, atiende al primero en espera, consulta el siguiente turno y muestra la lista actual.

### Estructura seleccionada: Lista simplemente enlazada

| Criterio | Decisión |
|---|---|
| Recorrido en ambos sentidos | No — sentido único |
| Volver al elemento anterior | No |
| Comportamiento | Lineal (FIFO) |
| Inserción frecuente | Al final (`addLast`) |
| Eliminación frecuente | Al inicio (`removeFirst`) |

### Justificación
La lista simple es suficiente porque el comportamiento es FIFO puro. No se requiere navegar hacia atrás ni ciclar la lista, por lo que agregar punteros `prev` o hacerla circular sería complejidad innecesaria.

### Métodos de `Panaderia`
| Método | Descripción |
|---|---|
| `addOrder(String order)` | Agrega un pedido al final |
| `attend()` | Elimina el primer pedido (atiende al cliente) |
| `next()` | Consulta el primer pedido sin eliminarlo |
| `allOrders()` | Imprime toda la lista de pedidos |

---

## Escenario 2: Historial de navegación web

### Descripción
Un navegador almacena las páginas visitadas. Permite visitar una nueva página, retroceder a la anterior, avanzar si ya se había retrocedido y mostrar la página actual.

### Estructura seleccionada: Lista doblemente enlazada

| Criterio | Decisión |
|---|---|
| Recorrido en ambos sentidos | Sí |
| Acceso al nodo anterior | Sí — necesita puntero `prev` |
| Una sola referencia por nodo | No — se necesitan `next` y `prev` |
| Circular | No — la última página no conecta con la primera |

### Justificación
La navegación en ambos sentidos exige obligatoriamente el puntero `prev`. No es circular porque no tiene sentido que al retroceder desde la primera página se llegue a la última. Con lista simple no sería posible retroceder.

### Métodos de `HistorialWeb`
| Método | Descripción |
|---|---|
| `visit(String url)` | Agrega una página y reposiciona el iterador |
| `current()` | Devuelve la página actual |
| `next()` | Avanza a la siguiente página |
| `prev()` | Retrocede a la página anterior |

---

## Escenario 3: Lista de reproducción continua de música

### Descripción
Una app de música reproduce canciones de forma continua. Al llegar al final vuelve automáticamente a la primera. Permite avanzar, eliminar canciones y mostrar la secuencia. No se requiere retroceder.

### Estructura seleccionada: Lista circular simplemente enlazada

| Criterio | Decisión |
|---|---|
| Secuencia que se reinicia automáticamente | Sí |
| Final real de la lista | No — el último nodo apunta al primero |
| Recorrido sobre sí mismo | Sí |
| Moverse hacia atrás | No |

### Justificación
La circularidad garantiza que la reproducción nunca termine: `getNext()` en el último nodo retorna al primero. Como no se necesita retroceder canción, la versión simple (sin `prev`) es suficiente y más eficiente en memoria que la doble.

### Métodos de `Reproductor`
| Método | Descripción |
|---|---|
| `addSong(String song)` | Agrega una canción y restaura la posición del iterador |
| `removeSong(String song)` | Elimina una canción manteniendo la posición de reproducción |
| `next()` | Avanza y retorna la siguiente canción |
| `secuence()` | Muestra todas las canciones en orden |

---

## Escenario 4: Juego por turnos con varios jugadores

### Descripción
Un videojuego por turnos administra jugadores alrededor de una mesa virtual. Los turnos pasan continuamente del último jugador al primero, se puede consultar quién jugó antes y quién juega después, y es posible expulsar jugadores en mitad de la partida.

### Estructura seleccionada: Lista circular doblemente enlazada

| Criterio | Decisión |
|---|---|
| Recorrido circular | Sí — del último vuelve al primero |
| Siguiente y anterior | Sí — requisito explícito |
| Sistema continuo indefinidamente | Sí |
| Eliminar nodos intermedios fácilmente | Sí — expulsión de jugadores |

### Justificación
Es la única estructura que cumple simultáneamente los tres requisitos: continuidad circular (el turno nunca termina), navegación en ambos sentidos (consultar anterior y siguiente), y eliminación sencilla de nodos intermedios (expulsar jugadores). Las simples no tienen `prev`; las no circulares tienen final `null`.

### Métodos de `JuegoTurnos`
| Método | Descripción |
|---|---|
| `addPlayer(String player)` | Agrega un jugador manteniendo la posición actual |
| `removePlayer(String player)` | Expulsa un jugador y reposiciona el iterador si es necesario |
| `next()` | Turno del siguiente jugador |
| `prev()` | Turno del jugador anterior |
| `isEmpty()` | Indica si no quedan jugadores en la partida |

---

## Comparativa de estructuras

| Escenario | Estructura | Circular | Doble | Razón clave |
|---|---|:---:|:---:|---|
| Panadería | Simple | ✗ | ✗ | FIFO lineal, un solo sentido |
| Historial web | Doble | ✗ | ✓ | Navegar hacia atrás y adelante |
| Reproductor | Circ. simple | ✓ | ✗ | Reproducción infinita, sin `prev` |
| Juego turnos | Circ. doble | ✓ | ✓ | Ciclo infinito + sentido anterior |

---

## Restricciones

- No se permite usar `ArrayList`, `LinkedList`, `List`, `Queue`, `Set` ni `Map`
- Toda la lógica se implementa con clases propias (`Node<T>` y la clase Lista)
- Cada escenario tiene su propia implementación independiente
- Las listas usan generics con `T extends Comparable<T>`

---

*Estructuras de Datos — Universidad del Quindío — 2026*
