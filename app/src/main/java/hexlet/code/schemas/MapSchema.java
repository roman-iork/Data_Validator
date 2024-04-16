package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    public MapSchema required() {
        Predicate<Map<String, String>> isMeaningful = Objects::nonNull;
        addCheck("required", isMeaningful);
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Map<String, String>> isOfSize = data -> data == null || data.size() == size;
        addCheck("size", isOfSize);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        Predicate<Map<String, String>> isValidated = data -> {
            if (data == null || schemas == null) {
                return true;
            }
            for (var key : data.keySet()) {
                var schema = schemas.get(key);
                if (schemas.containsKey(key) && !schema.isValid(data.get(key))) {
                    return false;
                }
            }
            return true;
        };
        addCheck("shape", isValidated);
        return this;
    }
}
