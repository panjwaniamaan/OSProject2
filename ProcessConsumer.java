public class ProcessConsumer extends Thread {
    private final ProcessBuffer buffer;

    public ProcessConsumer(ProcessBuffer buffer, String name) {
        super(name);
        this.buffer = buffer;
    }

    public void run() {
        try {
            while (true) {
                Process p = buffer.consume();
                System.out.println("[" + getName() + "] Started execution of Process " + p.pid);
                Thread.sleep(p.burstTime * 1000L);
                System.out.println("[" + getName() + "] Finished execution of Process " + p.pid);
            }
        } catch (InterruptedException e) {
            System.out.println("[" + getName() + "] Terminated.");
        }
    }
}
