public class Threads {
        public static void main(String[] args) {
            Thread t1 = new Thread(new ThreadRun(), "The Thread 1");
            Thread t2 = new Thread(new ThreadRun(), "The Thread 2");
            Thread t3 = new Thread(new ThreadRun(), "The Thread 3");
            Thread t4 = new Thread(new ThreadRun(), "The Thread 4");



            t1.start();
            t2.start();
            t3.start();
            t4.start();
        }
}

            class ThreadRun implements Runnable {
                public void run() {

                    System.out.println(Thread.currentThread().getName() + " is running perfectly!");
                }
            }


