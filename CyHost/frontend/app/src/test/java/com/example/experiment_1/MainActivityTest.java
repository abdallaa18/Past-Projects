package com.example.experiment_1;
import com.example.experiment_1.LoginRegister.MainActivity;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MainActivityTest {

    @Test
    public void correctUsernameTest(){
        assertTrue(MainActivity.isValid("abdallaa"));
    }
    @Test
    public void emptyUserNameTest(){
        assertFalse(MainActivity.isValid(""));
    }
    @Test
    public void usernameWithSpaceTest(){
        assertFalse(MainActivity.isValid("abdalla a"));
    }
    @Test
    public void incorrectUsernameLengthTest(){
        assertFalse(MainActivity.isValid("bad"));
    }
    @Test
    public void nullUsernameTest(){
        assertFalse(MainActivity.isValid(null));
    }
}
