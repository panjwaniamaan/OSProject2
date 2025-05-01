import java.util.List;

public class ProcessProducer extends Thread {
    private final List<Process> processList;
    private final ProcessBuffer buffer;

    public ProcessProducer(List<Process> processList, ProcessBuffer buffer) {
        this.processList = processList;
        this.buffer = buffer;
    }

    public void run() {
        for (Process p : processList) {
            try {
                buffer.produce(p);
                Thread.sleep(500); // Simulate delay between arrivals
            } catch (InterruptedException e) {
                System.out.println("[Producer] Interrupted");
            }
        }
    }
}
