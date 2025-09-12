package horus.tasks;

/**
 * An object representing an event task
 */
public class EventTask extends Task {
    CustomDateTime from;
    CustomDateTime to;

    /**
     * Creates an event task
     **
     * @param taskStr String representing the description of task to be created,
     *                the start of the event denoted by /from
     *                and the end of the event denoted by /to
     * @throws InvalidInputException If dateTimeStr contains a substring
     *         with the correct date or time formatting (dd/MM/yyyy and HHmm) but is not a valid date or time
     */
    public EventTask(String taskStr) throws InvalidInputException {
        super(taskStr.substring(0,taskStr.indexOf("/from")) );
        from = new CustomDateTime(taskStr.substring(taskStr.indexOf("/from") + 5,taskStr.indexOf("/to") )) ;
        to =  new CustomDateTime(taskStr.substring(taskStr.indexOf("/to") + 3 ));

    }

    @Override
    public String getTaskData() {
        String taskData = "E," + super.getTaskData() + "/from " + this.from.getData() + " /to " + this.to.getData();

        try {
            assert new EventTask(taskData).equals(this);
        } catch (InvalidInputException e) {
            throw new AssertionError(e);
            //An invalid input exception will only occur if the assert statement is false
        }

        return taskData;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +" (from: " + this.from + " to: "+ this.to +")";
    }

    @Override
    public boolean equals(Object inputObj) {
        if (!(inputObj instanceof Task inputTask)){
            return false;
        }
        if (!(inputTask.equals(this))) {
            return false;
        }

        if (!(inputTask instanceof EventTask inputEventTask)){
            return false;
        }

        return inputEventTask.from.equals(this.from) && inputEventTask.to.equals(this.to);

    }
}
