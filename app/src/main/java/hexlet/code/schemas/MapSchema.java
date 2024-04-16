package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema<K, V> extends BaseSchema<Map<K, V>> {

    public MapSchema<K, V> required() {
        Predicate<Map<K, V>> isMeaningful = Objects::nonNull;
        addCheck("required", isMeaningful);
        return this;
    }

    public MapSchema<K, V> sizeof(int size) {
        Predicate<Map<K, V>> isOfSize = data -> data == null || data.size() == size;
        addCheck("size", isOfSize);
        return this;
    }

    public MapSchema<K, V> shape(Map<K, BaseSchema<V>> schemas) {
        Predicate<Map<K, V>> isValidated = data -> {
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
