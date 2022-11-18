package h03;

import fopbot.Direction;

/**
 * {@link TwinRobots} is a class with two similar robots.
 */
public class TwinRobots {

    /**
     * {@link RobotWithOffspring}-array that contains two similar robots.
     */
    private final RobotWithOffspring[] robots;

    /**
     * Constructor creates {@link TwinRobots}-object and initializes
     * {@link TwinRobots#robots} with the array length 2 and
     * fills both indices with two (not same but) identical
     * instances of a robot but then creates a different
     * offspring robot for each of them.
     *
     * @param numberOfColumnsOfWorld    Number of columns in the world.
     * @param numberOfRowsOfWorld       Number of rows in the world.
     */
    public TwinRobots(int numberOfColumnsOfWorld, int numberOfRowsOfWorld) {
        robots = new RobotWithOffspring[2];
        robots[0] = new RobotWithOffspring(numberOfColumnsOfWorld, numberOfRowsOfWorld, Direction.RIGHT, 0);
        robots[1] = new RobotWithOffspring2(numberOfColumnsOfWorld, numberOfRowsOfWorld, Direction.UP, 0);
        robots[0].initOffspring(Direction.LEFT, 0);
        robots[1].initOffspring(Direction.LEFT, 0);
    }

    /**
     * Returns the robot that is in {@link TwinRobots#robots} (at the respective index).
     *
     * @param index     Index of the robot in {@link TwinRobots#robots} that will be returned.
     * @return          Robot that is in {@link TwinRobots#robots} (at the respective index).
     */
    public RobotWithOffspring getRobotByIndex(int index) { return robots[index]; }

    /**
     * Invokes the addToDirectionOfOffspring-method of the respective robots to change their direction.
     *
     * @param n     Value by which the offspring robot of the respective robots will be rotated.
     */
    public void addToDirectionOfBothOffsprings(int n) {
        robots[0].addToDirectionOfOffspring(n);
        robots[1].addToDirectionOfOffspring(n);
    }

}
