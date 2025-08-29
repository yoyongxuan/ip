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

    /**
     * Returns string to be saved to file
     * @return String representing this task
     */
    public String getFileString() {
        String markStr = this.isMarked? "T" : "F";
        return markStr + "," + this.taskDesc;
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
        String markStr = isMarked? "X" : " ";
        return "[" + markStr +"] " + this.taskDesc;
    }
}
