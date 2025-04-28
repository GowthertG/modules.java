public class Program {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.err.println("Usage: java Program --count=<number>");
            System.exit(1);
        }

        int count = Integer.parseInt(args[0].substring(8));
        Store store = new Store();

        Thread eggThread = new Thread(new Egg(count, store));
        Thread henThread = new Thread(new Hen(count, store));

        eggThread.start();
        henThread.start();

        try {
            eggThread.join();
            henThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

