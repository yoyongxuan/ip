package tasks;

/**
 * An object representing a task
 */
public class Task {
    String taskDesc;
    boolean isMarked;

    /**
     * Creates a task
     **
     * @param taskStr String representing the description of task to be created
     */
    public Task(String taskStr) {
        this.taskDesc = taskStr;
        this.isMarked = false;
    }

    //Marks task as done
    public void mark() {
        isMarked = true;
    }

    //Unmarks task
    public void unmark() {
        isMarked = false;
    }


    @Override
    public String toString() {
        String mark_str = isMarked? "X" : " ";
        return "[" + mark_str +"] " + this.taskDesc;
    }
}
