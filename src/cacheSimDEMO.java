/*
 * Cache Simulator v0.02
 * by Eduardo A. Sosa
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class cacheSimDEMO {
    private static int timeCache = 5;
    private static int timeMain = 50;
    private static int[] cacheArray = new int[4];
    private static int[] mainArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    private static int elapsedTime = 0;
    private static int counter = 0;
    private static String result = "";
    private static String systemStatus = "";
    private static String replacementAlgorithm = "Round Robin";

    public static void main(String[] args) {
        int requestedPage;
        String fileName;
        Scanner userInput = new Scanner(System.in);

        /* ---------------------------- */

        System.out.print("Ingrese el nombre del archivo con las peticiones de páginas: ");
        fileName = userInput.nextLine();
        File file = new File(".\\" + fileName + ".txt");
        System.out.print("Ingrese el tiempo de acceso para el cache (en nano segundos): ");
        timeCache = userInput.nextInt();
        System.out.print("Ingrese el tiempo de acceso para la memoria principal (en nano segundos): ");
        timeMain = userInput.nextInt();
        userInput.nextLine();  // consume the leftover newline
        System.out.print("Ingrese el algoritmo de reemplazo (por ahora, solo 'Round Robin' es válido): ");
        replacementAlgorithm = userInput.nextLine();

        runProcessor();

        int[] requestedPages = grabRequestedPages(file);

        for (int page : requestedPages) {
            result = "Miss";

            requestedPage = page;

            /* CHEQUEAR CACHE */
            searchCache(requestedPage);

            /* CHEQUEAR MAIN MEMORY */
            if (result.equals("Miss")) {
                searchMain(requestedPage);
            }

            /* CORRER PROCESADOR */
            runProcessor(requestedPage);
        }

    }

    public static int[] grabRequestedPages(File file) {
        try {
            Scanner compScanner = new Scanner(file);

            List<Integer> numbers = new ArrayList<>();

            while (compScanner.hasNextLine()) {
                String line = compScanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                while (lineScanner.hasNextInt()) {
                    int number = lineScanner.nextInt();
                    numbers.add(number);
                }
                lineScanner.close();
            }

            compScanner.close();

            return numbers.stream().mapToInt(i -> i).toArray();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //
    public static void searchCache(int requestedPage) {
        elapsedTime += timeCache;
        for (int cacheNum : cacheArray) {
            if (requestedPage == cacheNum) {
                result = "Hit";
                systemStatus = "No sustitución";
                break;
            }
        }
    }

    public static void searchMain(int requestedPage) {
        elapsedTime += timeMain;
        if (requestedPage >= 0 && requestedPage < mainArray.length) {
            cacheArray[counter] = mainArray[requestedPage];
            systemStatus = "Escribir en página " + String.format("%02d", counter);
            counter++;
            if (counter > 3) {
                counter = 0;
            }
        } else {
            systemStatus = "Invalid page request: " + requestedPage;
        }
    }

    public static void runProcessor() {
        System.out.println("\n---------------- ESTADO INICIAL DEL SISTEMA ----------------");
        runProcessor(-1);
        System.out.println("------------------------------------------------------------");
    }

    public static void runProcessor(int requestedPage) {
        System.out.println("\nTiempo acceso cache : " + timeCache + " nano segundos");
        System.out.println("Tiempo acceso main : " + timeMain + " nano segundos");
        System.out.println("Algoritmo reemplazo : " + replacementAlgorithm);

        if (requestedPage != -1) {
            System.out.println("Se requiere la página : " + requestedPage);
        } else {
            System.out.println("Se requiere la página : ");
        }

        System.out.println("Resultado : " + result);
        System.out.println("Tiempo transcurrido : " + String.format("%04d", elapsedTime) + " nano segundos");
        System.out.println("Status del sistema : " + systemStatus + "\n");
    }
}