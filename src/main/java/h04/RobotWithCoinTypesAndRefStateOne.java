package h04;

import fopbot.Direction;

/**
 * Robot with different coin types and reference state (attr-version).
 */
public class RobotWithCoinTypesAndRefStateOne extends RobotWithCoinTypes implements RobotWithReferenceState {

    private int refX;

    private int refY;

    private Direction refDirection;

    private int refNumberOfCoins;

    /**
     * Constructor invokes constructors of its parents class
     * with its given parameters & initializes its attributes.
     *
     * @param x                         x-position of robot.
     * @param y                         y-position of robot.
     * @param direction                 Direction of robot.
     * @param numberOfSilverCoins       Number of silver coins of robot.
     * @param numberOfBrassCoins        Number of brass coins of robot.
     * @param numberOfCopperCoins       Number of copper coins of robot.
     */
    public RobotWithCoinTypesAndRefStateOne(int x, int y, Direction direction, int numberOfSilverCoins, int numberOfBrassCoins, int numberOfCopperCoins) {
        super (x, y, direction, numberOfSilverCoins, numberOfBrassCoins, numberOfCopperCoins);
        refX = x;
        refY = y;
        refDirection = direction;
        refNumberOfCoins = numberOfSilverCoins + numberOfBrassCoins + numberOfCopperCoins;
    }

    @Override
    public void setCurrentStateAsReferenceState() {
        refX = super.getX();
        refY = super.getY();
        refDirection = super.getDirection();
        refNumberOfCoins = super.getNumberOfCoins();
    }

    @Override
    public int getDiffX() {
        return super.getX() - refX;
    }

    @Override
    public int getDiffY() {
        return super.getY() - refY;
    }

    @Override
    public Direction getDiffDirection() {
        int rotation = refDirection.ordinal() - super.getDirection().ordinal();
        if (rotation<0) rotation+=4;
        if (rotation==0) return Direction.UP;
        else if (rotation==1) return Direction.LEFT;
        else if (rotation==2) return Direction.DOWN;
        else return Direction.RIGHT;
    }

    @Override
    public int getDiffNumberOfCoins() {
        return super.getNumberOfCoins() - refNumberOfCoins;
    }

}
