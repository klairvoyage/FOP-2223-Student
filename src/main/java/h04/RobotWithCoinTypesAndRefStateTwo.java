package h04;

import fopbot.Direction;

/**
 * Robot with different coin types and reference state (obj-version).
 */
public class RobotWithCoinTypesAndRefStateTwo extends RobotWithCoinTypes implements RobotWithReferenceState {

    private ReferenceRobot refRobot;

    /**
     * Constructor invokes constructors of its parents class
     * with its given parameters & initializes
     * {@link RobotWithCoinTypesAndRefStateTwo#refRobot}.
     *
     * @param x                         x-position of robot.
     * @param y                         y-position of robot.
     * @param direction                 Direction of robot.
     * @param numberOfSilverCoins       Number of silver coins of robot.
     * @param numberOfBrassCoins        Number of brass coins of robot.
     * @param numberOfCopperCoins       Number of copper coins of robot.
     */
    public RobotWithCoinTypesAndRefStateTwo(int x, int y, Direction direction, int numberOfSilverCoins, int numberOfBrassCoins, int numberOfCopperCoins) {
        super (x, y, direction, numberOfSilverCoins, numberOfBrassCoins, numberOfCopperCoins);
        refRobot = new ReferenceRobot(x, y, direction, numberOfSilverCoins + numberOfBrassCoins + numberOfCopperCoins);
    }

    @Override
    public void setCurrentStateAsReferenceState() {
        refRobot.setRefX(super.getX());
        refRobot.setRefY(super.getY());
        refRobot.setRefDirection(super.getDirection());
        refRobot.setRefNumberOfCoins(super.getNumberOfCoins());
    }

    @Override
    public int getDiffX() {
        return super.getX() - refRobot.getRefX();
    }

    @Override
    public int getDiffY() {
        return super.getY() - refRobot.getRefY();
    }

    @Override
    public Direction getDiffDirection() {
        int rotation = refRobot.getRefDirection().ordinal() - super.getDirection().ordinal();
        if (rotation<0) rotation+=4;
        if (rotation==0) return Direction.UP;
        else if (rotation==1) return Direction.LEFT;
        else if (rotation==2) return Direction.DOWN;
        else return Direction.RIGHT;
    }

    @Override
    public int getDiffNumberOfCoins() {
        return super.getNumberOfCoins() - refRobot.getRefNumberOfCoins();
    }

}
