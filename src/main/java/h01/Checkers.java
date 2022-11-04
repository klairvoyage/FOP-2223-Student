package h01;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.RobotFamily;
import fopbot.World;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.tudalgo.algoutils.student.Student;
import static org.tudalgo.algoutils.student.Student.crash;
import static org.tudalgo.algoutils.student.io.PropertyUtils.getIntProperty;

/**
 * {@link Checkers} is a simplified version of Checkers, implemented in FOPBot.
 */
public class Checkers {

    /**
     * The number of rows in the game board.
     */
    public static final int NUMBER_OF_ROWS = getIntProperty("checkers.properties", "NUMBER_OF_ROWS");

    /**
     * The number of columns in the game board.
     */
    public static final int NUMBER_OF_COLUMNS = getIntProperty("checkers.properties", "NUMBER_OF_COLUMNS");

    /**
     * The minimum initial number of coins for a black stone.
     */
    public static final int MIN_NUMBER_OF_COINS = getIntProperty("checkers.properties", "MIN_NUMBER_OF_COINS");

    /**
     * The maximum initial number of coins for a black stone.
     */
    public static final int MAX_NUMBER_OF_COINS = getIntProperty("checkers.properties", "MAX_NUMBER_OF_COINS");

    /**
     * The current state of the game.
     * At the start of the game, the state of the game is set to {@link GameState#RUNNING}.
     * After the game has finished, the state of the game is set to {@link GameState#BLACK_WIN} or {@link GameState#WHITE_WIN}.
     */
    private GameState gameState = GameState.RUNNING;


    /**
     * The robot of the white team.
     */
    private Robot whiteStone;

    /**
     * The robots of the black team.
     */
    private Robot blackStone0, blackStone1, blackStone2, blackStone3, blackStone4;

    /**
     * Runs the initialization of the game.
     * The initialization of the game consists of the initialization of the world and all stones.
     */
    public void initGame() {
        Student.setCrashEnabled(false);
        // initialize the world
        World.setSize(NUMBER_OF_COLUMNS, NUMBER_OF_ROWS);
        // initialize all stones
        initWhiteStone();
        initBlackStones();
    }

    /**
     * Runs the game. After the game has finished, the winner of the game will be printed to the console.
     */
    public void runGame() {
        World.setVisible(true);
        while (isRunning()) {
            doBlackTeamActions();
            doWhiteTeamActions();
            updateGameState();
        }
        System.out.printf("Final State: %s%n", gameState);
    }

    /**
     * Returns {@code true} if the game is running, {@code false} otherwise.
     *
     * @return if the game is running
     */
    public boolean isRunning() {
        return gameState == GameState.RUNNING;
    }

    /**
     * Returns random direction.
     */
    public Direction getRandomDirection() {
        int randomDirectionValue = getRandom().nextInt(4);
        if (randomDirectionValue==0) return Direction.UP;
        else if (randomDirectionValue==1) return Direction.RIGHT;
        else if (randomDirectionValue==2) return Direction.DOWN;
        else return Direction.LEFT;
    }

    /**
     * Runs the initialization of the white stone.
     */
    public void initWhiteStone() {
        // TODO: H1.1 - remove if implemented
        int x = getRandom().nextInt(NUMBER_OF_COLUMNS);
        int y;
        do {
            y = getRandom().nextInt(NUMBER_OF_ROWS);
        } while ((x+y)%2==0);
        Direction randomDirection = getRandomDirection();
        whiteStone = new Robot(x, y, randomDirection, 0, RobotFamily.SQUARE_WHITE);
    }

    /**
     * Runs the initialization of all black stones.
     */
    public void initBlackStones() {
        // TODO: H1.2 - remove if implemented
        Robot[] blackStone = {blackStone0, blackStone1, blackStone2, blackStone3, blackStone4};
        for (int i=0;i<5;i++) {
            int x;
            int y;
            do {
                x = getRandom().nextInt(NUMBER_OF_COLUMNS);
                y = getRandom().nextInt(NUMBER_OF_ROWS);
            } while ((x+y)%2==0 || (x==whiteStone.getX() && y==whiteStone.getY()));
            Direction randomDirection = getRandomDirection();
            int coinNumber = getRandom().nextInt(MIN_NUMBER_OF_COINS, MAX_NUMBER_OF_COINS+1);
            blackStone[i] = new Robot(x, y, randomDirection, coinNumber, RobotFamily.SQUARE_BLACK);
        }
        blackStone0=blackStone[0];
        blackStone1=blackStone[1];
        blackStone2=blackStone[2];
        blackStone3=blackStone[3];
        blackStone4=blackStone[4];
    }

    /**
     * Moves stone 1 up, 1 right (robot's pov).
     */
    public void moveUpRight(Robot chosenStone) {
        chosenStone.turnLeft();
        chosenStone.turnLeft();
        chosenStone.turnLeft();
        moveUpLeft(chosenStone);
    }

