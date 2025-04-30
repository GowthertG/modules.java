import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class Program {

	private static final String FILE_NAME = "files_urls.txt";
	private static final BlockingQueue<DownloadTask> queue = new LinkedBlockingQueue<>();

	public static void main (String args[]) throws IOException{
		if (args.length != 1 || !args[0].startsWith("--threadsCount=")){
			System.err.println("Error: use the --threadsCount=<Number>");
			System.exit(1);
		}
		int threadCount = getThreadCount(args);
		if (threadCount <= 0){
			System.err.println("Error: number must be > 0");
			System.exit(1);
		}
		// Read from file to a buffer
		try(BufferedReader reader = new BufferedReader (new FileReader(FILE_NAME))){
			int index = 1;
			String line;
			while ((line = reader.readLine()) != null)
			{
				// System.out.println(line);
				queue.add(new DownloadTask(index++, line));
			}
		}
		ExecutorService pool = Executors.newFixedThreadPool(threadCount);
		for (int i = 0; i < threadCount; i++){
			pool.submit(new Downloader());
		}
		pool.shutdown();
	}

	private static int getThreadCount(String[] args){
		try {
			return Integer.parseInt(args[0].split("=")[1]);
		}
		catch (Exception e){
			System.err.println("Error: use the --threadsCount=<Number>");
			System.exit(1);
		}
		return -1;
	};

	static class DownloadTask {
		final int number;
		final String url;

		DownloadTask(int number, String url){
			this.number = number;
			this.url = url;
		}
	}
	static class Downloader implements Runnable {
        @Override
        public void run() {
            while (true) {
                DownloadTask task = queue.poll();
                if (task == null) break;

                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " start download file number " + task.number);

                try (InputStream in = new URL(task.url).openStream()) {
                    Files.copy(in, Paths.get("file" + task.number), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println(threadName + " finish download file number " + task.number);
                } catch (IOException e) {
                    System.err.println("Error downloading file " + task.number + ": " + e.getMessage());
                }
            }
        }
    }
}
