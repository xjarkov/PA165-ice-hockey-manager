package cz.fi.muni.pa165.hockeymanager.mvc.formatters;

import cz.fi.muni.pa165.hockeymanager.mvc.LoggedInFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    private final static Logger logger = LoggerFactory.getLogger(LocalDateTimeFormatter.class);

    private final DateTimeFormatter formatter;

    public LocalDateTimeFormatter(String pattern) {
        formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public LocalDateTime parse(String text, Locale locale) {
        logger.info("Time: " + LocalDateTime.parse(text, formatter).toString());
        return LocalDateTime.parse(text, formatter);
    }

    @Override
    public String print(LocalDateTime object, Locale locale) {
        return formatter.format(object);
    }
}