    /**
     * Moves stone 1 up, 1 left (robot's pov).
     */
    public void moveUpLeft(Robot chosenStone) {
        chosenStone.move();
        chosenStone.turnLeft();
        chosenStone.move();
    }

    /**
     * Moves stone 1 down, 1 left (robot's pov).
     */
    public void moveDownLeft(Robot chosenStone) {
        chosenStone.turnLeft();
        moveUpLeft(chosenStone);
    }

    /**
     * Moves stone 1 down, 1 right (robot's pov).
     */
    public void moveDownRight(Robot chosenStone) {
        chosenStone.turnLeft();
        chosenStone.turnLeft();
        moveUpLeft(chosenStone);
    }

    /**
     * Returns true if the field up right (base perspective) is within the world and not already taken by the white stone.
     */
    public boolean checkUpRightFree(Robot chosenStone) {
        int x = chosenStone.getX()+1;
        int y = chosenStone.getY()+1;
        if (x<NUMBER_OF_COLUMNS && y<NUMBER_OF_ROWS) return x!=whiteStone.getX() || y!=whiteStone.getY();
        return false;
    }

    /**
     * Returns true if the field up left (base perspective) is within the world and not already taken by the white stone.
     */
    public boolean checkUpLeftFree(Robot chosenStone) {
        int x = chosenStone.getX()-1;
        int y = chosenStone.getY()+1;
        if (0<=x && y<NUMBER_OF_ROWS) return x!=whiteStone.getX() || y!=whiteStone.getY();
        return false;
    }

    /**
     * Returns true if the field down left (base perspective) is within the world and not already taken by the white stone.
     */
    public boolean checkDownLeftFree(Robot chosenStone) {
        int x = chosenStone.getX()-1;
        int y = chosenStone.getY()-1;
        if (0<=x && 0<=y) return x!=whiteStone.getX() || y!=whiteStone.getY();
        return false;
    }

    /**
     * Returns true if the field down right (base perspective) is within the world and not already taken by the white stone.
     */
    public boolean checkDownRightFree(Robot chosenStone) {
        int x = chosenStone.getX()+1;
        int y = chosenStone.getY()-1;
        if (x<NUMBER_OF_COLUMNS && 0<=y) return x!=whiteStone.getX() || y!=whiteStone.getY();
        return false;
    }

    /**
     * Moves chosen & up looking black stone to first free field.
     */
    public void moveUpLooking(Robot chosenStone) {
        if (checkUpRightFree(chosenStone)) moveUpRight(chosenStone);
        else if (checkUpLeftFree(chosenStone)) moveUpLeft(chosenStone);
        else if (checkDownLeftFree(chosenStone)) moveDownLeft(chosenStone);
        else moveDownRight(chosenStone);
    }

    /**
     * Moves chosen & right looking black stone to first free field.
     */
    public void moveRightLooking(Robot chosenStone) {
        if (checkDownRightFree(chosenStone)) moveUpRight(chosenStone);
        else if (checkUpRightFree(chosenStone)) moveUpLeft(chosenStone);
        else if (checkUpLeftFree(chosenStone)) moveDownLeft(chosenStone);
        else moveDownRight(chosenStone);
    }

    /**
     * Moves chosen & down looking black stone to first free field.
     */
    public void moveDownLooking(Robot chosenStone) {
        if (checkDownLeftFree(chosenStone)) moveUpRight(chosenStone);
        else if (checkDownRightFree(chosenStone)) moveUpLeft(chosenStone);
        else if (checkUpRightFree(chosenStone)) moveDownLeft(chosenStone);
        else moveDownRight(chosenStone);
    }

    /**
     * Moves chosen & left looking black stone to first free field.
     */
    public void moveLeftLooking(Robot chosenStone) {
        if (checkUpLeftFree(chosenStone)) moveUpRight(chosenStone);
        else if (checkDownLeftFree(chosenStone)) moveUpLeft(chosenStone);
        else if (checkDownRightFree(chosenStone)) moveDownLeft(chosenStone);
        else moveDownRight(chosenStone);
    }

    /**
     * Runs the action of the black team.
     */
    public void doBlackTeamActions() {
        // TODO: H2.1 - remove if implemented
        Robot[] blackStone = {blackStone0, blackStone1, blackStone2, blackStone3, blackStone4};
        int i;
        Robot chosenStone;
        do {
            i = getRandom().nextInt(5);
            chosenStone = blackStone[i];
        } while (!chosenStone.hasAnyCoins() || !chosenStone.isTurnedOn());
        chosenStone.putCoin();
        if (chosenStone.isFacingUp()) moveUpLooking(chosenStone);
        else if (chosenStone.isFacingRight()) moveRightLooking(chosenStone);
        else if (chosenStone.isFacingDown()) moveDownLooking(chosenStone);
        else moveLeftLooking(chosenStone);
    }

