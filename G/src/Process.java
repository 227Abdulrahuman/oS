import java.awt.*;
 public class Process {
    int ProcessId;
    String name;
    int ArrivalTime;
    int BurstTime;
    int Priority;
    int quantum;
    int RemainingTime;
    int WaitingTime = 0;
    int TurnaroundTime = 0;
    int age = 0;
    double fcaiFactor = 0;
    Color color;

    // fcai factor
    public Process(int processId, int arrivalTime, int burstTime, int priority, int quantum,Color color) {
        this.ProcessId = processId;
        this.ArrivalTime = arrivalTime;
        this.BurstTime = burstTime;
        this.Priority = priority;
        this.quantum = quantum;
        this.RemainingTime = burstTime;
        this.color = color;
    }
     public Process(int processId, int arrivalTime, int burstTime, int priority, int quantum,Color color, String name) {
         this.ProcessId = processId;
         this.ArrivalTime = arrivalTime;
         this.BurstTime = burstTime;
         this.Priority = priority;
         this.quantum = quantum;
         this.RemainingTime = burstTime;
         this.color = color;
         this.name = name;
     }
    public void Calc_FcaiFactor(double v1,double v2) {
        this.fcaiFactor =  (10-this.Priority) + Math.ceil(this.ArrivalTime/v1) + Math.ceil(this.RemainingTime/v2);
        return;
    }


    //PriorityScheduling
    public Process(int processId, int arrivalTime, int burstTime, int priority,Color color) {
        this.ProcessId = processId;
        this.ArrivalTime = arrivalTime;
        this.BurstTime = burstTime;
        this.Priority = priority;
        this.RemainingTime = burstTime;
        this.color = color;
    }
     public Process(int processId, int arrivalTime, int burstTime, int priority,Color color, String name) {
         this.ProcessId = processId;
         this.ArrivalTime = arrivalTime;
         this.BurstTime = burstTime;
         this.Priority = priority;
         this.RemainingTime = burstTime;
         this.color = color;
         this.name = name;
     }


    public Process(int processId, int arrivalTime, int burstTime,Color Color) {
        this.ProcessId = processId;
        this.ArrivalTime = arrivalTime;
        this.BurstTime = burstTime;
        this.RemainingTime = burstTime;
        this.color = Color;
    }
     public Process(int processId, int arrivalTime, int burstTime,Color Color,String name) {
         this.ProcessId = processId;
         this.ArrivalTime = arrivalTime;
         this.BurstTime = burstTime;
         this.RemainingTime = burstTime;
         this.color = Color;
         this.name = name;
     }

    public String toString() {
        return Integer.toString(this.ProcessId);
    }

    public Process (int id, Color c, int p) {
        this.ProcessId = id;
        this.color = c;
        this.Priority = p;
    }

}