package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.example.WebDrivingTwo.getStringByRegexp;
import static org.example.WebDrivingTwo.isInString;
import static org.junit.jupiter.api.Assertions.*;

class WebDrivingTwoTest {

    @Test
    void isInStringTest() {
        Assertions.assertTrue( isInString("^re..+star.$","review(3)/score/star4"));
        String s = "review(3)/score/star4";
        Assertions.assertEquals("4", s.substring(s.length()-1));
    }

    @Test
    void getStringByRegexpT() {
        String s = "review(3)/score/star4";

        assertEquals("3)", getStringByRegexp("\\d+.",s));
    }

}