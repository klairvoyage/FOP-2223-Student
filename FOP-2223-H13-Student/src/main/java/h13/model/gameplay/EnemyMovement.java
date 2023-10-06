package h13.model.gameplay;

import h13.model.gameplay.sprites.Enemy;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

import java.util.Set;

import static h13.controller.GameConstants.*;
import static org.tudalgo.algoutils.student.Student.crash;

/**
 * The {@link EnemyMovement} class is responsible for moving the {@linkplain h13.model.gameplay.sprites.Enemy enemies} in a grid.
 */
public class EnemyMovement implements Updatable {

    // --Variables-- //

    /**
     * The current movement direction
     */
    private Direction direction;

    /**
     * The current movement speed
     */
    private double velocity = INITIAL_ENEMY_MOVEMENT_VELOCITY;

    /**
     * The Next y-coordinate to reach
     */
    private double yTarget = 0;

    /**
     * The current {@link GameState}
     */
    private final GameState gameState;

    // --Constructors-- //

    /**
     * Creates a new EnemyMovement.
     *
     * @param gameState The enemy controller.
     */
    public EnemyMovement(final GameState gameState) {
        this.gameState = gameState;
        nextRound();
    }

    // --Getters and Setters-- //

    /**
     * Gets the current {@link #velocity}.
     *
     * @return The current {@link #velocity}.
     * @see #velocity
     */
    public double getVelocity() {
        return velocity;
    }

    /**
     * Sets the current {@link #velocity} to the given value.
     *
     * @param velocity The new {@link #velocity}.
     * @see #velocity
     */
    public void setVelocity(final double velocity) {
        this.velocity = velocity;
    }

    /**
     * Gets the current {@link #direction}.
     *
     * @return The current {@link #direction}.
     * @see #direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the current {@link #direction} to the given value.
     *
     * @param direction The new {@link #direction}.
     * @see #direction
     */
    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    /**
     * Checks whether the bottom was reached.
     *
     * @return {@code true} if the bottom was reached, {@code false} otherwise.
     */
    public boolean bottomWasReached() {
        // TODO: H1.6 - remove if implemented
        return getEnemyBounds().getMaxY()>=ORIGINAL_GAME_BOUNDS.getMaxY();
    }

    /**
     * Gets the enemy controller.
     *
     * @return The enemy controller.
     */
    public GameState getGameState() {
        return gameState;
    }

    // --Utility Methods-- //

    /**
     * Creates a BoundingBox around all alive enemies.
     *
     * @return The BoundingBox.
     */
    public Bounds getEnemyBounds() {
        // TODO: H1.6 - remove if implemented
        Set<Enemy> enemySet = gameState.getAliveEnemies();
        Bounds currentBound;
        double minX = ORIGINAL_GAME_BOUNDS.getMinX();
        double minY = ORIGINAL_GAME_BOUNDS.getMinY();
        double maxX = minX;
        double maxY = minY;
        for (Enemy enemy : enemySet) {
            currentBound = enemy.getBounds();
            if (currentBound.getMinX()<minX) minX = currentBound.getMinX();
            if (currentBound.getMinY()<minY) minY = currentBound.getMinY();
            if (currentBound.getMaxX()>maxX) maxX = currentBound.getMaxX();
            if (currentBound.getMaxY()>maxY) maxY = currentBound.getMaxY();
        }
        return new BoundingBox(minX, minY, maxX-minX, maxY-minY);
    }

    /**
     * Checks whether the target Position of the current movement iteration is reached.
     *
     * @param enemyBounds The BoundingBox of all alive enemies.
     * @return {@code true} if the target Position of the current movement iteration is reached, {@code false} otherwise.
     */
    public boolean targetReached(final Bounds enemyBounds) {
        // TODO: H1.6 - remove if implemented
        if ((enemyBounds.getMaxX()<ORIGINAL_GAME_BOUNDS.getMaxX() && getDirection()==Direction.RIGHT) ||
            (enemyBounds.getMinX()>ORIGINAL_GAME_BOUNDS.getMinX() && getDirection() == Direction.LEFT)) return false;
        return enemyBounds.getMinY()>=yTarget;
    }

    // --Movement-- //

    @Override
    public void update(final double elapsedTime) {
        crash(); // TODO: H1.6 - remove if implemented
    }

    /**
     * Updates the positions of all alive enemies.
     *
     * @param deltaX The deltaX.
     * @param deltaY The deltaY.
     */
    public void updatePositions(final double deltaX, final double deltaY) {
        crash(); // TODO: H1.6 - remove if implemented
    }

    /**
     * Starts the next movement iteration.
     *
     * @param enemyBounds The BoundingBox of all alive enemies.
     */
    public void nextMovement(final Bounds enemyBounds) {
        // TODO: H1.6 - remove if implemented
        if (enemyBounds.getMaxX()>=ORIGINAL_GAME_BOUNDS.getMaxX()) {
            if (getDirection()==Direction.RIGHT) setDirection(Direction.DOWN);
            else if (getDirection()==Direction.DOWN) setDirection(Direction.LEFT);
        } else if (enemyBounds.getMinX()<=ORIGINAL_GAME_BOUNDS.getMinX()) {
            if (getDirection()==Direction.LEFT) setDirection(Direction.DOWN);
            else if (getDirection()==Direction.DOWN) setDirection(Direction.RIGHT);
        }
        if (getDirection().isVertical()) yTarget += VERTICAL_ENEMY_MOVE_DISTANCE;
        velocity += ENEMY_MOVEMENT_SPEED_INCREASE;
    }

    /**
     * Prepares the next round of enemies.
     * Uses {@link h13.controller.GameConstants#INITIAL_ENEMY_MOVEMENT_DIRECTION} and {@link h13.controller.GameConstants#INITIAL_ENEMY_MOVEMENT_VELOCITY} to set the initial values.
     */
    public void nextRound() {
        direction = INITIAL_ENEMY_MOVEMENT_DIRECTION;
        yTarget = HUD_HEIGHT;
    }
}
