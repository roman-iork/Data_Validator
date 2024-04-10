package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        restrictions.put("required", true);
        return this;
    }

    public StringSchema minLength(int minLength) {
        restrictions.put("minLength", minLength);
        return this;
    }

    public StringSchema contains(String subString) {
        restrictions.put("contains", subString);
        return this;
    }

    public boolean computeFirstRestriction(String string) {
        var isRequired = restrictions.get("required");
        return isRequired == null || string != null && !string.isEmpty();
    }

    public boolean computeSecondRestriction(String string) {
        var minLength = restrictions.get("minLength");
        return minLength == null || string == null || string.length() >= (int) minLength;
    }

    public boolean computeThirdRestriction(String string) {
        var subString = restrictions.get("contains");
        return subString == null || string == null || string.contains((String) subString);
    }
}
