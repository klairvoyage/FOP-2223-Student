package h05;

/**
 * Extends the abstract class {@link MeansOfTransport} and implements the interfaces {@link FuelDriven} &
 * {@link ElectricallyDriven} and manages methods used for hybrid vehicles (V2).
 */
public class HybridType2 extends MeansOfTransport implements FuelDriven, ElectricallyDriven {

    private HybridType1 hybridObject;

    /**
     * Constructor initializes the attribute of its class with an instance of {@link HybridType1}.
     */
    public HybridType2() { hybridObject = new HybridType1(); }

    @Override
    public FuelType getFuelType() { return hybridObject.getFuelType(); }

    @Override
    public double getAverageConsumption(double speed) { return hybridObject.getAverageConsumption(speed); }

    @Override
    public boolean standardVoltageChargeable() { return hybridObject.standardVoltageChargeable(); }

    @Override
    public boolean highVoltageChargeable() { return hybridObject.highVoltageChargeable(); }

    @Override
    public void letsGo(byte additionalChargeVolume, int distance) { hybridObject.letsGo(additionalChargeVolume, distance); }

    @Override
    public int letMeMove(int distance) { return 0; }

}
