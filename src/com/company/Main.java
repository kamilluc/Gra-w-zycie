package com.company;

import java.io.Console;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        tgol mygame = new tgol(15, 15, 50);
        int iterations = 100;
        int delay = 1000;

        mygame.start();
        mygame.show();
        for (int i = 0; i < iterations; i++) {
            try {TimeUnit.MILLISECONDS.sleep(delay);} catch (Exception e) {}
            mygame.next();
            mygame.show();
        }
    }
}
