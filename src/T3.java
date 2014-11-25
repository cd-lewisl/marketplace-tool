import java.util.concurrent.Semaphore;

public class T3 {

    public static void main(String[] args) {
        final Object monitorA = new Object();
        final Semaphore semap = new Semaphore(0);

        new Thread("B") {
            @Override
            public void run() {

                try {
                    semap.acquire();
                } catch (InterruptedException e1) {
                }

                for (int i = 0; i < 26; i++) {
                    synchronized (monitorA) {
                        monitorA.notify();
                        System.out.println((char) (i + 65));
                        try {
                            if (i != 25) {
                                monitorA.wait();
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        }.start();

        new Thread("A") {
            @Override
            public void run() {
                for (int i = 0; i < 26; i++) {
                    synchronized (monitorA) {
                        monitorA.notify();
                        System.out.println(i);
                        if (i == 0) {
                            semap.release();
                        }
                        try {
                            if (i != 25) {
                                monitorA.wait();
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        }.start();
    }

}
