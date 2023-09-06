import java.util.HashMap;
import java.util.Map;

public class cacheMemory {
    int accessTime;
    String replacementAlgorithm;
    int[] pages = new int[4];
    int counter;

    public void setAccessTime(int accessTime) {
        this.accessTime = accessTime;
    }

    public void setReplacementAlgorithm(String replacementAlgorithm) {
        this.replacementAlgorithm = replacementAlgorithm;
    }

    public boolean contains(int pageRequest) {
        for (int page : pages) {
            if (page == pageRequest) {
                return true;
            }
        }
        return false;
    }

    public int getAccessTime() {
        return accessTime;
    }

    public String getReplacementAlgorithm() {
        return replacementAlgorithm;
    }

    public int getCounter() {
        return counter;
    }

    public void updateStatus(int pageRequest) {
        // null in Round Robin or FIFO
    }

    public void storePage(int pageData) {
        switch(replacementAlgorithm) {
            case "Round Robin":
                useRoundRobin(pageData);
                break;
            case "Last Recently Used":
                // useLastRecentlyUsed(pageData);
                break;
        }
    }

    public void useRoundRobin(int pageData) {
        if (counter == 4) {
            counter = 0;
        }
        pages[counter] = pageData;
        counter++;
    }
}
