package h05;

/**
 * Abstract class that manages the different means of transport.
 */
public abstract class MeansOfTransport {

    /**
     * Transport type of the vehicle.
     */
    protected TransportType transportType;

    /**
     * Provides the transport type of the vehicle.
     *
     * @return      Transport type of the vehicle.
     */
    public TransportType getTransportType() { return transportType; }

    /**
     * Moves the vehicle.
     *
     * @param distance      Distance by how much it is wanted to move the vehicle.
     * @return              By how much the filling level of the vehicle is reduced.
     */
    public abstract int letMeMove(int distance);

    /**
     * Adjusts the String to the corresponding conventions.
     *
     * @return      The corrected String.
     */
    @Override
    public String toString() {
        if (super.toString()==null) return "I am an undefined.";
        String s = super.toString();
        char[] chArr = new char[s.length()];
        int firstIndex = (int)(s.charAt(0));
        if (lowCase(firstIndex)) chArr[0] = (char)(s.charAt(0)-32);
        else if (upCase(firstIndex) || isNumber(firstIndex)) chArr[0] = s.charAt(0);
        int charIndex;
        for (int i=1;i<s.length();i++) {
            charIndex = (int)(s.charAt(i));
            if (upCase(charIndex)) chArr[i] = (char)(s.charAt(i)+32);
            else if (lowCase(charIndex) || isNumber(charIndex)) chArr[i] = s.charAt(i);
            else chArr[i] = ' ';
        }
        String yo = new String(chArr);
        if (chArr[0]=='A' || chArr[0]=='E' || chArr[0]=='I' || chArr[0]=='O' || chArr[0]=='U') return "I am an " + yo + ".";
        return "I am a " + yo + ".";
    }

    private boolean lowCase(int asciiIndex) { return ((asciiIndex>='a' && asciiIndex<='z') || asciiIndex=='ä' || asciiIndex=='ö' || asciiIndex=='ü'); }

    private boolean upCase(int asciiIndex) { return ((asciiIndex>='A' && asciiIndex<='Z') || asciiIndex=='Ä' || asciiIndex=='Ö' || asciiIndex=='Ü'); }

    private boolean isNumber(int asciiIndex) {
        return asciiIndex>='0' && asciiIndex<='9';
    }

}
