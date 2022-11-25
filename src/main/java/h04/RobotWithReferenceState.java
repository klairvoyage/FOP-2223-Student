package h04;

import fopbot.Direction;

/**
 * Manages a roboter's reference state.
 */
public interface RobotWithReferenceState {

    /**
     * Sets current state of Robot as reference state.
     */
    void setCurrentStateAsReferenceState();

    /**
     * Provides relative difference to reference state's x.
     *
     * @return      Relative difference between the x.
     */
    int getDiffX();

    /**
     * Provides relative difference to reference state's y.
     *
     * @return       Relative difference between the y.
     */
    int getDiffY();

    /**
     * Provides relative directional difference to reference state's.
     *
     * @return      Relative directional difference.
     */
    Direction getDiffDirection();

    /**
     * Provides relative difference of the number of coins to reference state's.
     *
     * @return      Relative difference of number of coins.
     */
    int getDiffNumberOfCoins();

}
