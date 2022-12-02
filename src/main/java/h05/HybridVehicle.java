package h05;

/**
 * Extends the interfaces {@link FuelDriven} & {@link ElectricallyDriven}
 * and manages methods used for hybrid vehicles.
 */
public interface HybridVehicle extends FuelDriven, ElectricallyDriven {

    /**
     * Provides if the hybrid vehicle prefers electric-powered or fuel-based driving.
     *
     * @return      Preferred driving type of the hybrid vehicle.
     */
    DriveType getPreferredDriveType();

}
