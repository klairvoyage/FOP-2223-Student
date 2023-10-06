package h05;

/**
 * Extends the abstract class {@link MeansOfTransport} and implements
 * {@link FuelDriven} & manages methods used for fuel-based vehicles.
 */
public class FuelDrivenVehicle extends MeansOfTransport implements FuelDriven {

    private FuelType fuelType;

    private int fillingLevel;

    /**
     * Constructor initializes the attributes of its class with its given parameters.
     *
     * @param fuelType          Type of fuel the vehicle uses.
     * @param transportType     Type of transportation of the vehicle.
     * @param fillingLevel      The fuel level of the vehicle.
     */
    public FuelDrivenVehicle(FuelType fuelType, TransportType transportType, int fillingLevel) {
        this.fuelType = fuelType;
        this.transportType = transportType;
        this.fillingLevel = fillingLevel;
    }

    @Override
    public FuelType getFuelType() { return fuelType; }

    /**
     * Provides the fuel level of the vehicle.
     *
     * @return      The filling level.
     */
    public int getFillingLevel() { return fillingLevel; }

    @Override
    public double getAverageConsumption(double speed) {
        if (speed<0) return 0;
        else if (speed>200) return 20;
        else return 0.1*speed;
    }

    /**
     * Fills up the vehicle with fuel.
     *
     * @param fillValue     Amount of fuel by which the vehicle is filled.
     */
    public void fillUp(int fillValue) { if (fillValue>0) fillingLevel += fillValue; }

    @Override
    public int letMeMove(int distance) {
        if (distance<0) return 0;
        else if (distance<(10*fillingLevel)) {
            fillingLevel -= distance/10;
            return distance;
        } else {
            int temp = fillingLevel;
            fillingLevel = 0;
            return temp*10;
        }
    }

}
