import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.table.DefaultTableCellRenderer;

public class Main {
    public static String scheduleName;
    public static double AWT;
     public static double ATAT;
    public static  List<timeLine> times = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the Processing Scheduler: Prio, SJF, SRTF, FCAI");
        String algo = scan.nextLine();

        List<Process> processes = new ArrayList<>();


        //*******************************************************
        if (algo.equalsIgnoreCase("FCAI")) {

            scheduleName = "FCAI Scheduler";
            System.out.println("Enter the number of processes: ");
            int numProcesses = scan.nextInt();

            for (int i = 0; i < numProcesses; i++) {
                System.out.println("Enter details for Process " + (i + 1) + ":");

                System.out.print("Process ID: ");
                int processId = scan.nextInt();
                scan.nextLine();
                System.out.println("Name:");
                String name = scan.nextLine();

                System.out.print("Arrival Time: ");
                int arrivalTime = scan.nextInt();

                System.out.print("Burst Time: ");
                int burstTime = scan.nextInt();

                System.out.print("Priority : ");
                int priority = scan.nextInt();

                System.out.print("Quantum: ");
                int quantum = scan.nextInt();

                System.out.println("Choose Process Color (R G B values): ");
                System.out.print("Red (0-255): ");
                int red = scan.nextInt();
                System.out.print("Green (0-255): ");
                int green = scan.nextInt();
                System.out.print("Blue (0-255): ");
                int blue = scan.nextInt();


                Color color = new Color(red, green, blue);


                processes.add(new Process(processId, arrivalTime, burstTime, priority, quantum, color,name));
            }

            List<Process> processes1 = new ArrayList<>(processes.size());

            for (Process p : processes) {
                processes1.add(p);
            }
            FcaiFactor fcai = new FcaiFactor();
            System.out.println("Enter the ContextSwitch: ");
            int ContextSwitch = scan.nextInt();
             times = fcai.Schedule(processes1,ContextSwitch);
            ATAT = fcai.ATAT;
            AWT = fcai.AWT;
        }
//********************************************************************************

    else if (algo.equalsIgnoreCase("prio")) {
            scheduleName = "Priority Scheduler";
            System.out.println("Enter the number of processes: ");
            int numProcesses = scan.nextInt();


            for (int i = 0; i < numProcesses; i++) {
                System.out.println("Enter details for Process " + (i + 1) + ":");

                System.out.print("Process ID: ");
                int processId = scan.nextInt();
                scan.nextLine();
                System.out.println("name:");
                String name = scan.nextLine();


                System.out.print("Arrival Time: ");
                int arrivalTime = scan.nextInt();

                System.out.print("Burst Time: ");
                int burstTime = scan.nextInt();

                System.out.print("Priority : ");
                int priority = scan.nextInt();



                System.out.println("Choose Process Color (R G B values): ");
                System.out.print("Red (0-255): ");
                int red = scan.nextInt();
                System.out.print("Green (0-255): ");
                int green = scan.nextInt();
                System.out.print("Blue (0-255): ");
                int blue = scan.nextInt();

                Color color = new Color(red, green, blue);

                processes.add(new Process(processId, arrivalTime, burstTime, priority, color,name));
            }

            System.out.print("SwitchTime: ");
            int switchtime = scan.nextInt();


            NonPreemptivePrioritySchedulerUsingContextSwitching prio = new NonPreemptivePrioritySchedulerUsingContextSwitching();
            times = prio.Schedule(processes,switchtime);
            AWT = prio.AWT;
            ATAT = prio.ATAT;
        }

    else if (algo.equalsIgnoreCase("SJF")) {
        scheduleName = "SJF";
            System.out.println("Enter the number of processes: ");
            int numProcesses = scan.nextInt();

            for (int i = 0; i < numProcesses; i++) {
                System.out.println("Enter details for Process " + (i + 1) + ":");

                System.out.print("Process ID: ");
                int processId = scan.nextInt();
                scan.nextLine();
                System.out.println("name:");
                String name = scan.nextLine();

                System.out.print("Arrival Time: ");
                int arrivalTime = scan.nextInt();

                System.out.print("Burst Time: ");
                int burstTime = scan.nextInt();


                System.out.println("Choose Process Color (R G B values): ");
                System.out.print("Red (0-255): ");
                int red = scan.nextInt();
                System.out.print("Green (0-255): ");
                int green = scan.nextInt();
                System.out.print("Blue (0-255): ");
                int blue = scan.nextInt();

                Color color = new Color(red, green, blue);

                processes.add(new Process(processId, arrivalTime, burstTime, color,name));
            }



            NonPreemptiveSJF sjf = new NonPreemptiveSJF();

            times = sjf.Schedule(processes,5);
            ATAT = sjf.ATAT;
            AWT = sjf.AWT;

        }

