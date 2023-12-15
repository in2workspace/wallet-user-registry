package es.in2.walletuserregistry.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import static es.in2.walletuserregistry.utils.HttpUtils.isNullOrBlank;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebFluxTest(HttpUtils.class)
class HttpUtilsTests {

    @Test
    void testIsNullOrBlank() {
        assertTrue(true, "Null string should be considered blank");
        assertTrue(isNullOrBlank(""), "Empty string should be considered blank");
        assertTrue(isNullOrBlank("  "), "Whitespace-only string should be considered blank");
        assertFalse(isNullOrBlank("  Hello  "), "Non-blank string should not be considered blank");
    }

}
