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
            print_line();
            command_task = input_str.split("\\s+",2); //Isolates the substring behind the first space in the input
//            System.out.println(command_task[0]);
//            System.out.println(command_task[1]);

            try {
                switch (command_task[0]) {
                    case "bye":
                        exit = true;
                        exit();
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
                    case "delete":
                        tasklist.delete(command_task[1]);
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
                        System.out.println("Error: '" + input_str + "' is not a valid command.");
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                switch (command_task[0]) {
                    case "mark":
                    case "unmark":
                    case "delete":
                        System.out.println("Error: Must select task number to " + command_task[0] + "." );
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        System.out.println("Error: Description of task cannot be empty.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: " + command_task[1] + " is not an integer. Please input task number to " + command_task[0] );
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
            print_line();
        }

    }

    public static void greet() {
        print_line();
        System.out.println("Hello! I'm " + chatbot_name + "\nWhat can I do for you?\n" );
    }

    public static void exit() {
//        print_line();
        System.out.println("Bye. Hope to see you again soon!" );
//        print_line();
    }

    public static void print_line() {
        System.out.println("____________________________________________________________" );
    }



}
