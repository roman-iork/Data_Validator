package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ValidatorTest {

    @Test
    public void testIsValidString() {
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
        strVal2.required().minLength(2).contains("mi");
        assertFalse(strVal2.isValid(null));
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
        numVal2.required().positive().range(2, 6);
        assertFalse(numVal1.isValid(7));
    }

    @Test
    public void testIsValidMap() {
        var validator = new Validator();
        MapSchema<Integer, Integer> mapVal1 = validator.map();
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
        MapSchema<Integer, Integer> mapVal2 = validator.map();
        mapVal2.sizeof(2);
        assertTrue(mapVal2.isValid(null));
        mapVal2.required();
        assertFalse(mapVal2.isValid(null));
        assertTrue(mapVal2.isValid(data));
        data.put(3, 3);
        assertFalse(mapVal2.isValid(data));
        mapVal2.required().sizeof(1);
        assertFalse(mapVal2.isValid(data));
    }

    @Test
    public void testIsValidShape() {
        var validator = new Validator();
        MapSchema<String, String> mapVal1 = validator.map();
        var strVal1 = validator.string();
        var strVal2 = validator.string();
        var schemas1 = new HashMap<String, BaseSchema<String>>();
        schemas1.put("item", strVal1.required().minLength(3).contains("item"));
        schemas1.put("status", strVal2.required().minLength(2).contains("="));
        mapVal1.shape(schemas1);
        var data1 = Map.of("item", "item1", "status", "=yes");
        var data2 = Map.of("item", "itm2", "status", "no");
        var data3 = Map.of("thing", "item2", "stat", "=yes");
        assertTrue(mapVal1.isValid(data1));
        assertFalse(mapVal1.isValid(data2));
        assertTrue(mapVal1.isValid(data3));
        MapSchema<Integer, Integer> mapVal2 = validator.map();
        var numVal1 = validator.number();
        var numVal2 = validator.number();
        var schemas2 = new HashMap<Integer, BaseSchema<Integer>>();
        schemas2.put(1, numVal1.required().positive().range(0, 100));
        schemas2.put(2, numVal2.range(0, 100));
        mapVal2.shape(schemas2).sizeof(2);
        var data4 = new HashMap<Integer, Integer>();
        data4.put(1, 5);
        data4.put(2, null);
        var data5 = new HashMap<Integer, Integer>();
        data5.put(1, 5);
        data5.put(3, 50);
        assertTrue(mapVal2.isValid(data4));
        assertTrue(mapVal2.isValid(data5));
        MapSchema<String, Integer> mapVal3 = validator.map();
        var schemas3 = new HashMap<String, BaseSchema<Integer>>();
        schemas3.put("3", numVal1.required().range(-5, 6));
        schemas3.put("5", numVal1.range(1, 6));
        mapVal3.shape(schemas3);
        var data6 = new HashMap<String, Integer>();
        data6.put("1", 5);
        data6.put("2", null);
        assertTrue(mapVal3.isValid(data6));
        MapSchema<String, String> mapVal4 = validator.map();
        mapVal4.shape(null);
        assertTrue(mapVal4.isValid(data1));
        assertTrue(mapVal3.isValid(null));
    }
}
