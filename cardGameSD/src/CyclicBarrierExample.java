import java.util.concurrent.*;

public class CyclicBarrierExample {

    static class Worker implements Runnable {
        private final CyclicBarrier barrier;
        private final int id;

        public Worker(CyclicBarrier barrier, int id) {
            this.barrier = barrier;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // Simulating some work by the thread
                    System.out.println("Thread " + id + " is working...");
                    Thread.sleep((int)(Math.random() * 1000)); // Simulating random work duration

                    // At this point, all threads must reach the barrier before proceeding
                    System.out.println("Thread " + id + " reached barrier.");
                    barrier.await(); // Wait for other threads to finish

                    // After all threads have finished, they can perform their next round of work
                    System.out.println("Thread " + id + " can start next round.");
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int numThreads = 3; // Number of threads
        CyclicBarrier barrier = new CyclicBarrier(numThreads, new Runnable() {
            @Override
            public void run() {
                System.out.println("All threads have finished their tasks. Starting a new round.");
            }
        });

        // Create and start threads
        for (int i = 0; i < numThreads; i++) {
            new Thread(new Worker(barrier, i + 1)).start();
        }
    }
}
