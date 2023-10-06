package h05;

/**
 * Manages methods used for fuel-based vehicles.
 */
public interface FuelDriven {

    /**
     * Provides fuel type of the vehicle.
     *
     * @return      Type of fuel the vehicle uses.
     */
     FuelType getFuelType();

    /**
     * Provides average fuel consumption of the vehicle (at a certain speed).
     *
     * @param speed     Speed of which we want to know its fuel consumption.
     * @return          Average fuel consumption (at a certain speed).
     */
     double getAverageConsumption(double speed);

}
