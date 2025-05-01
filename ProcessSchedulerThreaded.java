import java.io.*;
import java.util.*;

public class ProcessSchedulerThreaded {
    public static List<Process> readProcessData(String filename) {
        List<Process> processes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.trim().split("\s+");
                int pid = Integer.parseInt(data[0]);
                int arrival = Integer.parseInt(data[1]);
                int burst = Integer.parseInt(data[2]);
                int priority = Integer.parseInt(data[3]);
                processes.add(new Process(pid, arrival, burst, priority));
            }
        } catch (IOException e) {
            System.out.println("File read error: " + e.getMessage());
        }
        return processes;
    }
}
