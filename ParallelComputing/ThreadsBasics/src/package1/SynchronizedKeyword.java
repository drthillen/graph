package package1;

import java.lang.Thread;

public class SynchronizedKeyword {
    public static void main(String[] args) {
        IntegerWrapper i = new IntegerWrapper();
        //
        Thread1 t1 = new Thread1(i, -1);
        Thread1 t2 = new Thread1(i, 2);

        Thread T1 = new Thread(t1);
        Thread T2 = new Thread(t2);

        T1.start();
        T2.start();

    }
}

class Thread1 implements Runnable {
    IntegerWrapper i;
    int y = 0;

    Thread1(IntegerWrapper i, int y) {
        this.i = i;
        this.y = y;
    }

    void method() {
        i.setI(y);
    }

    public void run() {
        method();
    }
}

class IntegerWrapper {
    int l = 0;

    /**
     * @param i the i to set
     */
    public synchronized void setI(int j) {
        int a = 1;
        l = j;
        for (int i = 0; i < 100; i++) {

            try {
                Thread.sleep(0);
                /* One could also do the following calc. expensive enoughto be analogous */
                for (int m = 0; m < 2_000000; m++) {
                    a = (int) Math.sqrt(j);
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("" + j + ": " + this.l);
        }
    }
}