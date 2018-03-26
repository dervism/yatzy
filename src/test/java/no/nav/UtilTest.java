package no.nav;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void max() {
        assertTrue(Util.max(Arrays.asList(1, 1, 1)) == 1);
    }
}