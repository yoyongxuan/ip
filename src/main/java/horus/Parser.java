package horus;

import horus.tasks.TaskList;
import horus.tasks.InvalidInputException;


/**
 * A class that is able to parse input strings and execute relevant commands on this.taskList
 */
public class Parser {
    TaskList taskList;

    /**
     * Initializes parser with taskList
     *
     * @param taskList The TaskList on which future commands are executed
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses inputStr for commands and executes relevant commands on this.taskList
     *
     * @param inputStr String representing user input
     * @return String to be printed by Ui
     */
    public String parse(String inputStr) {
        String[] inputArray = inputStr.split("\\s+",2);
        String command = inputArray[0];
//        String details;
//        if (inputArray.length == 1) {
//            details = "";
//        } else {
//            details = inputArray[1];
//        }
        try {
            switch (command) {
            case "bye":
                return "Bye. Hope to see you again soon!\n";
            case "list":
                return taskList.showList();
            case "mark":
                return taskList.markTask(inputArray[1]);
            case "unmark":
                return taskList.unmarkTask(inputArray[1]);
            case "delete":
                return taskList.delete(inputArray[1]);
            case "todo":
                return taskList.addTask(inputArray[1], TaskList.taskTypes.TODO);
            case "deadline":
                return taskList.addTask(inputArray[1], TaskList.taskTypes.DEADLINE);
            case "event":
                return taskList.addTask(inputArray[1], TaskList.taskTypes.EVENT);
            default:
                return "Error: '" + inputStr + "' is not a valid command.\n";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            switch (command) {
                case "mark":
                case "unmark":
                case "delete":
                    return "Error: Must select task number to " + command + ".\n";
                case "todo":
                case "deadline":
                case "event":
                    return "Error: Description of task cannot be empty.\n";
            }
        } catch (NumberFormatException e) {
            return "Error: " + inputArray[1] + " is not an integer. Please input task number to "
                            + command +"\n";
        } catch (InvalidInputException e) {
            return e.getMessage() +"\n";
        }
        return "";
    }
}
