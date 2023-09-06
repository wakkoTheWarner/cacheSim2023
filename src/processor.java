import java.util.List;

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
            cache.updateStatus(pageRequest);
        } else {
            result = "Miss";
            timeAccumulator += cache.getAccessTime();
            int pageData = primaryMemory.fetchPage(pageRequest);
            timeAccumulator += primaryMemory.getAccessTime();
            systemStatus = "Escribir en página " + String.format("%02d",cache.getCounter());

            cache.storePage(pageData);
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
    }
}
