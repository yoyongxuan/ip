package tasks;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
     * Saves the content of the task list to a file such that it can be used to initialize another task list
     *
     * @param saveFile File object where the task list is to be saved to
     * @throws IOException If saveFile cannot be accessed
     */
    public void saveToFile(File saveFile) throws IOException {
        FileWriter taskWriter = new FileWriter(saveFile);
        for (int i = 0; i < tasks.size(); i++) {
            taskWriter.write(tasks.get(i).getTaskData() + "\n");
        }
        taskWriter.close();
        System.out.println("Task list saved.");
    }

    /**
     * Reads the contents of the file and adds tasks to tasklist
     *
     * @param saveFile File object where the task list is to be read from
     * @throws IOException If saveFile cannot be accessed
     */
    public void readFromFile(File saveFile) throws IOException {
        Scanner taskReader = new Scanner(saveFile);
        while (taskReader.hasNextLine()) {
            String taskData = taskReader.nextLine();
            try {
                tasks.add(Task.readTaskData(taskData));
            } catch (InvalidInputException e) {
                System.out.println("Local file corrupted. " + taskData + " is not a valid task");
            }
        }
        taskReader.close();
    }

    /**
     * Adds a task to the TaskList and prints a corresponding message if successful
     *
     * @param taskStr String containing task description and other details such as /to /from and /by
     * @param taskType enum representing type of task to be added
     * @throws InvalidInputException If taskStr is invalid
     */
    public void addTask(String taskStr, taskTypes taskType) throws InvalidInputException {
//            print_line();
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
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size()-1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    //Prints the contents of the taskList
    public void showList() {
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println( i+1 + "." + tasks.get(i).toString() );
        }
    }

    /**
     * Marks selected task as done
     *
     * @param taskId String representing the index of task to be marked
     * @throws InvalidInputException If taskId is not a valid task index
     */
    public void markTask(String taskId) throws InvalidInputException {
        int task_index = Integer.parseInt(taskId) - 1;
        if(task_index >= tasks.size()) {
            throw new InvalidInputException(
                    "Error: No task at index " + taskId + ". Please input a valid task number." );
        }
        tasks.get(task_index).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(task_index));
    }

    /**
     * Unmarks selected task
     *
     * @param taskId String representing the index of task to be unmarked
     * @throws InvalidInputException If taskId is not a valid task index
     */
    public void unmarkTask(String taskId) throws InvalidInputException {
        int task_index = Integer.parseInt(taskId) - 1;
        if(task_index >= tasks.size()) {
            throw new InvalidInputException(
                    "Error: No task at index " + taskId + ". Please input a valid task number." );
        }
        tasks.get(task_index).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(task_index));
    }

    /**
     * Deletes selected task
     *
     * @param taskId String representing the index of task to be deleted
     * @throws InvalidInputException If taskId is not a valid task index
     */
    public void delete(String taskId) throws InvalidInputException {
        int task_index = Integer.parseInt(taskId) - 1;
        if(task_index >= tasks.size()) {
            throw new InvalidInputException(
                    "Error: No task at index " + taskId + ". Please input a valid task number." );
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasks.get(task_index));
        tasks.remove(task_index);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}