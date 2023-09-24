import java.util.ArrayList;

// Clase que simula una memoria caché con varios algoritmos de reemplazo.
public class CacheMemory {

    // Variables que almacenan el tiempo de acceso de la caché y el tipo de algoritmo de reemplazo.
    private int accessTime;
    private String replacementAlgorithm;

    // Un array dinámico para almacenar páginas de caché.
    private final ArrayList<Integer> pages = new ArrayList<>();

    // Variable para administrar la ubicación de la caché para el algoritmo Round Robin.
    private int cacheLocation = 0;

    // Un array dinámico para administrar el orden de acceso a las páginas, principalmente para el algoritmo LRU.
    private final ArrayList<Integer> orderOfAccess = new ArrayList<>();

    //----------------//

    // Establece el tiempo de acceso de la caché.
    public void setAccessTime(int accessTime) {
        this.accessTime = accessTime;
    }

    // Establece el algoritmo de reemplazo.
    public void setReplacementAlgorithm(String replacementAlgorithm) {
        this.replacementAlgorithm = replacementAlgorithm;
    }

    // Verifica si una página solicitada está en la caché y actualiza el estado según el algoritmo.
    public boolean contains(int pageRequest) {
        updateStatus(pageRequest);
        return pages.contains(pageRequest);
    }

    // Obtiene el tiempo de acceso de la caché.
    public int getAccessTime() {
        return accessTime;
    }

    // Obtiene el algoritmo de reemplazo.
    public String getReplacementAlgorithm() {
        return replacementAlgorithm;
    }

    // Devuelve la ubicación de la página solicitada en la caché.
    public int getCacheLocation(int pageRequest) {
        return pages.indexOf(pageRequest);
    }

    // Devuelve el valor en la ubicación de la página solicitada en la caché.
    public int getCacheValue(int pageRequest) {
        return pages.get(getCacheLocation(pageRequest));
    }

    // Actualiza el orden de acceso de la caché, especialmente para LRU.
    public void updateStatus(int pageRequest) {
        if (replacementAlgorithm.equals("Last Recently Used") || replacementAlgorithm.equals("LRU")) {
            orderOfAccess.remove(Integer.valueOf(pageRequest)); // Elimina la entrada existente (si existe)
            orderOfAccess.add(pageRequest); // La añade al final

            // Asegurarse de que ambas listas estén sincronizadas
            if (!pages.contains(pageRequest)) {
                orderOfAccess.remove(Integer.valueOf(pageRequest));
            }
        }
    }

    // Almacena una página en la caché utilizando el algoritmo de reemplazo apropiado.
    public int storePage(int pageData) {
        int replacedIndex;

        switch(replacementAlgorithm) {
            case "Round Robin":
            case "FIFO":
                replacedIndex = useRoundRobin(pageData);
                break;
            case "Last Recently Used":
            case "LRU":
                replacedIndex = useLastRecentlyUsed(pageData);
                break;
            default:
                throw new IllegalArgumentException("Algoritmo de reemplazo no válido");
        }

        return replacedIndex;
    }

    // Implementa el algoritmo de reemplazo Round Robin (FIFO).
    public int useRoundRobin(int pageData) {
        int replacedIndex;

        if (cacheLocation == 4) {
            cacheLocation = 0;
        }
        if (pages.size() == 4) {
            pages.set(cacheLocation, pageData);
        } else {
            pages.add(pageData);
        }
        replacedIndex = cacheLocation;
        cacheLocation++;

        return replacedIndex;
    }

    // Implementa el algoritmo de reemplazo Last Recently Used (LRU).
    public int useLastRecentlyUsed(int pageData) {
        int replacedIndex = -1;

        if (pages.contains(pageData)) {
            updateStatus(pageData);
        } else {
            if (pages.size() == 4) {
                int lruPage = orderOfAccess.get(0);
                replacedIndex = pages.indexOf(lruPage);
                if (replacedIndex != -1) {
                    pages.set(replacedIndex, pageData);
                    orderOfAccess.remove(0);
                } else {
                    orderOfAccess.remove(0);
                    pages.add(pageData);
                }
            } else {
                pages.add(pageData);
                replacedIndex = pages.size() - 1;
            }
            orderOfAccess.add(pageData);
        }

        return replacedIndex;
    }
}