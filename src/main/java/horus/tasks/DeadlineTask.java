package horus.tasks;

/**
 * An object representing a task with a deadline
 */
public class DeadlineTask extends Task {
    CustomDateTime by;

    /**
     * Creates a deadline task
     **
     * @param taskStr String representing the description of task to be created
     *                and a deadline denoted by /by.
     * @throws InvalidInputException If dateTimeStr contains a substring
     *         with the correct date or time formatting (dd/MM/yyyy and HHmm) but is not a valid date or time
     */
    public DeadlineTask(String taskStr) throws InvalidInputException{
        super(taskStr.substring(0,taskStr.indexOf("/by")) );
        by =  new CustomDateTime(taskStr.substring(taskStr.indexOf("/by") + 3));
    }

    @Override
    public String getTaskData() {
        String taskData = "D," + super.getTaskData() + "/by " + this.by.getData();

        try {
            assert new DeadlineTask(taskData).equals(this);
        } catch (InvalidInputException e) {
            throw new AssertionError(e);
            //An invalid input exception will only occur if the assert statement is false
        }

        return taskData;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +" (by: " + this.by +")";
    }

    @Override
    public boolean equals(Object inputObj) {
        if (!(inputObj instanceof Task inputTask)){
            return false;
        }
        if (!(inputTask.equals(this))) {
            return false;
        }

        if (!(inputTask instanceof DeadlineTask inputDeadlineTask)){
            return false;
        }

        return inputDeadlineTask.by.equals(this.by);

    }
}
