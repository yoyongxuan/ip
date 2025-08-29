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
    public String getTaskData() {
        String markStr = this.isMarked? "T" : "F";
        return markStr + "," + this.taskDesc;
    }

    public static Task readTaskData(String taskData) throws InvalidInputException {
        Task out;
        String[] taskDataArray = taskData.split(",", 3);
        String taskType = taskDataArray[0];
        String markedStr = taskDataArray[1];
        String taskStr = taskDataArray[2];
        switch (taskType) {
        case "T":
            out = new ToDoTask(taskStr);
            break;
        case "D":
            if(!taskStr.contains("/by")){
                throw new InvalidInputException("Error: Deadline tasks must have a deadline (denoted with /by).");
            }
            out = new DeadlineTask(taskStr);
            break;
        case "E":
            if(!taskStr.contains("/from") || !taskStr.contains("/to")){
                throw new InvalidInputException(
                        "Error: Event tasks must have a start and end (denoted with /from and /to).");
            }
            out = new EventTask(taskStr);
            break;
        default:
            throw new InvalidInputException(
                    "Error: Invalid task type.");
        }

        if (markedStr.equals("T")) {
            out.mark();
        }

        return out;
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
