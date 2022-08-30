package h11.parse;

import h11.LSystem;
import h11.LSystemGrowerImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FractalTreeTest {

    private final LSystem<Character> lSystem = new ParsedLSystem(List.of(
        new Projection('0', "1[0]0"),
        new Projection('1', "11")
    ));

    @Test
    void testAxiom() {
        assertEquals('0', lSystem.getAxiom());
    }

    @Test
    void testProject() {
        var grower = new LSystemGrowerImpl<>(lSystem);

        String[] expected = {
            "0",
            "1[0]0",
            "11[1[0]0]1[0]0",
            "1111[11[1[0]0]1[0]0]11[1[0]0]1[0]0"
        };

        var actual = grower.grow()
            .limit(expected.length)
            .map(l ->
                l.stream()
                .map(String::valueOf)
                .collect(Collectors.joining()))
            .toArray(String[]::new);

        assertArrayEquals(expected, actual);
    }
}
