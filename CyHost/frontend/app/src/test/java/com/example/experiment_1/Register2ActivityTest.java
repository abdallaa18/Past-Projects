package com.example.experiment_1;
import com.example.experiment_1.LoginRegister.Register2Activity;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class Register2ActivityTest {
    @Test
    public void passwordValidator_CorrectPassword(){
        boolean result = Register2Activity.isValidPassword("coms309", "coms309");

        assertTrue(result);
    }
    @Test
    public void passwordValidator_IncorrectPassword(){
        boolean result = Register2Activity.isValidPassword("coms309", "com309");

        assertFalse(result);
    }
    @Test
    public void passwordValidator_NullPassword(){
        boolean result = Register2Activity.isValidPassword(null, "com309");
        boolean result2 = Register2Activity.isValidPassword("coms309", null);
        boolean result3 = Register2Activity.isValidPassword(null, null);

        assertFalse(result);
        assertFalse(result2);
        assertFalse(result3);

    }
    @Test
    public void passwordValidator_passwordWithSpace(){
        boolean result = Register2Activity.isValidPassword("coms 309", "coms309");
        boolean result2 = Register2Activity.isValidPassword("coms309", "coms 309");
        boolean result3 = Register2Activity.isValidPassword(" coms309", "coms 309");
        assertFalse(result);
        assertFalse(result2);
        assertFalse(result3);

    }
    @Test
    public void passwordValidator_badLength(){
        boolean result = Register2Activity.isValidPassword("bad", "bad");
      assertFalse(result);

    }
    @Test
    public void usernameValidator_CorrectUsername(){
        assertTrue(Register2Activity.isValidUsername("abdallaa"));

    }
    @Test
    public void usernameValidator_EmptyUsername(){
        assertFalse(Register2Activity.isValidUsername(""));

        assertFalse(Register2Activity.isValidUsername("    "));
    }
    @Test
    public void usernameValidator_NullUsername(){
        assertFalse(Register2Activity.isValidUsername(null));

    }
    @Test
    public void usernameValidator_BadLength(){
        assertFalse(Register2Activity.isValidUsername("bad"));

    }
}
