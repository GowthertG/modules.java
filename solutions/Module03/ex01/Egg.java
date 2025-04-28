public class Egg implements Runnable {
    private final int count;
    private final Store store;

    public Egg(int count, Store store) {
        this.count = count;
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            store.printEgg();
        }
    }
}
