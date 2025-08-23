import java.util.Scanner;  // Import the Scanner class

public class Horus {
    static String chatbot_name = "Horus";
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        TaskList tasklist = new TaskList();
        String last_input;
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;


        greet();

        while (!exit) {
            last_input = scanner.nextLine();
            exit = last_input.equals("bye");
            if (last_input.equals("list")) {
                tasklist.showList();
            } else if (!exit) {
                tasklist.addTask(last_input);
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

    public static void list() {

        String[] tasks = new String[100];
        int num_of_tasks = 0;
        boolean exit = false;
        String last_input;
        Scanner scanner = new Scanner(System.in);

        print_line();
        while (!exit) {
            last_input = scanner.nextLine();
            exit = last_input.equals("bye");
            if (last_input.equals("list")) {
                print_line();
                for(int i = 0; i < num_of_tasks; i++) {
                    System.out.println( Integer.toString(i+1) + ". " + tasks[i]);
                }
                print_line();
            } else if (!exit) {
                print_line();
                tasks[num_of_tasks] = last_input;
                num_of_tasks++;
                System.out.println("added: " +last_input);
                print_line();
            }

        }
    }


    public static void print_line() {
        System.out.println("____________________________________________________________" );
    }

    public static class TaskList {
        String[] tasks;
        int num_of_tasks;
        public TaskList() {
            tasks = new String[100];
            num_of_tasks = 0;
        }

        public void addTask(String task_str) {
            print_line();
            tasks[num_of_tasks] = task_str;
            num_of_tasks++;
            System.out.println("added: " +task_str);
            print_line();
        }

        public void showList() {
            print_line();
            for(int i = 0; i < num_of_tasks; i++) {
                System.out.println( Integer.toString(i+1) + ". " + tasks[i]);
            }
            print_line();
        }
    }

}
