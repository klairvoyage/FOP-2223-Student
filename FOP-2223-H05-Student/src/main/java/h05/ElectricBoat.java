package h05;

import java.util.function.IntSupplier;

/**
 * Extends the abstract class {@link MeansOfTransport} and implements {@link ElectricallyDriven}
 * and {@link IntSupplier} & manages methods used for an electric boat.
 */
public class ElectricBoat extends MeansOfTransport implements ElectricallyDriven, IntSupplier {

    private byte specificType;

    private int currentCharge;

    private int capacity;

    /**
     * Constructor initializes the attributes of its class with its given parameters.
     *
     * @param specificType      Specific type of the electric boat.
     * @param currentCharge     Current charge of the electric boat.
     * @param capacity          Electric capacity of the electric boat.
     */
    public ElectricBoat(byte specificType, int currentCharge, int capacity) {
        setSpecificType(specificType);
        transportType = TransportType.VESSEL;
        if (currentCharge<0) this.currentCharge = 0;
        else this.currentCharge = currentCharge;
        if (capacity<0) this.capacity = 0;
        else this.capacity = capacity;
        if (currentCharge>this.capacity) this.currentCharge = this.capacity;
    }

    /**
     * Provides the specific type of the electric boat.
     *
     * @return      The specific type the electric boat is.
     */
    public byte getSpecificType() { return specificType; }

    /**
     * Provides the current charge of the electric boat.
     *
     * @return      The current charge of the electric boat.
     */
    public int getCurrentCharge() { return currentCharge; }

    /**
     * Provides the electric capacity of the electric boat.
     *
     * @return      The electric capacity of the electric boat.
     */
    public int getCapacity() { return capacity; }

    @Override
    public boolean standardVoltageChargeable() { return (specificType==6 || specificType==11 || specificType==12 || specificType==22); }

    @Override
    public boolean highVoltageChargeable() {
        return ((specificType%2)==0 && ((specificType+1)%3)==0);
    }

    @Override
    public void letsGo(byte additionalChargeVolume, int distance) {
        if ((currentCharge+=additionalChargeVolume)<capacity) currentCharge += additionalChargeVolume;
        else currentCharge = capacity;
        letMeMove(distance);
    }

    @Override
    public int letMeMove(int distance) {
        if (currentCharge>0 && 1<=(distance/100)) {
            currentCharge--;
            return 1;
        } else return 0;
    }

    @Override
    public int getAsInt() {
        return capacity - currentCharge;
    }

    /**
     * Sets the electric boat to a specific type.
     *
     * @param specificType      The specific type the electric boat is set to.
     * @return                  The previous specific type of the electric boat.
     */
    public byte setSpecificType(byte specificType) {
        byte temp = this.specificType;
        if (specificType<0) this.specificType = 0;
        else if (specificType>30) this.specificType = 30;
        else this.specificType = specificType;
        return temp;
    }

}
