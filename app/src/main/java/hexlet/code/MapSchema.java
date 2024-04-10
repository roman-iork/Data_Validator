package hexlet.code;

import java.util.ArrayList;
import java.util.Map;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {

    public MapSchema<K, V> required() {
        restrictions.put("required", true);
        return this;
    }

    public MapSchema<K, V> sizeof(int size) {
        restrictions.put("size", size);
        return this;
    }

    public MapSchema<K, V> shape(Map<K, BaseSchema<V>> schemas) {
        restrictions.put("shape", schemas);
        return this;
    }

    public boolean computeSecondRestriction(Map<K, V> data) {
        var sizeRestriction = restrictions.get("size");
        return sizeRestriction == null || data == null || data.size() == (int) sizeRestriction;
    }

    public boolean computeThirdRestriction(Map<K, V> data) {
        var shape = (Map<K, BaseSchema<V>>) restrictions.get("shape");
        var checkList = new ArrayList<Boolean>();
        if (shape == null || data == null) {
            return true;
        } else {
            for (var entry : data.entrySet()) {
                var key = entry.getKey();
                var value = entry.getValue();
                var scheme = shape.get(key);
                var check = shape.containsKey(key) && scheme.isValid(value);
                checkList.add(check);
            }
        }
        return !checkList.contains(false);
    }
}
