package com.company;
import java.math.*;
import java.util.ArrayList;

public class Main {
    private static int precision = 10001;
    //static int limit = 10;
    private static MathContext context = new MathContext(precision);
    static ArrayList<BigDecimal> totals = new ArrayList<>();
    private static BigDecimal total = new BigDecimal(0,context);
    public static void main(String[] args) {
        //Calculate some limits using a completely arbitrary algorithm
        //Because earlier iterations take less time
        //We need to distribute most of the work to the 'first' thread
        //And less going downwards though the threads


        //Start threads with the limits calculated
        long unixTimestampOld = System.currentTimeMillis();
        eThread p1 = new eThread(0,915);
        p1.start();
        eThread p2 = new eThread(916,1322);
        p2.start();
        eThread p3 = new eThread(1323,1526);
        p3.start();
        eThread p4 = new eThread(1527,1625);
        p4.start();
        try {
            p1.join();
            p2.join();
            p3.join();
            p4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(((System.currentTimeMillis() - unixTimestampOld) +"ms"));
        for (BigDecimal num : totals) {
            total = total.add(num);
        }
        //System.out.println(total);
    }
    private static BigDecimal factorial(int number) {
        BigDecimal factorial = BigDecimal.ONE;

        for (int i = number; i > 0; i--) {
            factorial = factorial.multiply(BigDecimal.valueOf(i));
        }

        return factorial;
    }
    static BigDecimal series(int lower, int upper) {
        BigDecimal e = new BigDecimal(0,context);
        BigDecimal add;
        for (int n = lower; n < upper; n++){
            add = new BigDecimal(2*n+2, context);
            add = add.divide(factorial((2*n+1)),context);
            e = e.add(add);
        }
        //
        // System.out.println(e);
        return(e);
    }
}
