import java.util.ArrayList;

public class TaskList {
//    Task[] tasks;
    ArrayList<Task> tasks;
//    int num_of_tasks;

    public enum taskTypes {
        TODO,
        DEADLINE,
        EVENT
    }

    public static class Task {
        String task_str;
        boolean marked;
        public Task(String task_str) {
            this.task_str = task_str;
            this.marked = false;
        }
        public void mark() {
            marked = true;
        }
        public void unmark() {
            marked = false;
        }
        @Override
        public String toString() {
            String mark_str = marked? "X" : " ";
            return "[" + mark_str +"] " + this.task_str;
        }
    }

    public static class ToDoTask extends Task {
        public ToDoTask(String task_str) {
            super(task_str);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class DeadlineTask extends Task {
        String by;
        public DeadlineTask(String task_str) {
            super(task_str.substring(0,task_str.indexOf("/by")) );
            by = task_str.substring(task_str.indexOf("/by") + 3);
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() +" (by:" + this.by +")";
        }
    }

    public static class EventTask extends Task {
        String from;
        String to;
        public EventTask(String task_str) {
            super(task_str.substring(0,task_str.indexOf("/from")) );
            from = task_str.substring(task_str.indexOf("/from") + 5,task_str.indexOf("/to") );
            to = task_str.substring(task_str.indexOf("/to") + 3 );

        }

        @Override
        public String toString() {
            return "[D]" + super.toString() +" (from:" + this.from + " to:"+ this.to +")";
        }
    }

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public void addTask(String task_str, taskTypes task_type) throws InvalidInputException {
//            print_line();
        switch(task_type) {
            case TODO:
                tasks.add(new ToDoTask(task_str));
                break;
            case DEADLINE:
                if(!task_str.contains("/by")){
                    throw new InvalidInputException("Error: Deadline tasks must have a deadline (denoted with /by).");
                }
                tasks.add(new DeadlineTask(task_str));
                break;
            case EVENT:
                if(!task_str.contains("/from") || !task_str.contains("/to")){
                    throw new InvalidInputException("Error: Event tasks must have a start and end (denoted with /from and /to).");
                }
                tasks.add(new EventTask(task_str));
                break;
        }
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size()-1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void showList() {
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println( i+1 + "." + tasks.get(i).toString() );
        }
    }

    public void markTask(String taskID) throws InvalidInputException {
        int task_index = Integer.parseInt(taskID) - 1;
        if(task_index >= tasks.size()) {
            throw new InvalidInputException("Error: No task at index " + taskID + ". Please input a valid task number." );
        }
        tasks.get(task_index).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(task_index));
    }

    public void unmarkTask(String taskID) throws InvalidInputException  {
        int task_index = Integer.parseInt(taskID) - 1;
        if(task_index >= tasks.size()) {
            throw new InvalidInputException("Error: No task at index " + taskID + ". Please input a valid task number." );
        }
        tasks.get(task_index).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(task_index));
    }

    public void delete(String taskID) throws InvalidInputException  {
        int task_index = Integer.parseInt(taskID) - 1;
        if(task_index >= tasks.size()) {
            throw new InvalidInputException("Error: No task at index " + taskID + ". Please input a valid task number." );
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasks.get(task_index));
        tasks.remove(task_index);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}