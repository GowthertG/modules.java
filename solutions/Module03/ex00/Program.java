public class Program {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Expected exactly one argument like --count=50");
            System.exit(1);
        }

        String arg = args[0];
        if (!arg.startsWith("--count=")) {
            System.err.println("Argument must start with --count=");
            System.exit(1);
        }

        int count;
        try {
            count = Integer.parseInt(arg.substring("--count=".length()));
        } catch (NumberFormatException e) {
            System.err.println("Count must be a valid integer");
            return;
        }

        Thread egg = new EggThread(count);
        Thread hen = new Thread(new HenRunnable(count));

        egg.start();
        hen.start();

        try {
            egg.join();
            hen.join();
        } catch (InterruptedException ex) {
            System.err.println("Thread was interrupted");
            Thread.currentThread().interrupt();
        }

        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    }
}

