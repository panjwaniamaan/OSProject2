import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class ProcessBuffer {
    private final Queue<Process> buffer;
    private final int capacity;
    private final Semaphore full;
    private final Semaphore empty;
    private final Lock mutex;

    public ProcessBuffer(int capacity) {
        this.buffer = new LinkedList<>();
        this.capacity = capacity;
        this.full = new Semaphore(0);
        this.empty = new Semaphore(capacity);
        this.mutex = new ReentrantLock();
    }

    public void produce(Process process) throws InterruptedException {
        System.out.println("[Producer] Waiting to enqueue Process " + process.pid);
        empty.acquire();
        mutex.lock();
        try {
            buffer.add(process);
            System.out.println("[Producer] Enqueued Process " + process.pid);
        } finally {
            mutex.unlock();
            full.release();
        }
    }

    public Process consume() throws InterruptedException {
        System.out.println("[" + Thread.currentThread().getName() + "] Waiting to dequeue");
        full.acquire();
        mutex.lock();
        try {
            Process p = buffer.poll();
            System.out.println("[" + Thread.currentThread().getName() + "] Dequeued Process " + p.pid);
            return p;
        } finally {
            mutex.unlock();
            empty.release();
        }
    }
}
