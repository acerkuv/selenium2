package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.RegEx;

import static org.junit.jupiter.api.Assertions.*;

class WebDrivingTest {

    @Test
    void getStringByRegexpTest() {
//        get numbers from string
        Assertions.assertEquals("1", WebDriving.getStringByRegexp(
                "(\\d++)", "quuiui(1)gjhgjhj"
        ));

    }

    @Test
    void isInString() {
        assertEquals(true, WebDriving.isInString("^re..+stage$", "quuiui(1)gjhgjhi"));
    }
}