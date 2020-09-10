import java.util.concurrent.RecursiveTask;

public class FibonacciFuture extends RecursiveTask<Integer> {
    final int n;
    FibonacciFuture(int n) { this.n = n; }

    @Override
    protected Integer compute() {
        if(n <= 1) return n;
        FibonacciFuture n1 = new FibonacciFuture(n - 1);
        FibonacciFuture n2 = new FibonacciFuture(n - 2);
        return n2.compute() + n1.join();
    }
}
