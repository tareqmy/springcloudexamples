package com.tareqmy.employeeservice.utils;

import java.util.Random;

/**
 * Created by tareqmy at 14/5/22
 */
public class ArtificialUtils {

    private static final Random random = new Random();

    private ArtificialUtils() {
    }

    public static void artificialSlowness() {
        int i = random.ints(500, 1100)
            .findFirst()
            .getAsInt();
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {

        }
    }
}
