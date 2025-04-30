import java.util.ArrayList;
import java.util.Random;

public class Program {
    private static final int MAX_MODULO = 1000;
    public static int threadSum = 0;
    public static final Object lock = new Object();

    private static Integer[] initArray(int size) {
        Random random = new Random();
        Integer[] arr = new Integer[size];
        int i = 0;
        while (i < size) {
            arr[i++] = random.nextInt(MAX_MODULO - 1);
        }

        int suma = 0;
        for (Integer integer : arr) {
            suma += integer;
        }

        System.out.println("Sum: " + suma);
        return arr;
    }

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Error: wrong args");
            System.exit(1);
        }

        if (!args[0].matches("--arraySize=\\d++") || !args[1].matches("--threadsCount=\\d++")) {
            System.err.println("Error: wrong flag");
            System.exit(1);
        }

        int arraySize = Integer.parseInt((args[0].split("=")[1]));
        int threadsCount = Integer.parseInt((args[1].split("=")[1]));
        if (arraySize > 2000000 || threadsCount > arraySize) {
            System.err.println("Error: big size");
            System.exit(1);
        }

        Integer[] arr = initArray(arraySize);

        int baseSize = arraySize / threadsCount;
        int remainder = arraySize % threadsCount;
        int start = 0;

        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < threadsCount; i++) {
            int end = start + baseSize - 1;
            if (remainder > 0) {
                end++;
                remainder--;
            }
            threads.add(new Thread(new ThreadSum(i + 1, start, end, arr)));
            start = end + 1;
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Sum by threads: " + threadSum);
    }
}
