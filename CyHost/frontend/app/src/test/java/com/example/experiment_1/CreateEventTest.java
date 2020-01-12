package com.example.experiment_1;

import android.location.Location;
import android.os.Bundle;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CreateEventTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void getLocality_returnsAgra() throws JSONException {
        LocationHandler test = mock(LocationHandler.class);
        CreateEventFragment testLocation = new CreateEventFragment();
        testLocation.address = "Taj Mahal";
        testLocation.locality = "Agra";
        String response;
        response = "Agra";

        when(test.getLocality("Taj Mahal")).thenReturn(response);
        Assert.assertEquals(testLocation.findLocality("Taj Mahal", test), "Agra");
    }

    @Test
    public void getLocality_returnsAmes() throws JSONException {
        LocationHandler test = mock(LocationHandler.class);
        CreateEventFragment testLocation = new CreateEventFragment();

        String Address = "Iowa State University";
        testLocation.locality = null;
        String response;
        response = "false";

        when(test.getLocality(Address)).thenReturn(response);
        Assert.assertEquals(testLocation.findLocality(Address, test), "false");
    }

    @Test
    public void validAddress_returnsTrue() throws JSONException {
        LocationHandler test = mock(LocationHandler.class);
        CreateEventFragment testLocation = new CreateEventFragment();

        String Address = "Ames";
        String response;
        response = "true";

        when(test.validAddress(Address)).thenReturn(response);
        Assert.assertEquals(testLocation.validAddress(Address, test), "true");
    }
    @Test
    public void validAddress_returnsFalse() throws JSONException {
        LocationHandler test = mock(LocationHandler.class);
        CreateEventFragment testLocation = new CreateEventFragment();

        String Address = null;
        String response;
        response = "false";

        when(test.validAddress(Address)).thenReturn(response);
        Assert.assertEquals(testLocation.validAddress(Address, test), "false");
    }
}