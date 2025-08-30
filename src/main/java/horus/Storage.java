package horus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

/**
 * Class that represents a file with methods to read and write said file
 */
public class Storage {
    private final File file;
    private final boolean isNewFile;

    /**
     * Creates an object representing the file at the input file path
     * Creates directories if directories in file path does not exist
     * Creates file if file does not exist
     *
     * @param filePath A string representing the file path to be initialized
     * @throws IOException If file path cannot be accessed by the program
     */
    public Storage(String filePath) throws IOException {
        String directoryPath = filePath.substring(0, filePath.lastIndexOf('/'));

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        file = new File(filePath);
        isNewFile = file.createNewFile();
    }

    /**
     * Checks if file represented by Storage was newly created
     *
     * @return true if file was newly created and false otherwise
     */
    public boolean checkIsNew() {
        return isNewFile;
    }

    /**
     * Writes the contents of fileContents into this.file
     *
     * @param fileContents An array of strings with each string representing a line to be written
     * @return String to be printed by Ui
     */
    public String writeToFile(String[] fileContents) {
        try {
            FileWriter taskWriter = new FileWriter(file);
            for (int i = 0; i < fileContents.length; i++) {
                taskWriter.write(fileContents[i] + "\n");
            }
            taskWriter.close();
        } catch (IOException e) {
            return "Error: Unable to access local file, tasks not saved.";
        }

        return "Task list saved.";
    }

    /**
     * Reads and returns the contents of this.file
     *
     * @return An array of strings with each string representing a line in the file
     * @throws IOException if file cannot be accessed
     */
    public String[] readFile() throws IOException {
        int lineCount = 0;
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            scanner.nextLine();
            lineCount++;
        }

        String[] out = new String[lineCount];

        lineCount = 0;
        scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String taskData = scanner.nextLine();
            out[lineCount] = taskData;
            lineCount++;
        }

        scanner.close();
        return out;
    }
}
