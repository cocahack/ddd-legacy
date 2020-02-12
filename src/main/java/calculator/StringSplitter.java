package calculator;

import org.apache.logging.log4j.util.Strings;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringSplitter {

    private static final String DEFAULT_DELIMITER = ",|:";
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.)\n(.*)");

    public static List<String> split(final String input) {
        if (Strings.isEmpty(input)) {
            return Collections.emptyList();
        }

        Matcher m = CUSTOM_DELIMITER_PATTERN.matcher(input);
        if (m.find()) {
            String customDelimiter = m.group(1);
            String extractedInput = m.group(2);

            return splitWithDelimiter(extractedInput, customDelimiter);
        }

        return splitWithDelimiter(input, DEFAULT_DELIMITER);
    }


    private static List<String> splitWithDelimiter(String input, String delimiter) {
        return Stream.of(input.split(delimiter))
                .collect(Collectors.toList());
    }
}