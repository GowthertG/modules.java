public class ArraySumTask implements Runnable {

    private final int index;
    private final int from;
    private final int to;
    private int sum;
    private final Integer[] arr;

    public ArraySumTask(int index, int from, int to, Integer[] arr) {
        this.index = index;
        this.from = from;
        this.to = to;
        this.sum = 0;
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i = from; i <= to; i++) {
            sum += arr[i];
        }
        System.out.println("Thread " + index + ": from " + from + " to " + to + " sum is " + sum);

        synchronized (Program.getLock()) {
            Program.threadSum += sum;
        }
    }
}

