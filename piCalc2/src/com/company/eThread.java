package com.company;

public class eThread extends Thread {
    private int l;
    private int u;
    eThread(int l, int u){
        this.l = l;
        this.u = u;

    }
    public void run() {
        Main.totals.add(Main.series(l,u));
        System.out.println("Thread finished"+String.valueOf(l)+" "+String.valueOf(u));
    }
}
