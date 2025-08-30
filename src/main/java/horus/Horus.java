package horus;

import horus.tasks.TaskList;

import java.io.IOException;

public class Horus {

    private final TaskList taskList;
    private final Storage saveFile;
    private final Ui ui;
    private final Parser parser;

    public Horus(String filePath) {
        taskList = new TaskList();
        parser = new Parser(taskList);
        ui = new Ui();
        try {
            saveFile = new Storage(filePath);
            if (!saveFile.checkIsNew()) {
                ui.print("Retrieving saved tasks from local file.");
                ui.print(taskList.readFromFile(saveFile.readFile()));
            }
        } catch (IOException e) {
            ui.print("Error: Unable to access local files");
            throw new RuntimeException();
        }
    }

    public void run() {
        boolean isExit = false;
        ui.printOutputString("Hello! I'm Horus\nWhat can I do for you?\n");

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

    public static void main(String[] args) {
        String filepath = "data/taskdata.txt";
        Horus horus = new Horus(filepath);
        horus.run();
    }

}
