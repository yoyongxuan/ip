public class Horus {
    static String chatbot_name = "Horus";
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {


        greet();
        exit();
    }

    public static void greet() {
        print_line();
        System.out.println("Hello! I'm " + chatbot_name + "\nWhat can I do for you?\n" );
    }

    public static void exit() {
        print_line();
        System.out.println("Bye. hope to see you again soon!\n" );
        print_line();
    }

    public static void print_line() {
        System.out.println("____________________________________________________________" );
    }


}
