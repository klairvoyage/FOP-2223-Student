package projekt.base;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * A tuple for the x- and y-coordinates of a point.
 */
public final class Location implements Comparable<Location> {

    private final static Comparator<Location> COMPARATOR =
        Comparator.comparing(Location::getX).thenComparing(Location::getY);

    private final int x;
    private final int y;

    /**
     * Instantiates a new {@link Location} object using {@code x} and {@code y} as coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of this location.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this location.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Adds the coordinates of this location and the other location and returns a new
     * {@link Location} object with the resulting coordinates.
     *
     * @param other the other {@link Location} object to get the second set of coordinates from
     * @return a new {@link Location} object with the sum of coordinates from both locations
     */
    public Location add(Location other) {
        return new Location(x + other.x, y + other.y);
    }

    /**
     * Subtracts the coordinates of this location from the other location and returns a new
     * {@link Location} object with the resulting coordinates.
     *
     * @param other the other {@link Location} object to get the second set of coordinates from
     * @return a new {@link Location} object with the difference of coordinates from both locations
     */
    public Location subtract(Location other) {
        return new Location(x - other.x, y - other.y);
    }

    @Override
    public int compareTo(@NotNull Location o) {
        if(x!=o.getX()) return x-o.getX();
        if(y!=o.getY()) return y-o.getY();
        return 0;
    }

    @Override
    public int hashCode() {
        return x*1000000+y;
    }

    @Override
    public boolean equals(Object o) {
        if(o==null) return false;
        if(o instanceof Location loc) return this.compareTo(loc)==0;
        return false;
    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}
