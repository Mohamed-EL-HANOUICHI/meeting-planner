package meeting.planner.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm";

    public static LocalDateTime toLocalDateTime(String date) {

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDateTime.parse(date, formatter);
    }
}
