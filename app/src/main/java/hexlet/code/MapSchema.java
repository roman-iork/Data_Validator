package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema sizeof(int size) {
        restrictions.put("size", size);
        return this;
    }

    public boolean computeSecondRestriction(Map<?, ?> data) {
        var sizeRestriction = restrictions.get("size");
        return sizeRestriction == null || data == null || data.size() == (int) sizeRestriction;
    }

    public boolean computeThirdRestriction(Map<?, ?> data) {
        return true;
    }
}
