package cz.fi.muni.pa165.hockeymanager.mvc.formatters;


import java.util.Locale;
import java.time.ZoneId;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.format.Formatter;

public class LocalDateTimeFormatter implements Formatter<Long> {

    private final DateTimeFormatter formatter;

    public LocalDateTimeFormatter(String pattern) {
        formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public Long parse(String text, Locale locale) {
        return LocalDateTime.parse(text, formatter).atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    @Override
    public String print(Long object, Locale locale) {
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.systemDefault());
        Instant dateTimeInst = Instant.ofEpochSecond(object);
        return DATE_TIME_FORMATTER.format(dateTimeInst);
    }
}
