import java.lang.reflect.WildcardType;
import java.util.*;

public class NonPreemptiveSJF {
    public double ATAT = 0;
    public double AWT = 0;
    public List<timeLine> Schedule(List<Process> processes,int MaximumAge) {

        List<timeLine> TimeLine = new ArrayList<timeLine>();

        processes.sort(Comparator.comparingInt(p -> p.ArrivalTime));

        List<Integer> executionOrder = new ArrayList<>();
        int lst_time = 0;
        int currentTime = 0;
        int executedCount = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.BurstTime));
        Deque<Process> important = new LinkedList<>();
        while (executedCount < processes.size()) {
            for (Process p : processes) {
                if (p.ArrivalTime <= currentTime && p.RemainingTime > 0 && !readyQueue.contains(p)) {
                    readyQueue.add(p);
                }
            }
            Iterator<Process> iterator = readyQueue.iterator();
            while (iterator.hasNext()) {
                Process p = iterator.next();
                if (p.age >= MaximumAge) {
                    important.add(p);
                    iterator.remove();
                }
            }

            if(!important.isEmpty()) {
                for(Process p : important) {
                    currentTime += p.BurstTime;
                    p.RemainingTime = 0;
                    timeLine time = new timeLine(p,lst_time,currentTime);
                    TimeLine.add(time);
                    lst_time = currentTime;
                    p.TurnaroundTime = currentTime - p.ArrivalTime;
                    p.WaitingTime = p.TurnaroundTime - p.BurstTime;

                    totalWaitingTime += p.WaitingTime;
                    totalTurnaroundTime += p.TurnaroundTime;

                    executionOrder.add(p.ProcessId);
                    executedCount++;
                }
                important.clear();
            }else {
                if (!readyQueue.isEmpty()) {
                    Process currentProcess = readyQueue.poll();
                    for(Process p: readyQueue) {
                        p.age++;
                    }
                    currentTime += currentProcess.BurstTime;
                    currentProcess.RemainingTime = 0;
                    timeLine time = new timeLine(currentProcess,lst_time,currentTime);
                    TimeLine.add(time);
                    lst_time = currentTime;
                    currentProcess.TurnaroundTime = currentTime - currentProcess.ArrivalTime;
                    currentProcess.WaitingTime = currentProcess.TurnaroundTime - currentProcess.BurstTime;

                    totalWaitingTime += currentProcess.WaitingTime;
                    totalTurnaroundTime += currentProcess.TurnaroundTime;

                    executionOrder.add(currentProcess.ProcessId);
                    executedCount++;
                } else {
                    currentTime++;
                }
            }

        }

        System.out.println("######## Processes Execution Order ########");
        System.out.println("Processes Execution Order: " + executionOrder);

        System.out.println("\n######## Waiting Time and Turnaround Time ########");
        System.out.println("PID     Waiting Time    Turnaround Time");
        for (Process p : processes) {
            System.out.printf("P%d      %-14d %d\n", p.ProcessId, p.WaitingTime, p.TurnaroundTime);
        }

        double averageWaitingTime = (double) totalWaitingTime / processes.size();
        double averageTurnaroundTime = (double) totalTurnaroundTime / processes.size();

        System.out.println("\n######## Averages ########");
        System.out.printf("Average Waiting Time: %.2f\n", averageWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);
        ATAT = averageTurnaroundTime;
        AWT = averageWaitingTime;
        return TimeLine;

    }


}
