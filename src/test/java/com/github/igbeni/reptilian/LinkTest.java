package com.github.igbeni.reptilian;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test cases for {@link com.github.igbeni.reptilian.Link}.
 *
 * @author iggor
 * @created 06/24/2020 - 14:19
 * @project reptilian
 */
class LinkTest {

    @Test
    public void validatesNull() {
        Link link = new Link("test", null);
        assertFalse(link.valid("www.igbeni.com"));
    }

    @Test
    public void validatesSelfFalse() {
        Link link = new Link("test", "www.github.com/igbeni");
        assertFalse(link.valid("www.igbeni.com"));
    }

    @Test
    public void validatesSelfTrue() {
        Link link = new Link("test", "www.github.com/igbeni");
        assertTrue(link.valid("www.github.com/igbeni/reptilian"));
    }
}
