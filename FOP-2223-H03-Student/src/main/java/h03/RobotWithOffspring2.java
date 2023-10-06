package h03;

import fopbot.Direction;

/**
 * {@link RobotWithOffspring2} is a derived class from {@link RobotWithOffspring}.
 */
public class RobotWithOffspring2 extends RobotWithOffspring {

    /**
     * Accumulated number of all direction changes.
     */
    private int directionAccu;

    /**
     * Constructor creates {@link RobotWithOffspring2}-object by invoking the
     * constructor of its parent class {@link RobotWithOffspring}.
     *
     * @param numberOfColumnsOfWorld    Number of columns in the world.
     * @param numberOfRowsOfWorld       Number of rows in the world.
     * @param direction                 Direction of {@link RobotWithOffspring2}.
     * @param numberOfCoins             Number of coins of {@link RobotWithOffspring2}.
     */
    public RobotWithOffspring2(int numberOfColumnsOfWorld, int numberOfRowsOfWorld, Direction direction, int numberOfCoins) {
        super(numberOfColumnsOfWorld, numberOfRowsOfWorld, direction, numberOfCoins);
    }

    /**
     * Overrides {@link RobotWithOffspring#initOffspring(Direction, int)} and invokes
     * {@link RobotWithOffspring#initOffspring(Direction, int)} & also initializes
     * the starting value of {@link RobotWithOffspring2#directionAccu}.
     *
     * @param direction         Direction of {@link RobotWithOffspring2#offspring}.
     * @param numberOfCoins     Number of coins of {@link RobotWithOffspring2#offspring}.
     */
    @Override
    public void initOffspring(Direction direction, int numberOfCoins) {
        super.initOffspring(direction, numberOfCoins);
        directionAccu = direction.ordinal();
    }

    /**
     * Converts {@link RobotWithOffspring2#directionAccu} into
     * a {@link Direction}-value (using modular arithmetic).
     *
     * @return      Direction that corresponds with the value of {@link RobotWithOffspring2#directionAccu}.
     */
    private Direction getDirectionFromAccu() {
        if (directionAccu%4<0) return Direction.values()[directionAccu%4+4];
        else return Direction.values()[directionAccu%4];
    }

    /**
     * Overrides {@link RobotWithOffspring#getDirectionOfOffspring()} and returns the direction
     * that corresponds with the value of {@link RobotWithOffspring2#directionAccu}.
     *
     * @return      The direction of {@link RobotWithOffspring2#offspring}.
     */
    @Override
    public Direction getDirectionOfOffspring() { return getDirectionFromAccu(); }

    /**
     * Overrides {@link RobotWithOffspring#addToDirectionOfOffspring(int)} and, if
     * initialized, adds the value, by which {@link RobotWithOffspring2#offspring}
     * will be rotated, onto {@link RobotWithOffspring2#directionAccu} & then
     * rotates it until a direction is reached that corresponds with the
     * {@link Direction}-value of {@link RobotWithOffspring2#directionAccu}.
     *
     * @param deltaDirectionValue   Value by which {@link RobotWithOffspring2#offspring} is rotated.
     */
    @Override
    public void addToDirectionOfOffspring(int deltaDirectionValue) {
        if (offspringIsInitialized()) {
            directionAccu += deltaDirectionValue;
            while (super.getDirectionOfOffspring()!=getDirectionOfOffspring()) offspring.turnLeft();
        }
    }

}
