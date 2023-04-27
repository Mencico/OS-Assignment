public class Threads {
    public static void main(String[] args) {
        Thread th1 = new Thread(new ThreadFunction());
        th1.setName("Thread 1");
        Thread th2 = new Thread(new ThreadFunction());
        th2.setName("Thread 2");
        Thread th3 = new Thread(new ThreadFunction());
        th3.setName("Thread 3");
        Thread th4 = new Thread(new ThreadFunction());
        th4.setName("Thread 4");

        th1.start();
        th2.start();
        th3.start();
        th4.start();
    }
}

class ThreadFunction implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("I'm inside parallel threads. Loop: " + Integer.toString(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


