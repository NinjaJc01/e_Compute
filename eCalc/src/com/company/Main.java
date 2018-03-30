package com.company;
import org.apfloat.Apfloat;
import org.apfloat.Apint;
import org.apfloat.ApintMath;

public class Main {

    static int precision = 10001;
    static int limit = 1625;
    public static void main(String[] args) {
        long unixTimestampOld = System.currentTimeMillis();
        Apfloat e = new Apfloat(0,precision);
        Apfloat add;
        //Sum
        for (int i=0; i<limit; i++) {
            add = new Apfloat(((2*i)+2),precision);
            add = add.divide((ApintMath.factorial((2*i)+1)));
            e = e.add(add);
        }
        //System.out.println(e.toString());
        System.out.println(((System.currentTimeMillis() - unixTimestampOld) +"ms"));
    }
}
