package cz.fi.muni.pa165.hockeymanager.mvc.formatters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeFormatter implements Formatter<Long> {

    private final static Logger logger = LoggerFactory.getLogger(LocalDateTimeFormatter.class);

    private final DateTimeFormatter formatter;

    public LocalDateTimeFormatter(String pattern) {
        formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public Long parse(String text, Locale locale) {
        //logger.info("Time: " + LocalDateTime.parse(text, formatter).toString());
        return LocalDateTime.parse(text, formatter).atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    @Override
    public String print(Long object, Locale locale) {
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm").withZone(ZoneId.systemDefault());
        Instant dateTimeInst = Instant.ofEpochSecond(object);
        return DATE_TIME_FORMATTER.format(dateTimeInst);
    }
}
