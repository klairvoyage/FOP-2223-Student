package h04.student;

import fopbot.Direction;

public record RobotWithCoinTypesData(
    int x,
    int y,
    Direction direction,
    int numberOfSilverCoins,
    int numberOfBrassCoins,
    int numberOfCopperCoins
) {
}
