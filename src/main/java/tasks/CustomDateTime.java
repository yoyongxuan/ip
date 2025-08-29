package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An object representing a time which may or may not contain a date and a time of day
 */
public class CustomDateTime {
    LocalDate date;
    LocalTime time;
    LocalDateTime datetime;
    String remainingStr;

    /**
     * Creates a CustomDateTime object based on dateTimeStr
     **
     * @param dateTimeStr String which may or may not start with a date and time
     * @throws InvalidInputException If dateTimeStr contains a substring with the correct date or time formatting
     *         but is not a valid date or time
     */
    public CustomDateTime(String dateTimeStr) throws InvalidInputException {
        date = null;
        time = null;
        remainingStr = "";
        String[] dateTimeArray = dateTimeStr.split(" ", 3);
        for (int i = 0; i < dateTimeArray.length; i++ ) {
            if (dateTimeArray[i].length() == 10
                    && dateTimeArray[i].charAt(2) == '/'
                    && dateTimeArray[i].charAt(5) == '/'  ) {
                try {
                    date = LocalDate.parse(dateTimeArray[i], DateTimeFormatter.ofPattern("dd/MM/yyyy") );
                } catch (DateTimeException e) {
                    throw new InvalidInputException("Error: " + dateTimeArray[i] + " is not a valid date!");
                }
            } else if ( dateTimeArray[i].length() == 4
                    && Character.isDigit(dateTimeArray[i].charAt(0))
                    && Character.isDigit(dateTimeArray[i].charAt(1))
                    && Character.isDigit(dateTimeArray[i].charAt(2))
                    && Character.isDigit(dateTimeArray[i].charAt(3))) {
                try {
                    time = LocalTime.parse(dateTimeArray[i], DateTimeFormatter.ofPattern("HHmm") );
                } catch (DateTimeException e) {
                    throw new InvalidInputException("Error: " + dateTimeArray[i] + " is not a valid time!");
                }
            } else {
                remainingStr += dateTimeArray[i];
                remainingStr += " ";
            }
        }
        remainingStr = remainingStr.strip();

        if (date == null && time == null) {
            datetime = null;
        } else if (date == null) {
            datetime = LocalDateTime.of(LocalDate.now() , time);
        } else if (time == null) {
            datetime = LocalDateTime.of(date, LocalTime.of(0,0) );
        } else {
            datetime = LocalDateTime.of(date, time );
        }
    }

    /**
     * Returns a string similar to the input dateTimeStr
     *         with dates and times converted to "MMM d yyyy" and "H:mma" formats
     */
    @Override
    public String toString() {
        String dateStr = date == null ? "" : date.format( DateTimeFormatter.ofPattern("MMM d yyyy")) + " ";
        String timeStr = time == null ? "" : time.format( DateTimeFormatter.ofPattern("h:mma"))  + " ";
        return dateStr + timeStr + remainingStr;
    }

    public String getData() {
        String dateStr = date == null ? "" : date.format( DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " ";
        String timeStr = time == null ? "" : time.format( DateTimeFormatter.ofPattern("HHmm"))  + " ";
        return dateStr + timeStr + remainingStr;
    }

}
