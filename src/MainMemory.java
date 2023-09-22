public class MainMemory {
    private int accessTime;
    //private Map<Integer, Integer> pages = new HashMap<>();
    private int[] pages = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    public void setAccessTime(int accessTime) {
        this.accessTime = accessTime;
    }

    public int getAccessTime() {
        return accessTime;
    }

    public int fetchPage(int pageNumber) {
        return pages[pageNumber];
    }
}
