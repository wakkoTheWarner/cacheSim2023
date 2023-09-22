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

    static Scanner scanner = new Scanner(System.in);

    public static void main (String[] args) {
        // Create instances of Processor, Cache, and MainMemory
        CacheMemory cache = new CacheMemory();
        MainMemory primaryMemory = new MainMemory();
        Processor cpuProcessor = new Processor(cache, primaryMemory);

        // Get user inputs: file name, cache access time, main memory access time, and replacement algorithm
        System.out.print("Ingrese el nombre del archivo con las peticiones de páginas: ");
        String fileName = scanner.nextLine();
        File file = new File(".\\" + fileName + ".txt");
        System.out.print("Ingrese el tiempo de acceso para el cache (en nano segundos): ");
        int cacheAccessTime = scanner.nextInt();
        System.out.print("Ingrese el tiempo de acceso para la memoria principal (en nano segundos): ");
        int mainMemoryAccessTime = scanner.nextInt();
        scanner.nextLine();  // consume the leftover newline
        System.out.print("Ingrese el algoritmo de reemplazo (por ahora, solo 'Round Robin' es válido): ");
        String replacementAlgorithm = scanner.nextLine();

        // Set the properties
        cache.setAccessTime(cacheAccessTime);
        primaryMemory.setAccessTime(mainMemoryAccessTime);
        cache.setReplacementAlgorithm(replacementAlgorithm);

        // Read the page requests from the file
        int[] pageRequests = readFile(file);

        // Run the simulator
        cpuProcessor.runSimulator(pageRequests);
    }

    public static int[] readFile(File file) {
        try {
            Scanner compScanner = new Scanner(file);

            List<Integer> pageRequests = new ArrayList<>();

            while (compScanner.hasNextLine()) {
                String line = compScanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                while (lineScanner.hasNextInt()) {
                    int number = lineScanner.nextInt();
                    pageRequests.add(number);
                }
                lineScanner.close();
            }

            compScanner.close();

            return pageRequests.stream().mapToInt(i -> i).toArray();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
