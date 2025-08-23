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
                default:
                    tasklist.addTask(input_str);
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
        System.out.println("Bye. Hope to see you again soon!\n" );
        print_line();
    }

    public static void print_line() {
        System.out.println("____________________________________________________________" );
    }

    public static class TaskList {
        Task[] tasks;
        int num_of_tasks;

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
            public String toString() {
                String mark_str = marked? "X" : " ";
                return "[" + mark_str +"] " + this.task_str;
            }
        }

        public TaskList() {
            tasks = new Task[100];
            num_of_tasks = 0;
        }

        public void addTask(String task_str) {
            print_line();
            tasks[num_of_tasks] = new Task(task_str);
            num_of_tasks++;
            System.out.println("added: " +task_str);
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
            System.out.println("   " + tasks[task_index]);
            print_line();
        }

        public void unmarkTask(String taskID) {
            int task_index = Integer.parseInt(taskID) - 1;
            tasks[task_index].unmark();
            print_line();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks[task_index]);
            print_line();
        }
    }

}
