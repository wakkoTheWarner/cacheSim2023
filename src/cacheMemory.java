public class cacheMemory {
    int accessTime;
    String replacementAlgorithm;
    int[] pages = new int[4];
    int cacheLocation;

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

    public int getCacheLocation() {
        return cacheLocation-1;
    }

    public void updateStatus(int pageRequest) {
        // null in Round Robin or FIFO
    }

    public void storePage(int pageData) {
        switch(replacementAlgorithm) {
            case "Round Robin":
            case "FIFO":
                useRoundRobin(pageData);
                break;
            case "Last Recently Used":
                // useLastRecentlyUsed(pageData);
                break;
        }
    }

    public void useRoundRobin(int pageData) {
        if (cacheLocation == 4) {
            cacheLocation = 0;
        }
        pages[cacheLocation] = pageData;
        cacheLocation++;
    }
}
