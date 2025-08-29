package tasks;

/**
 * An object representing a task with a deadline
 */
public class DeadlineTask extends Task {
    String by;

    /**
     * Creates a deadline task
     **
     * @param taskStr String representing the description of task to be created
     *                and a deadline denoted by /by
     */
    public DeadlineTask(String taskStr) {
        super(taskStr.substring(0,taskStr.indexOf("/by")) );
        by = taskStr.substring(taskStr.indexOf("/by") + 3);
    }

    @Override
    public String getTaskData() {
        return "D," + super.getTaskData() + "/by" + this.by ;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +" (by:" + this.by +")";
    }
}
