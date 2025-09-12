package horus.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomDateTimeTest {

    @Test
    public void testDateOnlyInput() {
        CustomDateTime testObj = new CustomDateTime("19/12/2023");
        assertEquals("Dec 19 2023 11:59pm ", testObj.toString() );
        testGetData(testObj);
    }

    @Test
    public void testTimeOnlyInput() {
        CustomDateTime testObj = new CustomDateTime("1600");
        assertEquals( LocalDate.now().format( DateTimeFormatter.ofPattern("MMM d yyyy")) + " 4:00pm ", testObj.toString() );
        testGetData(testObj);
    }

    @Test
    public void testDateTimeInput() {
        CustomDateTime testObj = new CustomDateTime("30/08/2025 1300");
        assertEquals("Aug 30 2025 1:00pm ", testObj.toString() );
        testGetData(testObj);
    }

    @Test
    public void testDateTimeWithExtraStringInput() {
        CustomDateTime testObj = new CustomDateTime("30/08/2025 1300 after lunch");
        assertEquals("Aug 30 2025 1:00pm after lunch", testObj.toString() );
        testGetData(testObj);
    }

//    @Test
//    public void testInvalidDate() {
//        Throwable exception = assertThrows(InvalidInputException.class, () -> new CustomDateTime("30/13/2025 after lunch"));
//        assertEquals("Error: 30/13/2025 is not a valid date!", exception.getMessage());
//    }
//
//    @Test
//    public void testInvalidTime() {
//        Throwable exception = assertThrows(InvalidInputException.class, () -> new CustomDateTime("1375 after lunch"));
//        assertEquals("Error: 1375 is not a valid time!", exception.getMessage());
//    }

    public void testGetData(CustomDateTime testObj)  {
        assertEquals(testObj.toString(), new CustomDateTime(testObj.getData()).toString() );
    }
}
