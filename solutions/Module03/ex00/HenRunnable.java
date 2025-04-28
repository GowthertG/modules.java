public class HenRunnable implements Runnable {
    private final int times;

    public HenRunnable(int times) {
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.println("Hen");
        }
    }
}

