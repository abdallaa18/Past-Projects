package com.example.experiment_1;
import com.example.experiment_1.Event.Event;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 27)
public class EventTest {
    private static final String EVENT_NAME = "Event Testing";
    private static final String EVENT_ID = "100";
    private static final String EVENT_DESCRIPTION = "Event Mochioto Testing";
    private static final String EVENT_DATE = "10-29-2019";
    private static final String EVENT_HOST = "abdallaa";
    private static final String EVENT_PRIVACY = "Public";
    private Event eventEntry;

    @Mock
    Event mMockBrokenEvent;

    @Mock
    Event mMockEvent;

    @Before
    public void initMocks(){
        eventEntry = new Event(EVENT_NAME, EVENT_ID, EVENT_DESCRIPTION,EVENT_DATE,EVENT_HOST, EVENT_PRIVACY);
        mMockEvent = eventEntry;
    }

@Test
   public void eventReturn_informationTest(){
        String ename = mMockEvent.getEventName();
        String edesc = mMockEvent.getEventDescription();
        String edate = mMockEvent.getEventDate();
        String ehost = mMockEvent.getHost();
        String eid = mMockEvent.getId();

        assertThat("Checking that mMockEvent returns the right name", ename, is(EVENT_NAME));
    assertThat("Checking that mMockEvent returns the right ID", eid, is(EVENT_ID));
    assertThat("Checking that mMockEvent returns the right Description", edesc, is(EVENT_DESCRIPTION));
    assertThat("Checking that mMockEvent returns the right Date", edate, is(EVENT_DATE));
    assertThat("Checking that mMockEvent returns the right Host", ehost, is(EVENT_HOST));

}


}
