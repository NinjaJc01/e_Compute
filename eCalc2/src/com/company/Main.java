package com.company;
import java.math.*;
public class Main {
    static int precision = 10001;
    static int limit = 1625;
    static MathContext context = new MathContext(precision);
    public static void main(String[] args) {
	// write your code here
        long unixTimestampOld = System.currentTimeMillis();
        BigDecimal e = new BigDecimal(0,context);
        BigDecimal add;

        for (int n = 0; n < limit; n++){
            add = new BigDecimal((2*n)+2, context);
            add = add.divide(factorial((2*n+1)),context);
            e = e.add(add);
        }
        //System.out.println(e.toString());
        System.out.println(((System.currentTimeMillis() - unixTimestampOld) +"ms"));
    }
    private static BigDecimal factorial(int number) {
        BigDecimal factorial = BigDecimal.ONE;

        for (int i = number; i > 0; i--) {
            factorial = factorial.multiply(BigDecimal.valueOf(i));
        }

        return factorial;
    }

}
