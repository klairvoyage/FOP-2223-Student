package h05;

/**
 * Implements the interfaces {@link FuelDriven} & {@link ElectricallyDriven}
 * and manages methods used for hybrid vehicles (V1).
 */
public class HybridType1 implements FuelDriven, ElectricallyDriven {

    private static FuelType fuelType;

    private static double averageConsumption;

    private static boolean standardVoltageChargeable;

    private static boolean highVoltageChargeable;

    @Override
    public FuelType getFuelType() { return fuelType; }

    @Override
    public double getAverageConsumption(double speed) { return averageConsumption; }

    @Override
    public boolean standardVoltageChargeable() { return standardVoltageChargeable; }

    @Override
    public boolean highVoltageChargeable() { return highVoltageChargeable; }

    /**
     * Sets the fuel type of the hybrid vehicle to the given type.
     *
     * @param fuelType      Fuel type the hybrid vehicle is set to.
     */
    public void setFuelType(FuelType fuelType) {
        HybridType1.fuelType = fuelType;
    }

    /**
     * Sets the average consumption of the hybrid vehicle to the given amount.
     *
     * @param averageConsumption    Average consumption the hybrid vehicle is set to.
     */
    public void setAverageConsumption(double averageConsumption) { HybridType1.averageConsumption = averageConsumption; }

    /**
     * Changes if the hybrid vehicle is standard voltage chargeable.
     */
    public void toggleStandardVoltageChargeable() { standardVoltageChargeable ^= true; }

    /**
     * Changes if the hybrid vehicle is high voltage chargeable.
     */
    public void toggleHighVoltageChargeable() {
        highVoltageChargeable ^= true;
    }

    @Override
    public void letsGo(byte additionalChargeVolume, int distance) { }

}
