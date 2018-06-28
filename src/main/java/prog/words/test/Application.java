package prog.words.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import prog.words.test.dtos.CollectedWords;
import prog.words.test.threads.FileProcessTask;
import prog.words.test.threads.FileCreationThread;

/**
 * Application which counts words in files, words counted in provided directory 
 * and output files written to provided directory
 * 
 * @author MG
 */
public class Application {

	/**
	 * Starts files processing threads
	 * 
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		CollectedWords context = new CollectedWords();
		ExecutorService executor = Executors.newFixedThreadPool(4);
		List<Callable<Boolean>> tasks = Lists.newArrayList(); 
		List<Path> directoryFilesPaths = Files.walk(Paths.get(System.getenv("inputFilesDirectory"))).filter(Files::isRegularFile).collect(Collectors.toList());
		for (Path path : directoryFilesPaths) {
			tasks.add(new FileProcessTask(path, context));
		}
		executor.invokeAll(tasks);
		executor.shutdownNow();
		(new FileCreationThread(context, "^[A-Ga-g]", System.getenv("resultFileLocation"))).start();
		(new FileCreationThread(context, "^[H-Nh-n]", System.getenv("resultFileLocation"))).start();
		(new FileCreationThread(context, "^[O-Uo-u]", System.getenv("resultFileLocation"))).start();
		(new FileCreationThread(context, "^[V-Zv-z]", System.getenv("resultFileLocation"))).start();
	}
}
