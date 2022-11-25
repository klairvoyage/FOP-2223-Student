package h04;

import fopbot.Direction;
import fopbot.Robot;

/**
 * Robots with different coin types.
 */
public class RobotWithCoinTypes extends Robot implements WithCoinTypes {

    private int numberOfSilverCoins;

    private int numberOfBrassCoins;

    private int numberOfCopperCoins;

    /**
     * Constructor invokes constructor of its parent class
     * with its parameters & initializes its attributes.
     *
     * @param x                         x-position of robot.
     * @param y                         y-position of robot.
     * @param direction                 Direction of robot.
     * @param numberOfSilverCoins       Number of silver coins of robot.
     * @param numberOfBrassCoins        Number of brass coins of robot.
     * @param numberOfCopperCoins       Number of copper coins of robot.
     */
    public RobotWithCoinTypes(int x, int y, Direction direction, int numberOfSilverCoins, int numberOfBrassCoins, int numberOfCopperCoins) {
        super(x, y, direction, numberOfSilverCoins + numberOfBrassCoins + numberOfCopperCoins);
        this.numberOfSilverCoins = numberOfSilverCoins;
        this.numberOfBrassCoins = numberOfBrassCoins;
        this.numberOfCopperCoins = numberOfCopperCoins;
    }

    @Override
    public void setNumberOfCoins(int coins) {
        if (coins<0) super.setNumberOfCoins(coins);
        else {
            numberOfCopperCoins = coins;
            super.setNumberOfCoins(coins);
        }
    }

    @Override
    public void setNumberOfCoinsOfType(CoinType cType, int amount) {
        if (amount<0) setNumberOfCoins(amount);
        else {
            if (cType == CoinType.SILVER) numberOfSilverCoins = amount;
            if (cType == CoinType.BRASS) numberOfBrassCoins = amount;
            if (cType == CoinType.COPPER) numberOfCopperCoins = amount;
        }
    }

    @Override
    public int getNumberOfCoinsOfType(CoinType cType) {
        if (cType == CoinType.SILVER) return numberOfSilverCoins;
        if (cType == CoinType.BRASS) return numberOfBrassCoins;
        if (cType == CoinType.COPPER) return numberOfCopperCoins;
        return 0;
    }

    @Override
    public void putCoin() {
        if (numberOfCopperCoins>0) numberOfCopperCoins--;
        else if (numberOfBrassCoins>0) numberOfBrassCoins--;
        else if (numberOfSilverCoins>0) numberOfSilverCoins--;
        super.putCoin();
    }

    @Override
    public void pickCoin() {
        numberOfCopperCoins++;
        super.pickCoin();
    }

}
