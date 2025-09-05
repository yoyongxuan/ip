package horus;

import horus.tasks.TaskList;

import java.io.IOException;

/**
 * A program that can be used to manage a list of tasks and save onto a local file
 */
public class Horus {
    private final TaskList taskList;
    private final Storage saveFile;
    private final Ui ui;
    private final Parser parser;
    private static final String DEFAULT_FILEPATH = "data/taskdata.txt";
    private String greetingMessage;

    /**
     * Initializes Horus and retrieves any previously saved tasks from a save file
     *
     * @param filePath A string representing the file path where any tasks added will be saved.
     *                 If the file already exists, Horus will initialize with any tasks saved on the file
     */
    public Horus(String filePath) {
        taskList = new TaskList();
        parser = new Parser(taskList);
        ui = new Ui();
        greetingMessage = "";
        try {
            saveFile = new Storage(filePath);
            if (!saveFile.checkIsNew()) {
                greetingMessage += "Retrieving saved tasks from local file.\n";
                greetingMessage += taskList.readFromFile(saveFile.readFile());
            }
        } catch (IOException e) {
            greetingMessage += "Error: Unable to access local files";
            throw new RuntimeException();
        }
        greetingMessage += "\nHello! I'm Horus\nWhat can I do for you?";
    }

    /**
     * Initializes Horus using the default filepath
     */
    public Horus() {
        this(Horus.DEFAULT_FILEPATH);
    }

    /**
     * Runs the Horus program, where the program will continuously execute input commands until program exits
     */
    public void run() {
        boolean isExit = false;
        ui.printOutputString(greetingMessage);

        while (!isExit) {
            String inputStr = ui.getInputStr();

            if (inputStr.equals("bye")) {
                isExit = true;
                ui.print(saveFile.writeToFile(taskList.saveToFile()));
            }

            String outputStr = parser.parse(inputStr);
            ui.printOutputString(outputStr);
        }

    }

    public String getGreetingMessage() {
        return this.greetingMessage;
    }

    public String getResponse(String inputStr) {
        String outputStr = "";
        if (inputStr.equals("bye")) {
            outputStr += saveFile.writeToFile(taskList.saveToFile());
        }

        outputStr += parser.parse(inputStr);
        return outputStr;
    }

    public static void main(String[] args) {
        String filepath = "data/taskdata.txt";
        Horus horus = new Horus(filepath);
        horus.run();
    }

}
