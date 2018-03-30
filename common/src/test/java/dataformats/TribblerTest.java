package dataformats;

import org.junit.Test;

import static org.junit.Assert.*;

public class TribblerTest {

    @Test
    public void testIfValidTribblerIsValid() {
        Tribbler tribbler = new Tribbler(1L, "Frodo");
        assertTrue(tribbler.isValid());
    }

    @Test
    public void testIfNullIdIsValid() {
        Tribbler tribbler = new Tribbler(null, "Frodo");
        assertFalse(tribbler.isValid());
    }

    @Test
    public void testIfNullNameIsValid() {
        Tribbler tribbler = new Tribbler(1L, null);
        assertFalse(tribbler.isValid());
    }

}