    /**
     * Moves white stone along the up right diagonal if there's a possible move.
     */
    public boolean checkUpRightPath(int x, int y, Robot[] blackStone, Robot aboutToBeBeaten) {
        int n = 0;
        for (int i=0;i<5;i++) {
            if ((x-1)==blackStone[i].getX() && (y-1)==blackStone[i].getY() && blackStone[i].isTurnedOn()) {
                n++;
                aboutToBeBeaten = blackStone[i];
            }
        }
        if (n!=0) {
            boolean noBlockingStone = true;
            int checkX = whiteStone.getX() + 1;
            int checkY = whiteStone.getY() + 1;
            while (checkX<(x-1) && checkY<(y-1)) {
                for (int i=0;i<5;i++) {
                    if (checkX==blackStone[i].getX() && checkY==blackStone[i].getY() && blackStone[i].isTurnedOn()) {
                        noBlockingStone = false;
                    }
                }
                checkX++;
                checkY++;
            }
            if (noBlockingStone) {
                whiteStone.setX(x);
                whiteStone.setY(y);
                aboutToBeBeaten.turnOff();
                return true;
            }
        }
        return false;
    }

    /**
     * Moves white stone along the up left diagonal if there's a possible move.
     */
    public boolean checkUpLeftPath(int x, int y, Robot[] blackStone, Robot aboutToBeBeaten) {
        int n = 0;
        for (int i=0;i<5;i++) {
            if ((x+1)==blackStone[i].getX() && (y-1)==blackStone[i].getY() && blackStone[i].isTurnedOn()) {
                n++;
                aboutToBeBeaten = blackStone[i];
            }
        }
        if (n!=0) {
            boolean noBlockingStone = true;
            int checkX = whiteStone.getX() - 1;
            int checkY = whiteStone.getY() + 1;
            while (checkX>(x+1) && checkY<(y-1)) {
                for (int i=0;i<5;i++) {
                    if (checkX == blackStone[i].getX() && checkY == blackStone[i].getY() && blackStone[i].isTurnedOn()) {
                        noBlockingStone = false;
                    }
                }
                checkX--;
                checkY++;
            }
            if (noBlockingStone) {
                whiteStone.setX(x);
                whiteStone.setY(y);
                aboutToBeBeaten.turnOff();
                return true;
            }
        }
        return false;
    }

    /**
     * Moves white stone along the down left diagonal if there's a possible move.
     */
    public boolean checkDownLeftPath(int x, int y, Robot[] blackStone, Robot aboutToBeBeaten) {
        int n = 0;
        for (int i=0;i<5;i++) {
            if ((x+1)==blackStone[i].getX() && (y+1)==blackStone[i].getY() && blackStone[i].isTurnedOn()) {
                n++;
                aboutToBeBeaten = blackStone[i];
            }
        }
        if (n!=0) {
            boolean noBlockingStone = true;
            int checkX = whiteStone.getX() - 1;
            int checkY = whiteStone.getY() - 1;
            while (checkX>(x+1) && checkY>(y+1)) {
                for (int i=0;i<5;i++) {
                    if (checkX == blackStone[i].getX() && checkY == blackStone[i].getY() && blackStone[i].isTurnedOn()) {
                        noBlockingStone = false;
                    }
                }
                checkX--;
                checkY--;
            }
            if (noBlockingStone) {
                whiteStone.setX(x);
                whiteStone.setY(y);
                aboutToBeBeaten.turnOff();
                return true;
            }
        }
        return false;
    }

    /**
     * Moves white stone along the down right diagonal if there's a possible move.
     */
    public boolean checkDownRightPath(int x, int y, Robot[] blackStone, Robot aboutToBeBeaten) {
        int n = 0;
        for (int i=0;i<5;i++) {
            if ((x-1)==blackStone[i].getX() && (y+1)==blackStone[i].getY() && blackStone[i].isTurnedOn()) {
                n++;
                aboutToBeBeaten = blackStone[i];
            }
        }
        if (n!=0) {
            boolean noBlockingStone = true;
            int checkX = whiteStone.getX() + 1;
            int checkY = whiteStone.getY() - 1;
            while (checkX<(x-1) && checkY>(y+1)) {
                for (int i=0;i<5;i++) {
                    if (checkX == blackStone[i].getX() && checkY == blackStone[i].getY() && blackStone[i].isTurnedOn()) {
                        noBlockingStone = false;
                    }
                }
                checkX++;
                checkY--;
            }
            if (noBlockingStone) {
                whiteStone.setX(x);
                whiteStone.setY(y);
                aboutToBeBeaten.turnOff();
                return true;
            }
        }
        return false;
    }

