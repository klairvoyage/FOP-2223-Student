package h01;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.RobotFamily;
import h01.template.Contaminant;
import h01.template.TickBased;
import h01.template.Utils;
import org.tudalgo.algoutils.student.Student;

/**
 * A {@link Contaminant}-{@link Robot} that moves in a predefined way and contaminates the floor.
 */
public class Contaminant2 extends Robot implements Contaminant, TickBased {

    /**
     * Creates a new {@link Contaminant2}.
     *
     * @param x             the initial x coordinate of the robot
     * @param y             the initial y coordinate of the robot
     * @param direction     the initial direction of the robot
     * @param numberOfCoins the initial number of coins of the robot
     */
    public Contaminant2(final int x, final int y, final Direction direction, final int numberOfCoins) {
        super(x, y, direction, numberOfCoins, RobotFamily.SQUARE_AQUA);
    }

    @Override
    public int getUpdateDelay() {
        return 8;
    }

    @Override
    public void doMove() {
        // TODO: H2.2
        if (!hasAnyCoins()) turnOff();
        else if (!isTurnedOff()) {
            // fills current field (if possible) up to 2 coins
            while (Utils.getCoinAmount(getX(), getY())<2 && hasAnyCoins()) putCoin();
            // collects needed amount of turns in an array for all viable direction
            // options; starting from its left and then in clockwise order
            Integer[] allAmountOfTurnsInOrder = new Integer[4];
            for (int i=0; i<4; i++) {
                if (isFrontClear()) {
                    if (i%2==0) allAmountOfTurnsInOrder[i+1] = i;
                    else allAmountOfTurnsInOrder[i-1] = i;
                }
                turnLeft();
            }
            // turns to the first direction in the array that is a viable option
            for (Integer amountOfTurns : allAmountOfTurnsInOrder) {
                if (amountOfTurns != null) {
                    for (int i=0; i<amountOfTurns; i++) turnLeft();
                    break;
                }
            }
            if (isFrontClear()) move();
        }
    }
}
