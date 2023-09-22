Documentación de Simulación de Caché

Introducción:
Este proyecto simula un sistema básico de jerarquía de memoria de computadora, demostrando la interacción entre un procesador, una memoria caché y la memoria principal (a menudo conocida como RAM). Describe el proceso de obtención de datos desde la caché o la memoria principal e implementa un algoritmo de reemplazo de páginas Round Robin para la caché.

Componentes:
	cacheSimMain:
		Propósito: Sirve como el principal impulsor para la simulación.
		Características Clave:
			Inicializa las instancias de Processor, CacheMemory y MainMemory.
			Toma entradas del usuario para el nombre del archivo que contiene solicitudes de páginas, tiempos de acceso de caché y memoria principal, y un algoritmo de reemplazo de páginas.
			Lee las solicitudes de páginas del archivo proporcionado.
			Ejecuta la simulación del procesador utilizando estas solicitudes de páginas.

	Processor:
		Propósito: Representa la CPU que interactúa con la caché y la memoria principal.
		Características Clave:
			Procesa solicitudes de páginas.
			Determina si una página solicitada resulta en un "Hit" o "Miss" de la caché.
			Actualiza el estado del sistema y el tiempo.
			Muestra el estado actual del sistema.

	CacheMemory:
		Propósito: Simula la memoria caché de una computadora.
		Características Clave:
			Mantiene páginas en la caché.
			Utiliza un algoritmo de reemplazo de páginas (actualmente solo está implementado "Round Robin").
			Verifica la presencia de una página en la caché.
			Almacena nuevas páginas cuando es necesario, reemplazando las antiguas según el algoritmo.
			Establece y recupera el tiempo de acceso de la caché.

	MainMemory:
		Propósito: Representa la memoria principal o RAM de la computadora.
		Características Clave:
			Extrae páginas basadas en el número de página dado.
			Establece y recupera el tiempo de acceso de la memoria principal.

Cómo Ejecutar:
	1. Compila todas las clases Java.
	2. Ejecuta la clase cacheSimMain.
	3. Cuando se te solicite, proporciona:
		El nombre del archivo (sin la extensión .txt) que contiene las solicitudes de páginas.
		El tiempo de acceso deseado para la caché (en nanosegundos).
		El tiempo de acceso deseado para la memoria principal (en nanosegundos).
		El algoritmo de reemplazo de páginas (actualmente, solo "Round Robin" es válido).
	4. La simulación mostrará los resultados para cada solicitud de página.

Notas:
	Las solicitudes de páginas en el archivo deben ser enteros separados por espacios o líneas nuevas.
	La implementación actual solo admite el algoritmo "Round Robin" para el reemplazo de páginas en caché.
	Aunque hay un marcador de posición para el algoritmo "Last Recently Used" (LRU) en la clase CacheMemory, no está implementado en esta versión.