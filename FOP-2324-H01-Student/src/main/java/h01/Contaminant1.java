package h01;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.RobotFamily;
import h01.template.Contaminant;
import h01.template.GameConstants;
import h01.template.TickBased;
import h01.template.Utils;
import org.tudalgo.algoutils.student.Student;

import java.util.ArrayList;

/**
 * A {@link Contaminant}-{@link Robot} that moves randomly and contaminates the floor.
 */
public class Contaminant1 extends Robot implements Contaminant, TickBased {

    /**
     * Creates a new {@link Contaminant1}.
     *
     * @param x             the initial x coordinate of the robot
     * @param y             the initial y coordinate of the robot
     * @param direction     the initial direction of the robot
     * @param numberOfCoins the initial number of coins of the robot
     */
    public Contaminant1(final int x, final int y, final Direction direction, final int numberOfCoins) {
        super(x, y, direction, numberOfCoins, RobotFamily.SQUARE_ORANGE);
    }

    @Override
    public int getUpdateDelay() {
        return 10;
    }

    @Override
    public void doMove() {
        // TODO: H2.1
        if (!hasAnyCoins()) turnOff();
        else if (!isTurnedOff()) {
            // puts a random amount of coins (as many as it can) if current field has less than 20
            for (int i=0; i<Utils.getRandomInteger(GameConstants.CONTAMINANT_ONE_MIN_PUT_COINS,
                GameConstants.CONTAMINANT_ONE_MAX_PUT_COINS); i++) {
                if (Utils.getCoinAmount(getX(), getY())<20 && hasAnyCoins()) putCoin();
            }
            // collects all viable direction options in a list during a 360-turn
            ArrayList<Integer> potentialDirections = new ArrayList<>();
            for (int i=0; i<4; i++) {
                if (isFrontClear()) potentialDirections.add(getDirection().ordinal());
                turnLeft();
            }
            if (!potentialDirections.isEmpty()) {
                // chooses a random direction from the list of viable options
                int nextDirection;
                do nextDirection = Utils.getRandomInteger(0, 3);
                while (!potentialDirections.contains(nextDirection));
                // turns to the chosen direction
                while (getDirection().ordinal()!=nextDirection) turnLeft();
            }
            if (isFrontClear()) move();
        }
    }
}
