package horus.tasks;

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
    public String getTaskData() {
        String taskData = "T," + super.getTaskData();
        assert new ToDoTask(taskData).equals(this);

        return taskData;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object inputObj) {
        if (!(inputObj instanceof Task inputTask)){
            return false;
        }
        if (!(inputTask.equals(this))) {
            return false;
        }

        return true;
    }
}