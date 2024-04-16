package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    protected final void addCheck(String checkName, Predicate<T> check) {
        checks.put(checkName, check);
    }

    public final boolean isValid(T data) {
        for (var check : checks.values()) {
            if (!check.test(data)) {
                return false;
            }
        }
        return true;
    }
}