    /**
     * Runs the action of the white team.
     */
    public void doWhiteTeamActions() {
        // TODO: H2.2 - remove if implemented
        Robot[] blackStone = {blackStone0, blackStone1, blackStone2, blackStone3, blackStone4};
        int whiteX = whiteStone.getX();
        int whiteY = whiteStone.getY();
        int upDistance = NUMBER_OF_ROWS - 1 - whiteY;
        int rightDistance = NUMBER_OF_COLUMNS - 1 - whiteX;
        //downDistance entspricht whiteY
        //leftDistance entspricht whiteX
        boolean alreadyMoved = false;
        int maxSchlagweg;
        int counter;
        Robot aboutToBeBeaten = blackStone0;
        //up right
        if (upDistance<rightDistance) maxSchlagweg = upDistance;
        else maxSchlagweg = rightDistance;
        if (maxSchlagweg>1) {
            for (int i=2;i<=maxSchlagweg;i++) {
                counter = 0;
                for (int n=0;n<5;n++) {
                    if (whiteX+i!=blackStone[n].getX() || whiteY+i!=blackStone[n].getY() || blackStone[n].isTurnedOff()) counter++;
                }
                if (counter==5) alreadyMoved = checkUpRightPath(whiteX+i, whiteY+i, blackStone, aboutToBeBeaten);
                if (alreadyMoved) break;
            }
        }
        //up left
        if (!alreadyMoved) {
            if (upDistance<whiteX) maxSchlagweg = upDistance;
            else maxSchlagweg = whiteX;
            if (maxSchlagweg>1) {
                for (int i=2;i<=maxSchlagweg;i++) {
                    counter = 0;
                    for (int n=0;n<5;n++) {
                        if (whiteX-i!=blackStone[n].getX() || whiteY+i!=blackStone[n].getY() || blackStone[n].isTurnedOff()) counter++;
                    }
                    if (counter==5) alreadyMoved = checkUpLeftPath(whiteX-i, whiteY+i, blackStone, aboutToBeBeaten);
                    if (alreadyMoved) break;
                }
            }
        }
        //down left
        if (!alreadyMoved) {
            if (whiteY<whiteX) maxSchlagweg = whiteY;
            else maxSchlagweg = whiteX;
            if (maxSchlagweg>1) {
                for (int i=2;i<=maxSchlagweg;i++) {
                    counter = 0;
                    for (int n=0;n<5;n++) {
                        if (whiteX-i!=blackStone[n].getX() || whiteY-i!=blackStone[n].getY() || blackStone[n].isTurnedOff()) counter++;
                    }
                    if (counter==5) alreadyMoved = checkDownLeftPath(whiteX-i, whiteY-i, blackStone, aboutToBeBeaten);
                    if (alreadyMoved) break;
                }
            }
        }
        //down right
        if (!alreadyMoved) {
            if (whiteY<rightDistance) maxSchlagweg = whiteY;
            else maxSchlagweg = rightDistance;
            if (maxSchlagweg>1) {
                for (int i=2;i<=maxSchlagweg;i++) {
                    counter = 0;
                    for (int n=0;n<5;n++) {
                        if (whiteX+i!=blackStone[n].getX() || whiteY-i!=blackStone[n].getY() || blackStone[n].isTurnedOff()) counter++;
                    }
                    if (counter==5) alreadyMoved = checkDownRightPath(whiteX+i, whiteY-i, blackStone, aboutToBeBeaten);
                    if (alreadyMoved) break;
                }
            }
        }
    }

    /**
     * Checks if a team has won the game and, if so, updates the game state to {@link GameState#BLACK_WIN} or {@link GameState#WHITE_WIN}.
     */
    public void updateGameState() {
        // TODO: H2.3 - remove if implemented
        if (blackStone0.isTurnedOff() && blackStone1.isTurnedOff() && blackStone2.isTurnedOff() && blackStone3.isTurnedOff() && blackStone4.isTurnedOff()) gameState = GameState.WHITE_WIN;
        if (blackStone0.isTurnedOn() || blackStone1.isTurnedOn() || blackStone2.isTurnedOn() || blackStone3.isTurnedOn() || blackStone4.isTurnedOn()) {
            Robot[] blackStone = {blackStone0, blackStone1, blackStone2, blackStone3, blackStone4};
            int turnedOnStones = 0;
            int brokeTurnedOnStones = 0;
            for (int i=0;i<5;i++) {
                if (blackStone[i].isTurnedOn()) {
                    turnedOnStones++;
                    if (!blackStone[i].hasAnyCoins()) brokeTurnedOnStones++;
                }
            }
            if (turnedOnStones==brokeTurnedOnStones) gameState = GameState.BLACK_WIN;
        }
    }

    /**
     * Returns an instance of {@link Random}.
     *
     * @return an instance of {@link Random}
     */
    private Random getRandom() {
        return ThreadLocalRandom.current();
    }
}
