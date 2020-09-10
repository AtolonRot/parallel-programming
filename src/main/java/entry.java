import java.util.concurrent.ForkJoinPool;

public class entry {

    public static void main(String... args) {
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println("Entrando");
        int n = pool.invoke(new FibonacciFuture(20));
        System.out.println("Saliendo");
        System.out.println(n);
    }
}
