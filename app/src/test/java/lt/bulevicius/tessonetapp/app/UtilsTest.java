package lt.bulevicius.tessonetapp.app;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void testBearer() {
        assertEquals("Bearer 123", Utils.addBearer("123"));
    }

    @Test
    public void testKmFormat() {
        assertEquals("123 km", Utils.addKilometers(123));
    }
}