import java.util.concurrent.ForkJoinPool;

import static edu.rice.pcdp.PCDP.isolated;

public class entry {

    volatile int test;
    public static void main(String... args) {
        System.out.println(calculateAcceleration(2.0, 0.39, 7.1));
        isolated(() -> {

        });
    }

    public static double calculateAcceleration(double x, double vI, double t) {
        return 2 * ((x - vI * t) / (t * t));
    }
}
