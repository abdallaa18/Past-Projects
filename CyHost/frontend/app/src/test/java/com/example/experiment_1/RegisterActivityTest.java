package com.example.experiment_1;
import com.example.experiment_1.LoginRegister.RegisterActivity;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class RegisterActivityTest {
    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(RegisterActivity.isValidEmail("name@email.com"));
    }
    @Test
    public void emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(RegisterActivity.isValidEmail("name@email"));
    }
    @Test
    public void emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(RegisterActivity.isValidEmail("name@email.co.uk"));
    }
    @Test
    public void emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(RegisterActivity.isValidEmail("name@email..com"));
    }
    @Test
    public void emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(RegisterActivity.isValidEmail("@email.com"));
    }
    @Test
    public void emailValidator_EmptyString_ReturnsFalse() {
        assertFalse(RegisterActivity.isValidEmail(""));
    }
    @Test
    public void emailValidator_NullEmail_ReturnsFalse() {
        assertFalse(RegisterActivity.isValidEmail(null));
    }
    @Test
    public void zipCodeValidator_CorrectZipCodeTest(){
        assertTrue(RegisterActivity.isValidZip("55125"));
    }
    @Test
    public void zipCodeValidator_lessLengthTest(){
        assertFalse(RegisterActivity.isValidZip("5"));
        assertFalse(RegisterActivity.isValidZip("55"));
        assertFalse(RegisterActivity.isValidZip("551"));
        assertFalse(RegisterActivity.isValidZip("5512"));
    }
    @Test
    public void zipCodeValidator_biggerLengthTest(){
        assertTrue(RegisterActivity.isValidZip("55125"));
        assertFalse(RegisterActivity.isValidZip("551251"));
        assertFalse(RegisterActivity.isValidZip("5512512"));
        assertFalse(RegisterActivity.isValidZip("55125123"));
    }
    @Test
    public void zipCodeValidator_spaceIncluded(){
        assertTrue(RegisterActivity.isValidZip("55125"));
        assertFalse(RegisterActivity.isValidZip("551 25"));
        assertFalse(RegisterActivity.isValidZip("5 5125"));
        assertFalse(RegisterActivity.isValidZip(" 55125"));
        assertFalse(RegisterActivity.isValidZip("55125 "));
    }
    @Test
    public void zipCodeValidator_nullZipCode(){
        assertFalse(RegisterActivity.isValidZip(null));

    }

    @Test
    public void nameValidator_nullName(){
        assertFalse(RegisterActivity.isValidName(null));

    }
    @Test
    public void nameValidator_CorrectName(){
        assertTrue(RegisterActivity.isValidName("Abdalla"));
        assertTrue(RegisterActivity.isValidName("abdalla"));

    }

    @Test
    public void nameValidator_EmptyName(){
        assertFalse(RegisterActivity.isValidName(""));


    }
    @Test
    public void nameValidator_SpacesIncluded(){
        assertFalse(RegisterActivity.isValidName("Abdalla Abdelrahman"));


    }
}
