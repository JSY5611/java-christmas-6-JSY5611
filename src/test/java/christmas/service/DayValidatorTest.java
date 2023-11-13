package christmas.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayValidatorTest {

    @Test
    void 날짜_예외_테스트() {
        assertThrows(IllegalArgumentException.class, ()
               -> new DayValidator("a"));
    }

    @Test
    void 날짜_예외_테스트2() {
        assertThrows(IllegalArgumentException.class, ()
               -> new DayValidator("32"));
    }

}