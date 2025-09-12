package horus.tasks;

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
    String remainingStr;

    /**
     * Creates a CustomDateTime object based on dateTimeStr
     **
     * @param dateTimeStr String which may or may not start with a date and time
     */
    public CustomDateTime(String dateTimeStr) {
        date = null;
        time = null;
        remainingStr = "";
        dateTimeStr = dateTimeStr.strip();
        String[] dateTimeArray = dateTimeStr.split(" ", 3);

        for (int i = 0; i < dateTimeArray.length; i++ ) {
            try {
                date = LocalDate.parse(dateTimeArray[i], DateTimeFormatter.ofPattern("d/M/yyyy") );
                continue;
            } catch (DateTimeException e) {
                //Will save contents to remainingStr instead of initializing a date
            }
            try {
                time = LocalTime.parse(dateTimeArray[i], DateTimeFormatter.ofPattern("HHmm") );
                continue;
            } catch (DateTimeException e) {
                //Will save contents to remainingStr instead of initializing a time
            }

            remainingStr += dateTimeArray[i];
            remainingStr += " ";

        }
        remainingStr = remainingStr.strip();
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

    @Override
    public boolean equals(Object inputObj){
        if (!(inputObj instanceof CustomDateTime dateTimeInput)){
            return false;
        }
        boolean isSameDate = this.date.equals(dateTimeInput.date);
        boolean isSameTime = this.time.equals(dateTimeInput.time);
        boolean isSameRemainingStr = this.remainingStr.equals(dateTimeInput.remainingStr);

        return isSameDate && isSameTime && isSameRemainingStr;
    }

    /**
     * Returns a string similar to the input dateTimeStr
     *
     * @return A string from which a similar CustomDateTime object may be initialized from
     */
    public String getData() {
        String dateStr = date == null ? "" : date.format( DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " ";
        String timeStr = time == null ? "" : time.format( DateTimeFormatter.ofPattern("HHmm"))  + " ";
        String outputDateTimeStr = dateStr + timeStr + remainingStr;

        assert new CustomDateTime(outputDateTimeStr).equals(this);
        return outputDateTimeStr;
    }

}
