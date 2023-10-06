package h05;

/**
 * Manages methods used for electric-powered vehicles.
 */
public interface ElectricallyDriven {

    /**
     * Provides if the vehicle accepts standard voltage charge.
     *
     * @return      If the vehicle accepts standard voltage charge.
     */
    boolean standardVoltageChargeable();

    /**
     * Provides if the vehicle accepts high voltage charge.
     *
     * @return      If the vehicle accepts high voltage charge.
     */
    boolean highVoltageChargeable();

    /**
     * Charges the vehicle by a certain volume and then moves it.
     *
     * @param additionalChargeVolume    Electrical volume by which the vehicle is charged.
     * @param distance                  Distance by how much it is wanted to move the vehicle after charge.
     */
    void letsGo(byte additionalChargeVolume, int distance);

}
