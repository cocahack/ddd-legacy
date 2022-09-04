package calculator;

import kitchenpos.utils.StringUtils;

import static kitchenpos.utils.NumberUtils.fromStringArrayConvertToPositiveNumberArray;
import static kitchenpos.utils.NumberUtils.sum;

public class StringCalculator {

    public int add(String text) {
        if (text == null || text.isBlank()) {
            return 0;
        }
        return sum(extractPositiveNumbers(text));
    }

    private int[] extractPositiveNumbers(String text) {
        return fromStringArrayConvertToPositiveNumberArray(splitText(text));
    }

    private String[] splitText(String text) {
        return StringUtils.filterTextByDelimiter(text);
    }

}
