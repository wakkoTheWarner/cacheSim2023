Documentación: Simulador Simple de Interacción Cache-Memoria

---

#### Resumen:
Este programa simula la interacción entre un procesador y sus memorias, específicamente la memoria caché y la memoria principal. El programa ofrece una interfaz amigable para el usuario, permitiendo a los usuarios ejecutar simulaciones y observar el comportamiento de la caché basado en algoritmos de reemplazo específicos.

#### Componentes:

1. Processor: Representa una CPU que interactúa con la Cache y la Memoria Principal.
2. CacheMemory: Simula una memoria caché con diferentes algoritmos de reemplazo.
3. MainMemory: Representa la memoria principal del sistema.
4. cacheSimMain: Proporciona una interfaz de usuario para la simulación.

#### Uso:

1. Iniciando la Simulación:

Para comenzar, ejecute la clase 'cacheSimMain'. Este es el punto principal de entrada a la simulación.

Una vez que lo inicie, el programa le guiará a través del proceso de configuración solicitándole que introduzca parámetros específicos.

2. Parámetros de Entrada:

- Tiempo de Acceso a la Caché: El tiempo que le lleva al procesador acceder a la memoria caché. Introduzca un valor entero (por ejemplo, '10' para 10 unidades de tiempo).
  
- Tiempo de Acceso a la Memoria Principal: El tiempo que le lleva al procesador acceder a la memoria principal. Este valor generalmente es mayor que el tiempo de acceso a la caché. Introduzca un valor entero (por ejemplo, '100' para 100 unidades de tiempo).
  
- Algoritmo de Reemplazo: Elija el algoritmo que utiliza la caché cuando necesita reemplazar una página. Las opciones son:
	- FIFO (o Round Robin)
	- LRU

- Solicitud de Página: Introduzca un archivo con la secuencia de números de página que el procesador solicitará. Estos deben ser números enteros.

3. Ejecutando la Simulación:

Después de introducir los parámetros requeridos, comenzará la simulación. El programa procesará cada solicitud de página y mostrará resultados que indican si cada solicitud fue un acierto o un fallo en la caché, el estado de la memoria caché y el tiempo de acceso acumulado.

4. Entendiendo la Salida:

- Tiempo acceso cache/main: Tiempo de acceso a la caché/memoria principal.
- Algoritmo reemplazo: Algoritmo de reemplazo de caché.
- Se requiere la página: Página que se solicita.
- Resultado: Resultado de la solicitud (Hit/Miss).
- Tiempo transcurrido: Tiempo de acceso acumulado.
- Estado del sistema: Estado del sistema mostrando el reemplazo de página o no reemplazo.

La visualización de la caché y memoria principal muestra qué páginas están en la caché en cualquier momento y qué página se está accediendo actualmente.