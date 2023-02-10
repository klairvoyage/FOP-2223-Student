package h13.shared;

import h13.model.gameplay.Direction;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

import static h13.controller.GameConstants.ORIGINAL_GAME_BOUNDS;
import static org.tudalgo.algoutils.student.Student.crash;

/**
 * A {@link Utils} class containing utility methods.
 */
public class Utils {

    /**
     * Returns the closest position for the given {@link Bounds} that is within the game bounds.
     *
     * @param bounds The bounds to be clamped.
     * @return the clamped coordinate.
     * @see <a href="https://en.wikipedia.org/wiki/Clamping_(graphics)">Clamping_(graphics)</a>
     * @see h13.controller.GameConstants
     */
    public static Bounds clamp(final Bounds bounds) {
        // TODO: H1.1 - remove if implemented
        if (ORIGINAL_GAME_BOUNDS.contains(bounds)) return bounds;
        double minX = bounds.getMinX();
        double minY = bounds.getMinY();
        if (minX<ORIGINAL_GAME_BOUNDS.getMinX()) minX = ORIGINAL_GAME_BOUNDS.getMinX();
        if (minX+bounds.getWidth()>ORIGINAL_GAME_BOUNDS.getMaxX()) minX = ORIGINAL_GAME_BOUNDS.getMaxX() - bounds.getWidth();
        if (minY<ORIGINAL_GAME_BOUNDS.getMinY()) minY = ORIGINAL_GAME_BOUNDS.getMinY();
        if (minY+bounds.getHeight()>ORIGINAL_GAME_BOUNDS.getMaxY()) minY = ORIGINAL_GAME_BOUNDS.getMaxY() - bounds.getHeight();
        return new BoundingBox(minX, minY, bounds.getWidth(), bounds.getHeight());
    }

    /**
     * Returns the Moved Bounding Box for the given {@link Bounds}, {@link Direction}, velocity and time.
     *
     * @param bounds      The bounds to be moved.
     * @param velocity    The velocity of the movement.
     * @param direction   The direction of the movement.
     * @param elapsedTime The time elapsed since the last movement.
     * @return the moved bounds
     */
    public static Bounds getNextPosition(final Bounds bounds, final double velocity, final Direction direction, final double elapsedTime) {
        // TODO: H1.1 - remove if implemented
        double minX = bounds.getMinX() + direction.getX()*velocity*elapsedTime;
        double miny = bounds.getMinY() + direction.getY()*velocity*elapsedTime;
        return new BoundingBox(minX, miny, bounds.getWidth(), bounds.getHeight());
    }
}
