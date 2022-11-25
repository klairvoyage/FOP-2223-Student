package h04;

/**
 * Manages the amount of the different types of coins.
 */
public class CoinCollection implements WithCoinTypes {

    private int numberOfSilverCoins;

    private int numberOfBrassCoins;

    private int numberOfCopperCoins;

    /**
     * Constructor initializes attributes with its given parameters.
     *
     * @param numberOfSilverCoins       Number of silver coins.
     * @param numberOfBrassCoins        Number of brass coins.
     * @param numberOfCopperCoins       Number of copper coins.
     */
    public CoinCollection(int numberOfSilverCoins, int numberOfBrassCoins, int numberOfCopperCoins) {
        this.numberOfSilverCoins = numberOfSilverCoins;
        this.numberOfBrassCoins = numberOfBrassCoins;
        this.numberOfCopperCoins = numberOfCopperCoins;
    }

    @Override
    public int getNumberOfCoinsOfType(CoinType cType) {
        if (cType == CoinType.SILVER) return numberOfSilverCoins;
        if (cType == CoinType.BRASS) return numberOfBrassCoins;
        if (cType == CoinType.COPPER) return numberOfCopperCoins;
        return 0;
    }

    @Override
    public void setNumberOfCoinsOfType(CoinType cType, int amount) {
        if (cType == CoinType.SILVER) {
            if (amount<0) numberOfSilverCoins = 0;
            else numberOfSilverCoins = amount;
        }
        if (cType == CoinType.BRASS) {
            if (amount<0) numberOfBrassCoins = 0;
            else numberOfBrassCoins = amount;
        }
        if (cType == CoinType.COPPER) {
            if (amount<0) numberOfCopperCoins = 0;
            else numberOfCopperCoins = amount;
        }
    }

    /**
     * Gets number of silver coins.
     *
     * @return      Number of silver coins.
     */
    public int getNumberOfSilverCoins() { return numberOfSilverCoins; }

    /**
     * Gets number of brass coins.
     *
     * @return      Number of brass coins.
     */
    public int getNumberOfBrassCoins() { return numberOfBrassCoins; }

    /**
     * Gets number of copper coins.
     *
     * @return      Number of copper coins.
     */
    public int getNumberOfCopperCoins() { return numberOfCopperCoins; }

    /**
     * Inserts coin of a specified type.
     *
     * @param cType     Type of coin that is inserted.
     */
    public void insertCoin(CoinType cType) {
        if (cType == CoinType.SILVER) numberOfSilverCoins++;
        if (cType == CoinType.BRASS) numberOfBrassCoins++;
        if (cType == CoinType.COPPER) numberOfCopperCoins++;
    }

    /**
     * Removes coin of a specified type.
     *
     * @param cType     Type of coin that is removed.
     */
    public void removeCoin(CoinType cType) {
        if (cType == CoinType.SILVER && numberOfSilverCoins>0) numberOfSilverCoins--;
        if (cType == CoinType.BRASS && numberOfBrassCoins>0) numberOfBrassCoins--;
        if (cType == CoinType.COPPER && numberOfCopperCoins>0) numberOfCopperCoins--;
    }

}
