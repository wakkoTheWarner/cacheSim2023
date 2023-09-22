public class Processor {
    // Instancia de CacheMemory y MainMemory que representan los componentes del sistema informático simulado
    private final CacheMemory cache;
    private final MainMemory primaryMemory;

    // Variables de String para representar el resultado y el estado del sistema para cada solicitud de página procesada
    private String result = "";
    private String systemStatus = "";

    // Número entero para acumular el tiempo necesario para procesar las solicitudes de página
    private int timeAccumulator = 0;

    // Entero para indicar la ubicación en el caché donde se almacena una página
    private int cacheLocationNum = -1;

    // Matriz para representar los valores almacenados en cada ubicación de caché
    private String[] cachePageValue = {"  ","  ","  ","  "};

    //----------------//

    // Constructor para inicializar el procesador con instancias dadas de CacheMemory y MainMemory
    public Processor(CacheMemory cache, MainMemory primaryMemory) {
        this.cache = cache;
        this.primaryMemory = primaryMemory;
    }

    // Método para ejecutar la simulación en una lista de solicitudes de páginas
    public void runSimulator(int[] pageRequests) {
        displaySystemState(-1); // Mostrar estado inicial
        for (int pageRequest : pageRequests) {
            processRequest(pageRequest); // Procesa cada solicitud de página en la lista
        }
    }

    // Método para procesar una solicitud de página individual
    private void processRequest(int pageRequest) {
        // Aumenta el tiempo acumulador con el tiempo de acceso a la caché
        timeAccumulator += cache.getAccessTime();
        if (cache.contains(pageRequest)) {
            result = "Hit";
            systemStatus = "No sustitución";
            // Si el caché contiene la página solicitada, es un hit
            // cache.updateStatus(pageRequest); // Descomentar si desea actualizar el estado de la caché después de un hit
        } else {
            // Si el caché no contiene la página solicitada, es Miss
            result = "Miss";
            // Aumentar el tiempo acumulador con el tiempo de acceso a la memoria principal
            timeAccumulator += primaryMemory.getAccessTime();
            // Recupera la página de la memoria primaria y la almacena en el caché
            cache.storePage(primaryMemory.fetchPage(pageRequest));
            // Obtener la ubicación del caché donde se almacenó la página
            cacheLocationNum = cache.getCacheLocation(pageRequest);
            // Actualiza el estado del sistema indicando la ubicación del caché donde se escribió la página
            systemStatus = "Escribir en página " + String.format("%02d",cacheLocationNum);
        }

        // Muestra el estado del sistema después de procesar la solicitud.
        displaySystemState(pageRequest);
    }

    // Método para mostrar el estado actual del sistema
    private void displaySystemState(int pageRequest) {
        int width = 22;
        char[] cacheSelection = {' ',' ',' ',' '};
        int cacheLocationNumHold = cacheLocationNum;

        // Determinar qué línea de caché resaltar según las operaciones de caché recientes
        if (result.isEmpty()) {
            cacheSelection[0] = '*';
        } else {
            if (cacheLocationNumHold == 3) {
                cacheLocationNumHold = -1;
            }
            cacheSelection[cacheLocationNumHold + 1] = '*';
            if (result.contains("Miss")) {
                // Actualiza la visualización del valor de la página de caché para errores
                cachePageValue[cacheLocationNum] = String.format("%02d",cache.getCacheValue(pageRequest));
            }
        }

        //Imprimiendo el estado actual del sistema
        System.out.println("");
        System.out.printf("%-" + width + "s: %s%n", "Tiempo acceso cache", cache.getAccessTime() + " nano segundos");
        System.out.printf("%-" + width + "s: %s%n", "Tiempo acceso main", primaryMemory.getAccessTime() + " nano segundos");
        System.out.printf("%-" + width + "s: %s%n", "Algoritmo reemplazo", cache.getReplacementAlgorithm());

        if (pageRequest != -1) {
            System.out.printf("%-" + width + "s: %s%n", "Se requiere la página", pageRequest);
        } else {
            System.out.printf("%-" + width + "s:%n", "Se requiere la página");
        }

        System.out.printf("%-" + width + "s: %s%n", "Resultado", result);
        System.out.printf("%-" + width + "s: %s%n", "Tiempo transcurrido", String.format("%04d", timeAccumulator) + " nano segundos");
        System.out.printf("%-" + width + "s: %s%n", "Status del sistema", systemStatus);

        // Descomentar la siguiente sección si desea mostrar una representación visual del caché y la memoria principal
        /*
        System.out.println("           cache      main ");
        System.out.println("-------     -----     -----");
        System.out.println("|     |   00|   |   00|   |");
        System.out.println("|Micro|---01|   |---01|   |");
        System.out.println("|     |   02|   |   02|   |");
        System.out.println("-------   03|   |   03|   |");
        System.out.println("            -----   04|   |");
        System.out.println("                    05|   |");
        System.out.println("                    06|   |");
        System.out.println("                    07|   |");
        System.out.println("                    08|   |");
        System.out.println("                    09|   |");
        System.out.println("                    10|   |");
        System.out.println("                    11|   |");
        System.out.println("                    12|   |");
        System.out.println("                    13|   |");
        System.out.println("                    14|   |");
        System.out.println("                    15|   |");
        System.out.println("                      -----");
         */
    }
}
