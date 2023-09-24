// Clase que simula un procesador con capacidad para interactuar con una memoria caché y principal.
public class Processor {
    // Variables para almacenar las instancias de memoria caché y principal.
    private final CacheMemory cache;
    private final MainMemory primaryMemory;

    // Variables para almacenar el estado del sistema durante la simulación.
    private String result = "";            // Almacena el resultado (Hit o Miss).
    private String systemStatus = "";      // Almacena el estado del sistema (sustitución o no).
    private int timeAccumulator = 0;       // Acumula el tiempo de acceso total.
    private int cacheLocationNum = -1;     // Almacena la ubicación en la memoria caché.
    private String[] cachePageValue = {"  ","  ","  ","  "};  // Representa las páginas en caché.

    //----------------//

    // Constructor para inicializar las instancias de memoria caché y principal.
    public Processor(CacheMemory cache, MainMemory primaryMemory) {
        this.cache = cache;
        this.primaryMemory = primaryMemory;
    }

    // Método para ejecutar la simulación dadas las solicitudes de páginas.
    public void runSimulator(int[] pageRequests) {
        displaySystemState(-1);
        for (int pageRequest : pageRequests) {
            processRequest(pageRequest);
        }
    }

    // Método privado para procesar una solicitud de página.
    private void processRequest(int pageRequest) {
        timeAccumulator += cache.getAccessTime();
        if (cache.contains(pageRequest)) { // Si la página está en caché.
            result = "Hit";
            systemStatus = "No sustitución";
        } else { // Si la página no está en caché.
            result = "Miss";
            timeAccumulator += primaryMemory.getAccessTime();
            cache.storePage(primaryMemory.fetchPage(pageRequest)); // Almacena la página en caché.
            cacheLocationNum = cache.getCacheLocation(pageRequest);
            systemStatus = "Escribir en página " + String.format("%02d",cacheLocationNum);
        }

        displaySystemState(pageRequest);
    }

    // Método privado para mostrar el estado actual del sistema.
    private void displaySystemState(int pageRequest) {
        int width = 22;
        char[] cacheSelection = {' ',' ',' ',' '};
        int cacheLocationNumHold = cacheLocationNum;

        // Configuración inicial para el display de la caché.
        if (result.isEmpty()) {
            cacheSelection[0] = '*';
        } else {
            if (cacheLocationNumHold == 3) {
                cacheLocationNumHold = -1;
            }
            cacheSelection[cacheLocationNumHold + 1] = '*';
            if (result.contains("Miss")) {
                cachePageValue[cacheLocationNum] = String.format("%02d",cache.getCacheValue(pageRequest));
            }
        }

        // A continuación, se imprime el estado actual y la información del sistema.
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

        System.out.println("           cache      main ");
        System.out.println("-------     -----     -----");
        System.out.println("|     |   00|" + cachePageValue[0] + cacheSelection[0] + "|   00|   |");
        System.out.println("|Micro|---01|" + cachePageValue[1] + cacheSelection[1] + "|---01|   |");
        System.out.println("|     |   02|" + cachePageValue[2] + cacheSelection[2] + "|   02|   |");
        System.out.println("-------   03|" + cachePageValue[3] + cacheSelection[3] + "|   03|   |");
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
    }
}
