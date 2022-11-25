package h04;

/**
 * Manages the get-/set-methods that are used for a specified coin type.
 */
public interface WithCoinTypes {

    /**
     * Sets number of coins of a specified type.
     *
     * @param cType     Specific type of coins that will be set.
     * @param amount    Amount of coins of a specified type that is set to.
     */
    void setNumberOfCoinsOfType(CoinType cType, int amount);

    /**
     * Gets number of coins of a specified type.
     *
     * @param cType     Specific type of coins of which its amount will be returned.
     * @return          Number of coins of a specified type.
     */
    int getNumberOfCoinsOfType(CoinType cType);

}
