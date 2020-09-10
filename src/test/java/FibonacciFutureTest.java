import junit.framework.TestCase;

import java.util.concurrent.ForkJoinPool;

public class FibonacciFutureTest extends TestCase {

    public void testFibonacci() {
        ForkJoinPool pool = new ForkJoinPool(1);
        final int result = pool.invoke(new FibonacciFuture(5));
        System.out.println("Result:" + result);
        assertEquals(result, 20);
    }
}
