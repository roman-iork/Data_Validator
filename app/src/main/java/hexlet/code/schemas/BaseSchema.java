package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseSchema<T> {

    private Map<String, Boolean> validations = new HashMap<>();
    protected Map<String, Object> restrictions = new HashMap<>();


    public final boolean isValid(T data) {
        validations.put("firstRestriction", computeFirstRestriction(data));
        validations.put("secondRestriction", computeSecondRestriction(data));
        validations.put("thirdRestriction", computeThirdRestriction(data));
        return !validations.containsValue(false);
    }

    public abstract boolean computeFirstRestriction(T data);
    public abstract boolean computeSecondRestriction(T data);
    public abstract boolean computeThirdRestriction(T data);
}
