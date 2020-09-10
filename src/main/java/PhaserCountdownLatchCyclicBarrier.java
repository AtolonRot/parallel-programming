public class PhaserCountdownLatchCyclicBarrier {

    public static void main(String... args) throws InterruptedException {
        final long startTime = System.currentTimeMillis();
        final int n = 10;
        Thread[] t = new Thread[n];
        for(int i = 0; i < n; i++) {
            t[i] = new Thread(new MyThread());
            t[i].start();
        }

        final long endTime = System.currentTimeMillis() - startTime;
        System.out.println("Final: " + endTime);
    }

    static class MyThread implements Runnable {

        @Override
        public void run() {
            System.out.println("Hello from thread" + this.toString());
        }
    }
}
