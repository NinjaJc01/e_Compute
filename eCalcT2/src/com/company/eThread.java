package com.company;

public class eThread extends Thread {
    private int l;
    private int u;
    eThread(int l, int u){
        this.l = l;
        this.u = u;

    }
    public void run() {
        //System.out.println("Hello from a thread");
        Main.totals.add(Main.series(l,u));
    }
}
