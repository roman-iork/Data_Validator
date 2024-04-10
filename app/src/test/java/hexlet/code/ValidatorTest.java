package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ValidatorTest {

    @Test
    public void testIsValidStringNoRestrictions() {
        var validator = new Validator();
        var strVal1 = validator.string();
        assertTrue(strVal1.isValid(""));
        assertTrue(strVal1.isValid(null));
        assertTrue(strVal1.isValid("abc"));
        strVal1.required();
        assertFalse(strVal1.isValid(""));
        assertFalse(strVal1.isValid(null));
        assertTrue(strVal1.isValid("abc"));
        strVal1.minLength(4);
        assertFalse(strVal1.isValid("ab"));
        assertTrue(strVal1.isValid("abcd"));
        strVal1.contains("ilk");
        assertFalse(strVal1.isValid("mil"));
        assertFalse(strVal1.isValid("ilk"));
        assertTrue(strVal1.isValid("milk"));
        strVal1.contains("ilk").minLength(2).contains("k");
        assertFalse(strVal1.isValid("k"));
        assertTrue(strVal1.isValid("lk"));
        var strVal2 = validator.string();
        strVal2.minLength(2);
        assertTrue(strVal2.isValid(null));
        strVal2.contains("mi");
        assertTrue(strVal2.isValid(null));
    }

    @Test
    public void testIsValidNumber() {
        var validator = new Validator();
        var numVal1 = validator.number();
        assertTrue(numVal1.isValid(null));
        assertTrue(numVal1.isValid(5));
        numVal1.required();
        assertFalse(numVal1.isValid(null));
        assertTrue(numVal1.isValid(5));
        numVal1.positive();
        assertFalse(numVal1.isValid(-5));
        assertTrue(numVal1.isValid(5));
        numVal1.range(-10, 10);
        assertFalse(numVal1.isValid(-5));
        assertTrue(numVal1.isValid(5));
        numVal1.range(-10, 10).range(0, 2);
        assertFalse(numVal1.isValid(-5));
        assertFalse(numVal1.isValid(5));
        var numVal2 = validator.number();
        numVal2.positive();
        assertTrue(numVal2.isValid(null));
        numVal2.range(0, 5);
        assertTrue(numVal2.isValid(null));
    }

    @Test
    public void testIsValidMap() {
        var validator = new Validator();
        var mapVal1 = validator.map();
        var data = new HashMap<Integer, Integer>();
        data.put(1, 1);
        assertTrue(mapVal1.isValid(null));
        assertTrue(mapVal1.isValid(data));
        mapVal1.required();
        assertFalse(mapVal1.isValid(null));
        assertTrue(mapVal1.isValid(data));
        mapVal1.sizeof(2);
        assertFalse(mapVal1.isValid(data));
        data.put(2, 2);
        assertTrue(mapVal1.isValid(data));
        mapVal1.sizeof(3).sizeof(2);
        assertTrue(mapVal1.isValid(data));
        var mapVal2 = validator.map();
        mapVal2.sizeof(2);
        assertTrue(mapVal2.isValid(null));
        mapVal2.required();
        assertFalse(mapVal2.isValid(null));
        assertTrue(mapVal2.isValid(data));
        data.put(3, 3);
        assertFalse(mapVal2.isValid(data));
    }
}
