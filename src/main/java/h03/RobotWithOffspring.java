package h03;

import fopbot.Direction;
import fopbot.Robot;

/**
 * {@link RobotWithOffspring} is a derived class from {@link Robot}.
 */
public class RobotWithOffspring extends Robot {

    /**
     * Number of columns in the world.
     */
    private final int numberOfColumnsOfWorld;

    /**
     * Number of rows in the world.
     */
    private final int numberOfRowsOfWorld;

    /**
     * offspring is an instance of {@link Robot}.
     */
    protected Robot offspring;

    /**
     * Constructor creates {@link RobotWithOffspring}-object by invoking
     * the constructor of its parent class {@link Robot} & also
     * initializes the dimensions of the world.
     *
     * @param numberOfColumnsOfWorld    Number of columns in the world.
     * @param numberOfRowsOfWorld       Number of rows in the world.
     * @param direction                 Direction of {@link RobotWithOffspring}.
     * @param numberOfCoins             Number of coins of {@link RobotWithOffspring}.
     */
    public RobotWithOffspring(int numberOfColumnsOfWorld, int numberOfRowsOfWorld, Direction direction, int numberOfCoins) {
        super(numberOfColumnsOfWorld/2, numberOfRowsOfWorld/2, direction, numberOfCoins);
        this.numberOfColumnsOfWorld = numberOfColumnsOfWorld;
        this.numberOfRowsOfWorld = numberOfRowsOfWorld;
    }

    /**
     * Initializes {@link RobotWithOffspring#offspring} with the same position
     * as the {@link RobotWithOffspring}-object & the 2 given parameters.
     *
     * @param direction         Direction of {@link RobotWithOffspring#offspring}.
     * @param numberOfCoins     Number of coins of {@link RobotWithOffspring#offspring}.
     */
    public void initOffspring(Direction direction, int numberOfCoins) {
        offspring = new Robot(this.getX(), this.getY(), direction, numberOfCoins);
    }

    /**
     * Returns x-coordinate of {@link RobotWithOffspring#offspring}.
     *
     * @return      x-coordinate of {@link RobotWithOffspring#offspring}.
     */
    public int getXOfOffspring() { return offspring.getX(); }

    /**
     * Returns y-coordinate of {@link RobotWithOffspring#offspring}.
     *
     * @return      y-coordinate of {@link RobotWithOffspring#offspring}.
     */
    public int getYOfOffspring() { return offspring.getY(); }

    /**
     * Returns direction of {@link RobotWithOffspring#offspring}.
     *
     * @return      Direction of {@link RobotWithOffspring#offspring}.
     */
    public Direction getDirectionOfOffspring() { return offspring.getDirection(); }

    /**
     * Returns number of coins of {@link RobotWithOffspring#offspring}.
     *
     * @return      Number of coins of {@link RobotWithOffspring#offspring}.
     */
    public int getNumberOfCoinsOfOffspring() { return offspring.getNumberOfCoins(); }

    /**
     * Returns true if {@link RobotWithOffspring#offspring} is
     * not null, returns false otherwise.
     *
     * @return      true if {@link RobotWithOffspring#offspring} is initialized.
     */
    public boolean offspringIsInitialized() { return offspring!=null; }

    /**
     * Moves {@link RobotWithOffspring#offspring}, if initialized,
     * along x-axis as far as the world allows it.
     *
     * @param deltaX    Value by which {@link RobotWithOffspring#offspring} is horizontally shifted.
     */
    public void addToXOfOffspring(int deltaX) {
        if (offspringIsInitialized()) {
            int n = getXOfOffspring() + deltaX;
            if (n>=0 && n<numberOfColumnsOfWorld) offspring.setX(n);
            if (n<0) offspring.setX(0);
            if (n>=numberOfColumnsOfWorld) offspring.setX(numberOfColumnsOfWorld-1);
        }
    }

    /**
     * Moves {@link RobotWithOffspring#offspring}, if initialized,
     * along y-axis as far as the world allows it.
     *
     * @param deltaY     Value by which {@link RobotWithOffspring#offspring} is vertically shifted.
     */
    public void addToYOfOffspring(int deltaY) {
        if (offspringIsInitialized()) {
            int n = getYOfOffspring() + deltaY;
            if (n>=0 && n<numberOfRowsOfWorld) offspring.setY(n);
            if (n<0) offspring.setY(0);
            if (n>=numberOfRowsOfWorld) offspring.setY(numberOfRowsOfWorld-1);
        }
    }

    /**
     * Rotates {@link RobotWithOffspring#offspring}, if initialized, clockwise
     * according to the value of the parameter (using modular arithmetic).
     *
     * @param deltaDirectionValue   Value by which {@link RobotWithOffspring#offspring} is rotated.
     */
    public void addToDirectionOfOffspring(int deltaDirectionValue) {
        if (offspringIsInitialized()) {
            int n = deltaDirectionValue%4;
            if (n<0) n += 4;
            if (n!=0) for (int i=4;i>n;i--) offspring.turnLeft();
        }
    }

    /**
     * Changes the amount of coins {@link RobotWithOffspring#offspring} carries
     * if it is initialized (within the realms of possibility).
     *
     * @param deltaNumberOfCoins    The amount of coins that is added/subtracted.
     */
    public void addToNumberOfCoinsOfOffspring(int deltaNumberOfCoins) {
        if (offspringIsInitialized()) {
            int n = getNumberOfCoinsOfOffspring() + deltaNumberOfCoins;
            if (n>0) offspring.setNumberOfCoins(n);
            else offspring.setNumberOfCoins(0);
        }
    }

}
