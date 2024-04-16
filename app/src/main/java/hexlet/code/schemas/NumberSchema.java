package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        Predicate<Integer> isMeaningful = Objects::nonNull;
        addCheck("required", isMeaningful);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> isPositive = data -> data == null || data > 0;
        addCheck("positive", isPositive);
        return this;
    }

    public NumberSchema range(int start, int end) {
        Predicate<Integer> isInsideRange = data -> data != null && data >= start && data <= end;
        addCheck("range", isInsideRange);
        return this;
    }
}
