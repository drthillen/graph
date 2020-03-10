package package1;

public class DifferentLocks {
    public static void main(String[] args) throws InterruptedException {
        // Experiment 1
        TwoCounters2Locks2Vars c_2_2 = new TwoCounters2Locks2Vars();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 1; j++) {
                        c_2_2.inc1();
                    }
                    System.out.println("Thread T1 - " + c_2_2);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 1; j++) {
                        c_2_2.dec2();
                    }
                    System.out.println("Thread T2 - " + c_2_2);
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println();
        // Experiment 2
        TwoCounters1Locks2Vars c_1_2 = new TwoCounters1Locks2Vars();
        t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 1; j++) {
                        c_1_2.inc1();
                    }
                    System.out.println("Thread T1 --" + c_1_2);
                }
            }
        });

        t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 1; j++) {
                        c_1_2.dec2();
                    }
                    System.out.println("Thread T1 --" + c_1_2);
                }
            }
        });

        t1.start();
        t2.start();

    }
}

class TwoCounters2Locks2Vars {
    long c1 = 0, c2 = 0;
    Object lock1 = new Object();
    Object lock2 = new Object();

    public void inc1() {
        synchronized (lock1) {
            for (int i = 0; i < 10000000; i++) {
                c1++;
            }
        }
    }

    public void dec2() {
        synchronized (lock2) {
            for (int i = 0; i < 10000000; i++) {
                c2--;
            }
        }
    }

    public String toString() {
        return "c1: " + c1 + " c2: " + c2;
    }

}

class TwoCounters1Locks2Vars {
    long c1 = 0, c2 = 0;
    Object lock1 = new Object();

    public void inc1() {
        synchronized (lock1) {
            for (int i = 0; i < 10000000; i++) {
                c1++;
            }
        }
    }

    public void dec2() {
        synchronized (lock1) {
            for (int i = 0; i < 10000000; i++) {
                c2--;
            }
        }
    }

    public String toString() {
        return "c1: " + c1 + " c2: " + c2;
    }
}
