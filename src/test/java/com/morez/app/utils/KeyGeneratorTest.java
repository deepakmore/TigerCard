package com.morez.app.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KeyGeneratorTest {

    @Test
    public void shouldGenerateSameHashcodeIfSourceAndDestinationAreSame() {
        String source = "ZoneOne", destination = "ZoneOne";
        int actual = KeyGenerator.generate(source, destination);
        assertEquals(source.hashCode(), actual);
    }

    @Test
    public void shouldGenerateHashcodeIfSourceAndDestinationAreNotSame() {
        String source = "ZoneTwo", destination = "ZoneOne";
        int actual = KeyGenerator.generate(source, destination);
        assertEquals((source + destination).hashCode(), actual);
    }

    @Test
    public void shouldGenerateSameHashcodeIfSourceAndDestinationAreSwapped() {
        String source = "ZoneOne", destination = "ZoneTwo";
        int actualSourceToDestination = KeyGenerator.generate(source, destination);
        int actualDestinationToSource = KeyGenerator.generate(destination, source);
        assertEquals(actualDestinationToSource, actualSourceToDestination);
    }
}