package package1;

import java.lang.*;

public class JoinMethod {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");
        int[] arr = new int[10];
        Thread t1 = new Thread(new ModifyArray(arr));
        Thread t2 = new Thread(new ModifyArray2(arr));
        t1.start();
        t2.start();
        t2.join();
        t1.join();
        //

        // t2.start();
        // t2.join();// try commenting this out...
        //
        System.out.println(arr[0]);
    }
}

class ModifyArray implements Runnable {
    int[] arr;

    ModifyArray(int[] arr) {
        this.arr = arr;
    }

    public void run() {
        for (int i = 0; i < 1_000_00; i++) {
            arr[0] += 1;
        }
        System.out.println("Thread has finished running.");
    }
}

class ModifyArray2 implements Runnable {
    int[] arr;

    ModifyArray2(int[] arr) {
        this.arr = arr;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(arr[0]--);
        }
        System.out.println("Thread2 has finished running.");
    }
}