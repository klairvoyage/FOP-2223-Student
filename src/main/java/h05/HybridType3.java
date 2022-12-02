package h05;

/**
 * Implements the interface {@link HybridVehicle} and manages methods used for hybrid vehicles (V3).
 */
public class HybridType3 implements HybridVehicle {

    private static FuelType fuelType;

    private static double averageConsumption;

    private static boolean standardVoltageChargeable;

    private static boolean highVoltageChargeable;

    private DriveType preferredDriveType;

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
    public void setFuelType(FuelType fuelType) { HybridType3.fuelType = fuelType; }

    /**
     * Sets the average consumption of the hybrid vehicle to the given amount.
     *
     * @param averageConsumption    Average consumption the hybrid vehicle is set to.
     */
    public void setAverageConsumption(double averageConsumption) { HybridType3.averageConsumption = averageConsumption; }

    /**
     * Changes if the hybrid vehicle is standard voltage chargeable.
     */
    public void toggleStandardVoltageChargeable() { standardVoltageChargeable ^= true; }

    /**
     * Changes if the hybrid vehicle is high voltage chargeable.
     */
    public void toggleHighVoltageChargeable() { highVoltageChargeable ^= true; }

    @Override
    public void letsGo(byte additionalChargeVolume, int distance) { }

    @Override
    public DriveType getPreferredDriveType() { return preferredDriveType; }

    /**
     * Changes the preferred driving type.
     */
    public void togglePreferredDriveType() { preferredDriveType = getPreferredDriveType()==DriveType.ELECTRICAL ? DriveType.FUEL_BASED : DriveType.ELECTRICAL; }

}
