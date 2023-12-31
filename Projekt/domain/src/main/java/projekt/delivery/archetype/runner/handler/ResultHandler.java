package projekt.delivery.archetype.runner.handler;

import projekt.delivery.rating.RatingCriteria;
import projekt.delivery.archetype.runner.Runner;

import java.util.Map;

/**
 * An interface for handling the result of a {@link Runner}.
 */
public interface ResultHandler {

    /**
     * Handles the given result of a finished {@link Runner}.
     *
     * @param result The result of the {@link Runner}.
     */
    void accept(Map<RatingCriteria, Double> result);
}
