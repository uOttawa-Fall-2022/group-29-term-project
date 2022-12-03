package com.example.virtualclassroom;

import org.junit.Test;
import static org.junit.Assert.*;

public class Deliverable2UnitTest {

    @Test
    public void testStartEndTime(){
        assertTrue(StartEndTimeValidator.hasStartAndEndTime("1 pm - 2pm"));
    }

    @Test
    public void testStartNoEndTime(){
        assertFalse(StartEndTimeValidator.hasStartAndEndTime("1 pm"));
    }

    @Test
    public void testMultiStartEndTimes(){
        assertTrue(StartEndTimeValidator.hasStartAndEndTime("1 pm - 2pm, 3am - 12pm"));
    }

    @Test
    public void testMissingEndTime(){
        assertFalse(StartEndTimeValidator.hasStartAndEndTime("1am,2pm - 3pm"));
    }

    @Test
    public void missingSecondTime(){
        assertFalse(StartEndTimeValidator.hasStartAndEndTime("1am - 1:30am,"));
    }
}
