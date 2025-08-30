package horus.tasks;

import java.util.ArrayList;

/**
 * A list of tasks
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Represents the 3 different task classes
     */
    public enum taskTypes {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Creates a list of tasks
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Saves the content of the task list to an array to be saved to a file
     *
     * @return Array of strings with containing task data to be saved to a file
     */
    public String[] saveToFile() {
        String[] fileContents = new String[tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            fileContents[i] = tasks.get(i).getTaskData();
        }

        return fileContents;
    }

    /**
     *Adds the tasks in fileData to tasklist
     *
     * @param fileData Array of strings representing contents of a save file
     * @return String to be printed by Ui
     */
    public String readFromFile(String[] fileData)  {
        String out = "";
        int addedTasks = 0;
        for (int i = 0; i < fileData.length; i++) {
            try {
                tasks.add(Task.readTaskData(fileData[i]));
                addedTasks++;
            } catch (InvalidInputException e) {
                out += "Local file corrupted. " + fileData[i] + " is not a valid task\n";
            }
        }
        out += addedTasks + " tasks retrieved.";
        return out;
    }

    /**
     * Adds a task to the TaskList and prints a corresponding message if successful
     *
     * @param taskStr String containing task description and other details such as /to /from and /by
     * @param taskType enum representing type of task to be added
     * @return String to be printed by Ui
     * @throws InvalidInputException If taskStr is invalid
     */
    public String addTask(String taskStr, taskTypes taskType) throws InvalidInputException {
        if (taskStr == "") {
            throw new InvalidInputException("Error: Description of task cannot be empty.");
        }
        switch(taskType) {
        case TODO:
            tasks.add(new ToDoTask(taskStr));
            break;
        case DEADLINE:
            if(!taskStr.contains("/by")){
                throw new InvalidInputException("Error: Deadline tasks must have a deadline (denoted with /by).");
            }
            tasks.add(new DeadlineTask(taskStr));
            break;
        case EVENT:
            if(!taskStr.contains("/from") || !taskStr.contains("/to")){
                throw new InvalidInputException(
                        "Error: Event tasks must have a start and end (denoted with /from and /to).");
            }
            tasks.add(new EventTask(taskStr));
            break;
        }
        return "Got it. I've added this task:\n  " + tasks.get(tasks.size()-1) + "\nNow you have " + tasks.size() + " tasks in the list.\n";
    }

    //Prints the contents of the taskList
    /**
     * Returns a string represnting the contents of taskList to be printed
     *
     * @return A string with each line representing a task in tasklist
     */
    public String showList() {
        String out = "";
        for(int i = 0; i < tasks.size(); i++) {
            out +=  i+1 + "." + tasks.get(i).toString() + "\n";
        }
        return out;
    }

    /**
     * Marks selected task as done
     *
     * @param taskId String representing the index of task to be marked
     * @return String to be printed by Ui
     * @throws InvalidInputException If taskId is not a valid task index
     */
    public String markTask(String taskId) throws InvalidInputException {
        int taskIndex = Integer.parseInt(taskId) - 1;
        if(taskIndex >= tasks.size()) {
            throw new InvalidInputException(
                    "Error: No task at index " + taskId + ". Please input a valid task number." );
        }
        tasks.get(taskIndex).mark();
        return "Nice! I've marked this task as done:\n  " + tasks.get(taskIndex) + "\n";
    }

    /**
     * Unmarks selected task
     *
     * @param taskId String representing the index of task to be unmarked
     * @return String to be printed by Ui
     * @throws InvalidInputException If taskId is not a valid task index
     */
    public String unmarkTask(String taskId) throws InvalidInputException {
        int task_index = Integer.parseInt(taskId) - 1;
        if(task_index >= tasks.size()) {
            throw new InvalidInputException(
                    "Error: No task at index " + taskId + ". Please input a valid task number." );
        }
        tasks.get(task_index).unmark();
        return "OK, I've marked this task as not done yet:\n  " + tasks.get(task_index) + "\n";
    }

    /**
     * Deletes selected task
     *
     * @param taskId String representing the index of task to be deleted
     * @return String to be printed by Ui
     * @throws InvalidInputException If taskId is not a valid task index
     */
    public String delete(String taskId) throws InvalidInputException {
        int task_index = Integer.parseInt(taskId) - 1;
        if(task_index >= tasks.size()) {
            throw new InvalidInputException(
                    "Error: No task at index " + taskId + ". Please input a valid task number." );
        }

        String out = "Noted. I've removed this task:\n  " + tasks.get(task_index);
        tasks.remove(task_index);
        out += "\nNow you have " + tasks.size() + " tasks in the list.\n";
        return out;
    }
}