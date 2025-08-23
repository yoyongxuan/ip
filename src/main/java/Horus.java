import java.util.Scanner;  // Import the Scanner class

public class Horus {
    static String chatbot_name = "Horus";

    public static void main(String[] args) {
        TaskList tasklist = new TaskList();
        String input_str;
        String[] command_task; //An array of 2 strings. First item is a command if present, second item is a string representing a task
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;


        greet();

        while (!exit) {
            input_str = scanner.nextLine();
            command_task = input_str.split("\\s+",2); //Isolates the substring behind the first space in the input
//            System.out.println(command_task[0]);
//            System.out.println(command_task[1]);

            switch(command_task[0]) {
                case "bye":
                    exit = true;
                    break;
                case "list":
                    tasklist.showList();
                    break;
                case "mark":
                    tasklist.markTask(command_task[1]);
                    break;
                case "unmark":
                    tasklist.unmarkTask(command_task[1]);
                    break;
                case "todo":
                    tasklist.addTask(command_task[1], TaskList.taskTypes.TODO);
                    break;
                case "deadline":
                    tasklist.addTask(command_task[1], TaskList.taskTypes.DEADLINE);
                    break;
                case "event":
                    tasklist.addTask(command_task[1], TaskList.taskTypes.EVENT);
                    break;
                default:
                    System.out.println(input_str);
                    break;
            }
        }

        exit();
    }

    public static void greet() {
        print_line();
        System.out.println("Hello! I'm " + chatbot_name + "\nWhat can I do for you?\n" );
    }

    public static void exit() {
        print_line();
        System.out.println("Bye. Hope to see you again soon!" );
        print_line();
    }

    public static void print_line() {
        System.out.println("____________________________________________________________" );
    }

    public static class TaskList {
        Task[] tasks;
        int num_of_tasks;

        public enum taskTypes {
            TODO,
            DEADLINE,
            EVENT
        }

        public static class Task {
            String task_str;
            boolean marked;
            public Task(String task_str) {
                this.task_str = task_str;
                this.marked = false;
            }
            public void mark() {
                marked = true;
            }
            public void unmark() {
                marked = false;
            }
            @Override
            public String toString() {
                String mark_str = marked? "X" : " ";
                return "[" + mark_str +"] " + this.task_str;
            }
        }

        public static class ToDoTask extends Task {
            public ToDoTask(String task_str) {
                super(task_str);
            }

            @Override
            public String toString() {
                return "[T]" + super.toString();
            }
        }

        public static class DeadlineTask extends Task {
            String by;
            public DeadlineTask(String task_str) {
                super(task_str.substring(0,task_str.indexOf("/by")) );
                by = task_str.substring(task_str.indexOf("/by") + 3);
            }

            @Override
            public String toString() {
                return "[D]" + super.toString() +" (by:" + this.by +")";
            }
        }

        public static class EventTask extends Task {
            String from;
            String to;
            public EventTask(String task_str) {
                super(task_str.substring(0,task_str.indexOf("/from")) );
                from = task_str.substring(task_str.indexOf("/from") + 5,task_str.indexOf("/to") );
                to = task_str.substring(task_str.indexOf("/to") + 3 );

            }

            @Override
            public String toString() {
                return "[D]" + super.toString() +" (from:" + this.from + " to:"+ this.to +")";
            }
        }

        public TaskList() {
            tasks = new Task[100];
            num_of_tasks = 0;
        }

        public void addTask(String task_str, taskTypes task_type) {
            print_line();
            switch(task_type) {
                case TODO:
                    tasks[num_of_tasks] = new ToDoTask(task_str);
                    break;
                case DEADLINE:
                    tasks[num_of_tasks] = new DeadlineTask(task_str);
                    break;
                case EVENT:
                    tasks[num_of_tasks] = new EventTask(task_str);
                    break;
            }
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks[num_of_tasks]);
            num_of_tasks++;
            System.out.println("Now you have " + num_of_tasks + " tasks in the list.");
            print_line();
        }

        public void showList() {
            print_line();
            for(int i = 0; i < num_of_tasks; i++) {
                System.out.println( i+1 + "." + tasks[i].toString() );
            }
            print_line();
        }

        public void markTask(String taskID) {
            int task_index = Integer.parseInt(taskID) - 1;
            tasks[task_index].mark();
            print_line();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[task_index]);
            print_line();
        }

        public void unmarkTask(String taskID) {
            int task_index = Integer.parseInt(taskID) - 1;
            tasks[task_index].unmark();
            print_line();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks[task_index]);
            print_line();
        }
    }

}
