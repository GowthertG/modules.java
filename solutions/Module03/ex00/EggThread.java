public class EggThread extends Thread {
    private final int times;

    public EggThread(int times) {
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.println("Egg");
        }
    }
}

