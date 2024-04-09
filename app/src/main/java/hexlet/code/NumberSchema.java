package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class NumberSchema extends BaseSchema<Integer> {
    private Map<String, Object> restrictions = new HashMap<>();

    public NumberSchema required() {
        restrictions.put("required", true);
        return this;
    }

    public NumberSchema positive() {
        restrictions.put("positive", true);
        return this;
    }

    public NumberSchema range(int start, int end) {
        restrictions.put("startRange", start);
        restrictions.put("endRange", end);
        return this;
    }

    public boolean computeFirstRestriction(Integer data) {
        var isRequired = restrictions.get("required");
        return isRequired == null || data != null;
    }

    public boolean computeSecondRestriction(Integer data) {
        var isPositive = restrictions.get("positive");
        return isPositive == null || data == null || data > 0;
    }

    public boolean computeThirdRestriction(Integer data) {
        var startRange = restrictions.get("startRange");
        var endRange = restrictions.get("endRange");
        return startRange == null || data == null || data >= (int) startRange && data <= (int) endRange;
    }
}
