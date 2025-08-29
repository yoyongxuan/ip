package tasks;

/**
 * An object representing an event task
 */
public class EventTask extends Task {
    String from;
    String to;

    /**
     * Creates an event task
     **
     * @param taskStr String representing the description of task to be created,
     *                the start of the event denoted by /from
     *                and the end of the event denoted by /to
     */
    public EventTask(String taskStr) {
        super(taskStr.substring(0,taskStr.indexOf("/from")) );
        from = taskStr.substring(taskStr.indexOf("/from") + 5,taskStr.indexOf("/to") );
        to = taskStr.substring(taskStr.indexOf("/to") + 3 );

    }

    @Override
    public String getFileString() {
        return "E," + super.getFileString() + "/from" + this.from + "/to" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +" (from:" + this.from + " to:"+ this.to +")";
    }
}
