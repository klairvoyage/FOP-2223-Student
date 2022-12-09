package h06;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * A collection of strange functions.
 */
public class StrangeFunctions {

    /**
     * A strange function.
     *
     * @param m a number
     * @param n another number
     * @return a mysterious result
     */
    public static double strangeFunction1(int m, int n) {
        // TODO: H1.1 - remove if implemented
        if (m<n) return m;
        else {
            if (0>(m%n)) return strangeFunction1(m-(m%n), n);
            else return (double)m/n;
        }
    }

    /**
     * Just another strange function.
     *
     * @param m just a number
     * @param n just another number
     * @return just another mysterious result
     */
    public static double strangeFunction2(int m, int n) {
        // TODO: H1.1 - remove if implemented
        return m<n ? m : 0>(m%n) ? strangeFunction2(m-(m%n), n) : (double)m/n;
    }

    /**
     * Oh, there is a third strange function?
     *
     * @param m a number
     * @param n another number
     * @return just another mysterious result
     */
    public static boolean understandable1(double m, double n) {
        // TODO: H1.2 - remove if implemented
        if ((m<=0) && (n<=0)) return true;
        else if ((m<=0) || (n<=0)) return false;
        else if (m<n) return understandable1(m-1, m+(2*n));
        else return understandable1(m, m-n);
    }

    /**
     * How much more strange functions are there?
     * @param m a number
     * @param n another number
     * @return just another mysterious result
     */
    public static boolean understandable2(double m, double n) {
        // TODO: H1.2 - remove if implemented
        return ((m<=0) && (n<=0)) ? true : ((m<=0) || (n<=0)) ? false : (m<n) ? understandable2(m-1, m+(2*n)) : understandable2(m, m-n);
    }

    /**
     * A simple function to transform arrays in a strange way.
     *
     * @param array the array to transform
     */
    public static void transformArray1(double[] array) {
        // TODO: H2 - remove if implemented
        if (array.length==1) array[0]++;
        else if (array.length>1) {
            double temp = array[0];
            for (int i=0;i<array.length-1;i++) array[i] += array[i+1];
            array[array.length-1] += temp;
        }
    }

    /**
     * Another simple function to transform arrays in a strange way.
     *
     * @param array the array to transform
     */
    public static void transformArray2(double[] array) {
        // TODO: H2 - remove if implemented
        if (array.length==1) array[0]++;
        else if (array.length>1) {
            double temp = array[0];
            doTheRecursion(array, 0);
            array[array.length-1] += temp;
        }
    }

    /**
     * This function is doing the recursive work!
     *
     * @param array the array to transform
     * @param i the index of the current element
     */
    public static void doTheRecursion(double[] array, int i) {
        // TODO: H2 - remove if implemented
        if (i!=array.length-1) {
            array[i] += array[i+1];
            doTheRecursion(array, i+1);
        }
    }
}
