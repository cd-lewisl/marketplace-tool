import java.util.concurrent.CountDownLatch;

public class T2 {

    public static void main(String[] args) {
        final Object monitorA = new Object();
        final CountDownLatch latch = new CountDownLatch(1);

        new Thread("B") {
            @Override
            public void run() {

                try {
                    latch.wait();
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
                            latch.countDown();
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
