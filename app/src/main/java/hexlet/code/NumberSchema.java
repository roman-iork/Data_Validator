package hexlet.code;

public class NumberSchema extends BaseSchema<Integer> {

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
