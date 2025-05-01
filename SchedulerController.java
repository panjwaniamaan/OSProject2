import java.util.*;

public class SchedulerController {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("== Project 2: Thread-Based Process Simulation ==");
        System.out.println("Using: Producer-Consumer Synchronization");

        List<Process> processes = ProcessSchedulerThreaded.readProcessData("processes.txt");

        int bufferSize = 3;
        int consumerCount = 2;

        System.out.println("Processes Loaded: " + processes.size());
        System.out.println("Buffer Size: " + bufferSize);
        System.out.println("Consumer Threads: " + consumerCount);
        System.out.println("Starting simulation...");

        SchedulerThreadController controller = new SchedulerThreadController(processes, bufferSize, consumerCount);
        controller.startSimulation();

        scanner.close();
    }
}
