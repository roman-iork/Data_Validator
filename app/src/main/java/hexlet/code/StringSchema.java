package hexlet.code;
import java.util.HashMap;
import java.util.Map;

public class StringSchema {
    private Map<String, Object> restrictions = new HashMap<>();
    private Map<String, Boolean> validations = new HashMap<>();


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

    public boolean isValid(String string) {
        validations.put("required", computeRequired(string));
        validations.put("minLength", computeMinLength(string));
        validations.put("contains", computeContains(string));
        return !validations.containsValue(false);
    }

    private boolean computeRequired(String string) {
        var isRequired = restrictions.get("required");
        return isRequired == null || string != null && !string.isEmpty();
    }

    private boolean computeMinLength(String string) {
        var minLength = restrictions.get("minLength");
        return minLength == null || string != null && string.length() >= (int) minLength;
    }

    private boolean computeContains(String string) {
        var subString = restrictions.get("contains");
        return subString == null || string != null && string.contains((String) subString);
    }
}
