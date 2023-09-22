import java.util.ArrayList;
public class CacheMemory {
    private int accessTime;
    private String replacementAlgorithm;
    //int[] pages = new int[4];
    private final ArrayList<Integer> pages = new ArrayList<>();
    private int cacheLocation = 0;
    private final ArrayList<Integer> orderOfAccess = new ArrayList<>();

    //----------------//

    public void setAccessTime(int accessTime) {
        this.accessTime = accessTime;
    }

    public void setReplacementAlgorithm(String replacementAlgorithm) {
        this.replacementAlgorithm = replacementAlgorithm;
    }

    public boolean contains(int pageRequest) {
        updateStatus(pageRequest);
        return pages.contains(pageRequest);
    }

    public int getAccessTime() {
        return accessTime;
    }

    public String getReplacementAlgorithm() {
        return replacementAlgorithm;
    }

    public int getCacheLocation(int pageRequest) {
        return pages.indexOf(pageRequest);
    }

    public int getCacheValue(int pageRequest) {
        return pages.get(getCacheLocation(pageRequest));
    }

    public void updateStatus(int pageRequest) {
        // null in Round Robin or FIFO
    }

    public int storePage(int pageData) {
        int replacedIndex;

        switch(replacementAlgorithm) {
            case "Round Robin":
            case "FIFO":
                replacedIndex = useRoundRobin(pageData);
                break;
            case "Last Recently Used":
            case "LRU":
                // replacedIndex = useLastRecentlyUsed(pageData);
                replacedIndex = 0;
                break;
            default:
                throw new IllegalArgumentException("Algoritmo de reemplazo no válido");
        }

        return replacedIndex;
    }

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
}
