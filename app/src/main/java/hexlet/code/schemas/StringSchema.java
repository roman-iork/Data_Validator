package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        Predicate<String> isMeaningful = data -> data != null && !data.isEmpty();
        addCheck("required", isMeaningful);
        return this;
    }

    public StringSchema minLength(int minLength) {
        Predicate<String> isLongEnough = data -> data == null || data.length() >= minLength;
        addCheck("minLength", isLongEnough);
        return this;
    }

    public StringSchema contains(String subString) {
        Predicate<String> hasSubstring = data -> data == null || data.contains(subString);
        addCheck("contains", hasSubstring);
        return this;
    }
}
