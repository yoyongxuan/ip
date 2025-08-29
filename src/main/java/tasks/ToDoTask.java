package tasks;

/**
 * An object representing a task to be done
 */
public class ToDoTask extends Task {

    /**
     * Creates a ToDoTask
     **
     * @param taskStr String representing the description of task to be created,
     */
    public ToDoTask(String taskStr) {
        super(taskStr);
    }

    @Override
    public String getFileString() {
        return "T," + super.getFileString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}