    else if (algo.equalsIgnoreCase("SRTF")) {
        scheduleName = "SRTF";
            System.out.println("Enter the number of processes: ");
            int numProcesses = scan.nextInt();

            for (int i = 0; i < numProcesses; i++) {
                System.out.println("Enter details for Process " + (i + 1) + ":");

                System.out.print("Process ID: ");
                int processId = scan.nextInt();
                scan.nextLine();
                System.out.println("Name:");
                String name = scan.nextLine();

                System.out.print("Arrival Time: ");
                int arrivalTime = scan.nextInt();

                System.out.print("Burst Time: ");
                int burstTime = scan.nextInt();




                System.out.println("Choose Process Color (R G B values): ");
                System.out.print("Red (0-255): ");
                int red = scan.nextInt();
                System.out.print("Green (0-255): ");
                int green = scan.nextInt();
                System.out.print("Blue (0-255): ");
                int blue = scan.nextInt();

                Color color = new Color(red, green, blue);

                processes.add(new Process(processId, arrivalTime, burstTime, color,name));
            }
            System.out.print("ContextSwitch: ");
            int contextSwitch = scan.nextInt();


            SRTFScheduler srtf = new SRTFScheduler();
            times = srtf.Schedule(processes,5000,contextSwitch);
            AWT = srtf.AWT;
            ATAT = srtf.ATAT;
        }












//        //********************************************
        //Priority SCH
//        List<Process> processes = new ArrayList<>();
//        processes.add(new Process(1, 0, 10, 3,Color.blue));
//        processes.add(new Process(2, 0, 1, 1,Color.CYAN));
//        processes.add(new Process(3, 0, 2, 4,Color.RED));
//
//        NonPreemptivePrioritySchedulerUsingContextSwitching PrioritySheduler = new NonPreemptivePrioritySchedulerUsingContextSwitching();
//        List<timeLine>times = PrioritySheduler.Schedule(processes,0);
        //**********************************************

        //FCAI SCHED

//        List<Process> processes = new ArrayList<>();
//        processes.add(new Process(1, 0, 17, 4, 4, Color.BLUE));
//        processes.add(new Process(2, 3, 6, 9, 3, Color.RED));
//        processes.add(new Process(3, 4, 10, 3, 5, Color.CYAN));
//        processes.add(new Process(4, 29, 4, 10, 2, Color.GREEN));







        JFrame frame = new JFrame();

        frame.setTitle("Scheduler GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1720, 1000);
        frame.setLayout(null); // Disable layout manager
        frame.setResizable(false);

        JLabel label = new JLabel("Process Information");
        label.setBounds(1300, 1, 300, 100); // Position of the label
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(Color.RED);
        frame.add(label);

        JLabel label2 = new JLabel("CPU Scheduling Graph");
        label2.setBounds(5, 1, 300, 100); // Position of the label
        label2.setFont(new Font("Arial", Font.BOLD, 24));
        label2.setForeground(Color.RED);
        frame.add(label2);





        String[] columns = {"ID", "COLOR", "Priority","Name"};
        Object[][] data = new Object[processes.size()][4];
        for (int i = 0; i < processes.size(); i++) {
            Process process = processes.get(i);
            data[i][0] = process.ProcessId;
            data[i][1] = process.color;
            data[i][2] = process.Priority;
            data[i][3] = process.name;

        }



        JTable table = new JTable(data, columns);

        table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground((Color) value);
                return label;
            }
        });

        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setForeground(Color.RED);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(1300, 100, 400, 400);
        frame.add(scrollPane);

        JLabel statsTitle = new JLabel("Statistics", SwingConstants.LEFT);
        statsTitle.setBounds(50, 700, 200, 50);
        statsTitle.setFont(new Font("Arial", Font.BOLD, 24));
        statsTitle.setForeground(Color.RED);
        frame.add(statsTitle);





        JLabel scheduleLabel = new JLabel("Schedule Name: " + scheduleName);
        scheduleLabel.setBounds(50, 750, 600, 50);
        scheduleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        frame.add(scheduleLabel);

        JLabel awtLabel = new JLabel("AWT: " + AWT);
        awtLabel.setBounds(50, 800, 300, 50);
        awtLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        frame.add(awtLabel);

        JLabel atatLabel = new JLabel("ATAT: " + ATAT);
        atatLabel.setBounds(50, 850, 300, 50);
        atatLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        frame.add(atatLabel);



        JPanel timelinePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int yOffset = 50;
                int barHeight = 30;
                int timeUnitWidth = 100;

                for (timeLine t : times) {
                    int row = t.p.ProcessId - 1;
                    int xStart = t.start * timeUnitWidth;
                    int xEnd = t.end * timeUnitWidth;
                    g.setColor(t.p.color);
                    g.fillRect(xStart, yOffset + (row * 50), (xEnd - xStart), barHeight);
                    g.setColor(Color.BLACK);
                    g.drawString("P" + t.p.ProcessId, xStart + 5, yOffset + (row * 50) + 20);
                }
            }
        };

        int timelineWidth = 12000;
        int timelineHeight = 6000;
        timelinePanel.setPreferredSize(new Dimension(timelineWidth, timelineHeight));
        timelinePanel.setBackground(Color.GRAY);
        timelinePanel.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane timelineScrollPane = new JScrollPane(timelinePanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        timelineScrollPane.setBounds(50, 100, 1200, 400);
        frame.add(timelineScrollPane);

        frame.setVisible(true);





    }
}