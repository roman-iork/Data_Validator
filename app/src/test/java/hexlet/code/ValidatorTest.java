package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ValidatorTest {

    @Test
    public void testIsValidStringNoRestrictions() {
        var validator = new Validator();
        var strVal = validator.string();
        assertTrue(strVal.isValid(""));
        assertTrue(strVal.isValid(null));
        assertTrue(strVal.isValid("abc"));
    }

    @Test
    public void testIsValidStringRequired() {
        var validator = new Validator();
        var strVal = validator.string();
        strVal.required();
        assertFalse(strVal.isValid(""));
        assertFalse(strVal.isValid(null));
        assertTrue(strVal.isValid("abc"));
    }

    @Test
    public void testIsValidStringMinLength() {
        var validator = new Validator();
        var strVal = validator.string();
        strVal.minLength(3);
        assertFalse(strVal.isValid(""));
        assertFalse(strVal.isValid(null));
        assertTrue(strVal.isValid("abc"));
    }

    @Test
    public void testIsValidStringContains() {
        var validator = new Validator();
        var strVal = validator.string();
        strVal.contains("olo");
        assertFalse(strVal.isValid(""));
        assertFalse(strVal.isValid(null));
        assertTrue(strVal.isValid("moloko"));
        assertFalse(strVal.isValid("mol"));
    }

    @Test
    public void testIsValidStringCombined() {
        var validator = new Validator();
        var strVal = validator.string();
        strVal.required();
        strVal.minLength(4);
        strVal.contains("olo");
        assertFalse(strVal.isValid(""));
        assertFalse(strVal.isValid(null));
        assertTrue(strVal.isValid("moloko"));
        assertFalse(strVal.isValid("olo"));
    }

    @Test
    public void testIsValidStringRestrictionRepetition() {
        var validator = new Validator();
        var strVal = validator.string();
        strVal.minLength(4);
        assertTrue(strVal.isValid("moloko"));
        strVal.minLength(8);
        assertFalse(strVal.isValid("moloko"));
    }

    @Test
    public void testIsValidStringRestrictionChained() {
        var validator = new Validator();
        var strVal = validator.string();
        strVal.required().minLength(6).minLength(4).contains("mil").contains("ilk").minLength(2);
        assertTrue(strVal.isValid("milk"));
    }
}
