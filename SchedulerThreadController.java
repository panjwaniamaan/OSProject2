import java.util.*;

public class SchedulerThreadController {
    private final ProcessBuffer buffer;
    private final ProcessProducer producer;
    private final List<ProcessConsumer> consumers;

    public SchedulerThreadController(List<Process> processList, int bufferSize, int consumerCount) {
        buffer = new ProcessBuffer(bufferSize);
        producer = new ProcessProducer(processList, buffer);
        consumers = new ArrayList<>();
        for (int i = 1; i <= consumerCount; i++) {
            consumers.add(new ProcessConsumer(buffer, "Consumer-" + i));
        }
    }

    public void startSimulation() throws InterruptedException {
        producer.start();
        for (Thread c : consumers) c.start();
        producer.join();
        Thread.sleep(5000); // Let consumers finish consuming
        for (Thread c : consumers) c.interrupt();
        for (Thread c : consumers) c.join();
        System.out.println("[SchedulerThreadController] Simulation complete.");
    }
}
