public class processor {
    private final cacheMemory cache;
    private final mainMemory primaryMemory;
    private String result = "";
    private String systemStatus = "";
    private int timeAccumulator = 0;

    public processor(cacheMemory cache, mainMemory primaryMemory) {
        this.cache = cache;
        this.primaryMemory = primaryMemory;
    }

    public void runSimulator(int[] pageRequests) {
        displaySystemState(-1);
        for (int pageRequest : pageRequests) {
            processRequest(pageRequest);
        }
    }

    private void processRequest(int pageRequest) {
        if (cache.contains(pageRequest)) {
            result = "Hit";
            timeAccumulator += cache.getAccessTime();
            systemStatus = "No sustitución";
            // cache.updateStatus(pageRequest);
        } else {
            result = "Miss";
            timeAccumulator += cache.getAccessTime();
            int pageData = primaryMemory.fetchPage(pageRequest);
            timeAccumulator += primaryMemory.getAccessTime();
            cache.storePage(pageData);
            systemStatus = "Escribir en página " + String.format("%02d",cache.getCacheLocation());
        }

        displaySystemState(pageRequest);
    }

    private void displaySystemState(int pageRequest) {
        int width = 22; // Set the desired width here

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
