import java.util.*;

public class NonPreemptivePrioritySchedulerUsingContextSwitching {

    public double ATAT = 0;
    public double AWT = 0;
    public List<timeLine> Schedule(List<Process> Processes, int SwitchTime) {
        List<timeLine> TimeLine = new ArrayList<timeLine>();

        Processes.sort(Comparator.comparingInt((Process P) -> P.ArrivalTime));

        List<Integer> ExecutionOrder = new ArrayList<>();

        int lst_time = 0;
        int CurrentTime = 0;
        int TotalWaitingTime = 0;
        int TotalTurnaroundTime = 0;
        int executed_count = 0;
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(
                Comparator.comparingInt((Process p) -> p.Priority));

        while(executed_count != Processes.size()) {
            lst_time = CurrentTime;
            for (Process p : Processes) {
                if (p.ArrivalTime <= CurrentTime && p.RemainingTime > 0 && !readyQueue.contains(p)) {
                    readyQueue.add(p);
                }
            }
            if (!readyQueue.isEmpty()) {
                Process current_process = readyQueue.poll();
                CurrentTime += current_process.RemainingTime;
                current_process.RemainingTime = 0;
                timeLine t = new timeLine(current_process,lst_time,CurrentTime);
                TimeLine.add(t);
                current_process.TurnaroundTime = CurrentTime - current_process.ArrivalTime;
                current_process.WaitingTime = current_process.TurnaroundTime - current_process.BurstTime;
                TotalWaitingTime += current_process.WaitingTime;
                TotalTurnaroundTime += current_process.TurnaroundTime;
                ExecutionOrder.add(current_process.ProcessId);
                CurrentTime += SwitchTime;
                executed_count++;
            } else {
                CurrentTime++;
            }

        }




        System.out.println("######## Processes Execution Order ########");

        System.out.println("Processes Execution Order: " + ExecutionOrder);
        System.out.println("\n######## Waiting Time and Turnaround Time ########");
        System.out.println("\nPID     Waiting Time    Turnaround Time");
        Processes.sort(Comparator.comparingInt((Process P) -> P.ProcessId));
        for (Process P : Processes) {
            System.out.printf("P%d      %-14d %d\n", P.ProcessId, P.WaitingTime, P.TurnaroundTime);
        }

        double AverageWaitingTime = (double) TotalWaitingTime / Processes.size();
        double AverageTurnaroundTime = (double) TotalTurnaroundTime / Processes.size();

        System.out.println("\n######## Averages ########");
        System.out.printf("Average Waiting Time: %.2f\n", AverageWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", AverageTurnaroundTime);

        ATAT = AverageTurnaroundTime;
        AWT = AverageWaitingTime;
        return TimeLine;
    }
}