import java.util.concurrent.ForkJoinPool;

import static edu.rice.pcdp.PCDP.isolated;

public class entry {

    volatile int test;
    public static void main(String... args) {
        // System.out.println(calculateAcceleration(2.0, 0.39, 7.1));
        System.out.println(velocidad(20.0, 2.0));
    }

    public static double calculateAcceleration(double x, double vI, double t) {
        return 2 * ((x - vI * t) / (t * t));
    }

    public static double velocidad(double angulo, double distancia) {
        System.out.println("");
        return Math.sqrt((distancia * 9.8) / Math.sin(angulo));
    }
}
