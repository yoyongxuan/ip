import java.util.Scanner;  // Import the Scanner class

public class Horus {
    static String chatbot_name = "Horus";
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        greet();
        echo();
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

    public static void echo() {
        boolean exit = false;
        String last_input;
        Scanner scanner = new Scanner(System.in);

        print_line();
        while (!exit) {
            last_input = scanner.nextLine();
            exit = last_input.equals("bye");
            if (!exit) {
                print_line();
                System.out.println(last_input);
                print_line();
            }

        }
    }

    public static void print_line() {
        System.out.println("____________________________________________________________" );
    }


}
