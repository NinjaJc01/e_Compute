package com.company;
import java.math.*;
import java.util.ArrayList;

public class Main {
    //These are for setting options! Feel free to tweak these.
    private static int precision = 10001;
    static int targetLimit = 6000;
    static int threadCount = 128;
    static double workBase = 1.85; //This can behave slightly oddly, be careful MUST BE LARGER THAN 1.5
    // optimum seems about 1.85


    //These are NOT to be touched
    private static MathContext context = new MathContext(precision);
    static ArrayList<BigDecimal> totals = new ArrayList<>();
    private static BigDecimal total = new BigDecimal(0,context);
    static Thread[] threads = new Thread[threadCount];
    /*
    These set up:
    The precision of internal calculations,
    A list of totals (Must be a list because of race conditions and other things)
    An array of threads for use later
    */

    public static void main(String[] args) {
        /*
        Calculate some limits using a completely arbitrary algorithm
        Because earlier iterations take less time
        We need to distribute most of the work to the 'first' thread
        And less going downwards though the threads
        THIS WORKS VERY BADLY!
        */
        int[] limits = new int[threadCount]; //Array to store the 'widths' of each thread
        int total_old = 0; //Used for making sure that the threads reach the target
        for (int threadID = 0; threadID < threadCount; threadID++)
        {
            limits[threadID] = ((int) (targetLimit/Math.pow(workBase,(threadID+1)))+5);
            total_old += limits[threadID];
        }
        limits[0] = limits[0] + (targetLimit - total_old);
        //Start threads with the limits calculated
        long unixTimestampOld = System.currentTimeMillis();
        total_old = 0; //reset totalling counter thing
        int counter = 0;
        //Create and set running threads
        for (int limit : limits){
            threads[counter] = new eThread(total_old+1, (limit+total_old));
            threads[counter].start();
            counter++;
            total_old += limit;
        }
        //Delay the main until the threads are all finished, using join
        try {
            for (Thread thread:threads) {thread.join();} //using the array of threads, join all of them
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Output the final time taken
        //System.out.println(((System.currentTimeMillis() - unixTimestampOld) +"ms"));
        //Add all the individual threads totals
        for (BigDecimal num : totals) {
            total = total.add(num);
        }
        total = total.multiply(BigDecimal.valueOf(2)).add(BigDecimal.valueOf(2));
        System.out.println(total);
    }
    private static BigDecimal factorial(int number) {
        BigDecimal factorial = BigDecimal.ONE;

        for (int i = number; i > 0; i--) {
            factorial = factorial.multiply(BigDecimal.valueOf(i));
        }

        return factorial;
    }
    static BigDecimal series(int lower, int upper) {
        BigDecimal pi = new BigDecimal(0,context);
        BigDecimal add;
        for (int n = lower; n < upper; n++){
            //2^k*(k!)^2
            add = new BigDecimal((BigInteger.TWO.pow(n)).multiply((factorial(n)).pow(2).toBigInteger()));
            add = add.divide(factorial((2*n+1)),context);
            pi = pi.add(add);
        }
        //
        // System.out.println(e);
        return(pi);
    }
}
