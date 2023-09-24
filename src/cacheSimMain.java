/*
 * CacheSim2023
 * Build v1.0.0
 * by Eduardo A. Sosa Torres
 * Equipo #7
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class cacheSimMain {

    // Inicializando el scanner para la entrada del usuario
    static Scanner scanner = new Scanner(System.in);

    public static void main (String[] args) {
        // Crea instancias de Procesador, Caché y MainMemory para simular el sistema de computadora
        CacheMemory cache = new CacheMemory();
        MainMemory primaryMemory = new MainMemory();
        Processor cpuProcessor = new Processor(cache, primaryMemory);

        // Solicita al usuario que ingrese el nombre del archivo que contiene las solicitudes de página
        System.out.print("Ingrese el nombre del archivo con las peticiones de páginas: ");
        String fileName = scanner.nextLine();

        // Solicita al usuario que ingrese el nombre del archivo que contiene las solicitudes de página
        File file = new File("..\\" + fileName + ".txt");

        // Solicita al usuario que ingrese el tiempo de acceso a la caché en nano segundos
        System.out.print("Ingrese el tiempo de acceso para el cache (en nano segundos): ");
        int cacheAccessTime = scanner.nextInt();

        // Solicita al usuario que ingrese el tiempo de acceso a la memoria principal en nano segundos
        System.out.print("Ingrese el tiempo de acceso para la memoria principal (en nano segundos): ");
        int mainMemoryAccessTime = scanner.nextInt();


        scanner.nextLine();  // consume la nueva línea sobrante de entradas anteriores

        // Solicitar al usuario que ingrese el algoritmo de reemplazo
        System.out.print("Ingrese el algoritmo de reemplazo: ");
        String replacementAlgorithm = scanner.nextLine();

        // Establece las propiedades de la caché y la memoria primaria según la entrada del usuario
        cache.setAccessTime(cacheAccessTime);
        primaryMemory.setAccessTime(mainMemoryAccessTime);
        cache.setReplacementAlgorithm(replacementAlgorithm);

        // Lee las solicitudes de página del archivo proporcionado
        int[] pageRequests = readFile(file);

        // Ejecute la simulación por computadora usando las solicitudes de página dadas
        cpuProcessor.runSimulator(pageRequests);
    }

    // Lee el archivo proporcionado para extraer una lista de solicitudes de páginas.
    public static int[] readFile(File file) {
        try {
            // Inicializa un escáner para leer el archivo
            Scanner compScanner = new Scanner(file);

            // Lista para almacenar solicitudes de páginas
            List<Integer> pageRequests = new ArrayList<>();

            // Recorre cada línea del archivo
            while (compScanner.hasNextLine()) {
                String line = compScanner.nextLine();

                // Usa otro scanner para leer números enteros de la línea actual
                Scanner lineScanner = new Scanner(line);
                while (lineScanner.hasNextInt()) {
                    int number = lineScanner.nextInt();
                    pageRequests.add(number); // Agrega el entero leído a la lista
                }

                // Cerrar el scanner de línea después de procesar la línea
                lineScanner.close();
            }

            // Cierra el scanner de archivos después de procesar todo el archivo
            compScanner.close();

            // Convierte la lista de números enteros en una matriz de enteros primitivos
            return pageRequests.stream().mapToInt(i -> i).toArray();
        } catch (FileNotFoundException e) {
            // Lanza una excepción de tiempo de ejecución si no se encuentra el archivo
            throw new RuntimeException(e);
        }
    }
}
