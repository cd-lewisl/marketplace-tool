public class T1 {

    public static void main(String[] args) {
        final Object monitorA = new Object();
        final Object monitorB = new Object();

        new Thread("B") {
            @Override
            public void run() {

                synchronized (monitorB) {
                    try {
                        monitorB.wait();
                    } catch (InterruptedException e) {
                    }
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
                            synchronized (monitorB) {
                                monitorB.notify();
                            }
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
