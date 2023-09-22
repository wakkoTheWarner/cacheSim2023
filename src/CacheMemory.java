import java.util.ArrayList;
public class CacheMemory {
    // Tiempo que lleva acceder a la memoria caché
    private int accessTime;
    // El algoritmo de reemplazo que utiliza el caché: FIFO, Round Robin, LRU, etc.
    private String replacementAlgorithm;
    // Una lista dinámica para almacenar las páginas presentes en la memoria caché
    private final ArrayList<Integer> pages = new ArrayList<>();
    // Un puntero o ubicación que indica dónde se debe almacenar o reemplazar la página siguiente
    private int cacheLocation = 0;
    // Una lista para almacenar el orden en el que se accedió a las páginas (esto podría usarse para LRU)
    private final ArrayList<Integer> orderOfAccess = new ArrayList<>();

    //----------------//

    // Establecedor de tiempo de acceso
    public void setAccessTime(int accessTime) {
        this.accessTime = accessTime;
    }

    // Establecedor del algoritmo de reemplazo
    public void setReplacementAlgorithm(String replacementAlgorithm) {
        this.replacementAlgorithm = replacementAlgorithm;
    }

    // Método para verificar si la página solicitada está en el caché
    public boolean contains(int pageRequest) {
        updateStatus(pageRequest);
        return pages.contains(pageRequest);
    }

    // Getter para el tiempo de acceso
    public int getAccessTime() {
        return accessTime;
    }

    // Getter para el algoritmo de reemplazo
    public String getReplacementAlgorithm() {
        return replacementAlgorithm;
    }

    // Método para obtener la ubicación/índice de la caché de la página solicitada
    public int getCacheLocation(int pageRequest) {
        return pages.indexOf(pageRequest);
    }

    // Método para recuperar el valor (datos de la página) de una página específica en caché dado su número de solicitud
    public int getCacheValue(int pageRequest) {
        return pages.get(getCacheLocation(pageRequest));
    }

    // Método para actualizar el estado de las páginas en el caché (como la que se accedió recientemente).
    // Actualmente, no está implementado y podría usarse para algoritmos como LRU
    public void updateStatus(int pageRequest) {
        // null in Round Robin or FIFO
    }

    // Método para almacenar una nueva página en el caché, reemplazando una antigua si es necesario según el algoritmo elegido
    public int storePage(int pageData) {
        int replacedIndex;

        switch(replacementAlgorithm) {
            case "Round Robin":
            case "FIFO":
                replacedIndex = useRoundRobin(pageData);
                break;
            case "Last Recently Used":
            case "LRU":
                // Aquí implementarías la lógica para el algoritmo LRU
                // replacedIndex = useLastRecentlyUsed(pageData);
                replacedIndex = 0;
                break;
            default:
                throw new IllegalArgumentException("Algoritmo de reemplazo no válido");
        }

        return replacedIndex;
    }

    // Este método implementa la estrategia de reemplazo Round Robin (o FIFO)
    public int useRoundRobin(int pageData) {
        int replacedIndex;

        // Si la ubicación de la caché apunta más allá del último índice, restablecela al principio
        if (cacheLocation == 4) {
            cacheLocation = 0;
        }

        // Si el caché está lleno, reemplaza la página en la ubicación actual del caché
        // De lo contrario, agrega la nueva página al final de la lista
        if (pages.size() == 4) {
            pages.set(cacheLocation, pageData);
        } else {
            pages.add(pageData);
        }
        // El índice que fue reemplazado o donde se agregó la página
        replacedIndex = cacheLocation;

        // Pasar a la siguiente ubicación de caché para el próximo reemplazo
        cacheLocation++;

        return replacedIndex;
    }
}
