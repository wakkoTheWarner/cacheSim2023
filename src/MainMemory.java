public class MainMemory {
    // El tiempo que lleva acceder a la memoria principal
    private int accessTime;

    // Una representación de las páginas de la memoria principal.
    private int[] pages = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    // Establece el tiempo que lleva acceder a la memoria principal
    public void setAccessTime(int accessTime) {
        this.accessTime = accessTime;
    }

    // Getter del tiempo que lleva acceder a la memoria principal
    public int getAccessTime() {
        return accessTime;
    }

    // Método para recuperar una página de la memoria principal dado su número de página.
    // El valor devuelto es esencialmente el mismo que el número de página proporcionado
    // (dado que la matriz de páginas es solo una serie de números enteros crecientes),
    // pero en un escenario del mundo real, obtendría los datos/contenido correspondientes de esa página.
    public int fetchPage(int pageNumber) {
        return pages[pageNumber];
    }
}
