public class Store {
    private boolean eggTurn = true;

    public synchronized void printEgg() {
        while (!eggTurn) {
            waitForTurn();
        }
        System.out.println("Egg");
        eggTurn = false;
        notify();
    }

    public synchronized void printHen() {
        while (eggTurn) {
            waitForTurn();
        }
        System.out.println("Hen");
        eggTurn = true;
        notify();
    }

    private void waitForTurn() {
        try {
            wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

