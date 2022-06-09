package h03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fopbot.*;

class H3_2_UnitTest {
    /*
     * Beispiel für ein JUnit-Test
     * 
     * assertEquals überprüft, ob der erste aktuale Parameterwert gleich dem zweiten
     * aktualen Parameterwert ist. Ist dies nicht der Fall, schlägt der JUnit-Test
     * fehl.
     */
    @Test
    void testRobotWithOffspringTwins() {
        World.setSize(10, 10);
        World.setDelay(0);

        // TwinRobots twins = new TwinRobots(10, 10);

        // assertEquals überprüft, ob der erste aktuale Parameterwert gleich dem zweiten
        // aktualen Parameterwert ist. Ist dies nicht der Fall, schlägt der JUnit-Test
        // fehl.

        // assertEquals(twins.getRobotByIndex(0).getDirectionOfOffspring(),
        // twins.getRobotByIndex(1).getDirectionOfOffspring());
    }
}
