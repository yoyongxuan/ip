package horus;

import horus.tasks.InvalidInputException;
import horus.tasks.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testByeCommand() {
        Parser parser = new Parser(new TaskList());
        assertEquals("Bye. Hope to see you again soon!\n", parser.parse("bye "));
    }

    @Test
    public void testListCommand() throws InvalidInputException {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);
        assertEquals(taskList.showList() , parser.parse("list"));
        taskList.addTask("clean room", TaskList.taskTypes.TODO);
        taskList.addTask("homework /by 1900", TaskList.taskTypes.DEADLINE);
        assertEquals(taskList.showList() , parser.parse("list"));
    }

    @Test
    public void testAddTaskCommands() throws InvalidInputException {
        TaskList expected = new TaskList();
        TaskList actual = new TaskList();
        Parser parser = new Parser(actual);

        assertEquals(expected.addTask("clean room", TaskList.taskTypes.TODO),
                parser.parse("todo clean room"));
        assertEquals(expected.showList(), actual.showList()  );

        assertEquals(expected.addTask("homework /by 1900", TaskList.taskTypes.DEADLINE),
                parser.parse("deadline homework /by 1900"));
        assertEquals(expected.showList(), actual.showList()  );

        assertEquals(expected.addTask("class /from 1300 /to 1500", TaskList.taskTypes.EVENT),
                parser.parse("event class /from 1300 /to 1500"));
        assertEquals(expected.showList(), actual.showList()  );
    }

    @Test
    public void testMarkUnmarkCommands() throws InvalidInputException {
        TaskList expected = new TaskList();
        TaskList actual = new TaskList();
        expected.addTask("clean room", TaskList.taskTypes.TODO);
        actual.addTask("clean room", TaskList.taskTypes.TODO);

        Parser parser = new Parser(actual);

        assertEquals(expected.markTask("1") , parser.parse("mark 1"));
        assertEquals(expected.showList(), actual.showList()  );

        assertEquals(expected.unmarkTask("1") , parser.parse("unmark 1"));
        assertEquals(expected.showList(), actual.showList()  );
    }

    @Test
    public void testDeleteCommand() throws InvalidInputException {
        TaskList expected = new TaskList();
        TaskList actual = new TaskList();
        Parser parser = new Parser(actual);

        expected.addTask("clean room", TaskList.taskTypes.TODO);
        expected.addTask("homework /by 1900", TaskList.taskTypes.DEADLINE);
        expected.addTask("class /from 1300 /to 1500", TaskList.taskTypes.EVENT);

        actual.addTask("clean room", TaskList.taskTypes.TODO);
        actual.addTask("homework /by 1900", TaskList.taskTypes.DEADLINE);
        actual.addTask("class /from 1300 /to 1500", TaskList.taskTypes.EVENT);


        assertEquals(expected.delete("3") , parser.parse("delete 3"));
        assertEquals(expected.showList(), actual.showList()  );

        assertEquals(expected.delete("1") , parser.parse("delete 1"));
        assertEquals(expected.showList(), actual.showList()  );

        assertEquals(expected.delete("1") , parser.parse("delete 1"));
        assertEquals(expected.showList(), actual.showList()  );
    }


    @Test
    public void testMissingDetails() {
        Parser parser = new Parser(new TaskList());
        String[] commandsToTest = new String[]{"mark", "unmark", "delete"};
        for (int i = 0; i < commandsToTest.length; i++) {
            assertEquals("Error: Must select task number to " + commandsToTest[i] + ".\n",
                    parser.parse(commandsToTest[i]) );
        }

        commandsToTest = new String[]{"todo", "deadline", "event"};
        for (int i = 0; i < commandsToTest.length; i++) {
            assertEquals("Error: Description of task cannot be empty.\n",
                    parser.parse(commandsToTest[i]) );
        }
    }

    @Test
    public void testInvalidTaskDetails() {
        Parser parser = new Parser(new TaskList());
        String[] commandsToTest = new String[]{"mark", "unmark", "delete"};
        for (int i = 0; i < commandsToTest.length; i++) {
            assertEquals("Error: abc is not an integer. Please input task number to "
                            + commandsToTest[i] +"\n",
                    parser.parse(commandsToTest[i] + " abc") );
        }
    }

}
