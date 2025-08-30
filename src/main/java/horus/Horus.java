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
        try {
            saveFile = new Storage(filePath);
            if (!saveFile.isNew()) {
                ui.print("Retrieving saved tasks from local file.");

//                ui.printOutputArray(saveFile.readFile());
                ui.print(taskList.readFromFile(saveFile.readFile()));
            }
        } catch (IOException e) {
            ui.print("Error: Unable to access local files");
            throw new RuntimeException();
        }
    }

    /**
     * Runs the Horus program, where the program will continuously execute input commands until program exits
     */
    public void run() {
        String inputStr;
        String outputStr;
        boolean isExit = false;
        ui.printOutputString("Hello! I'm Horus\nWhat can I do for you?\n");
        while (!isExit) {
            inputStr = ui.getInputStr();

            if (inputStr.equals("bye")) {
                isExit = true;
                ui.print(saveFile.writeToFile(taskList.saveToFile()));
            }

            outputStr = parser.parse(inputStr);
            ui.printOutputString(outputStr);
        }

    }

    public static void main(String[] args) {
        String filepath = "data/taskdata.txt";
        Horus horus = new Horus(filepath);
        horus.run();
    }

}
