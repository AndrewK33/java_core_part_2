package Lesson_5;

import java.util.Date;
import java.util.Arrays;

public class Main {


    private static final int size = 10000000; //размер длинного массива
    private static final int n = 2; // число потоков
    private static final int h = size / n; //размер разделенных массивов
    private static float[] arr = new float[size]; //одномерный длинный массив


    public static void main(String[] args) {


        Main m1 = new Main();

        firstWorkArray();
        secondWorkArray();





    }

    public static void firstWorkArray () {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        Date currentTime = new Date(); //Взятие первоначального времени
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        Date newTime = new Date(); //Взятие времени после выполнения метода
        long longTime = currentTime.getTime() - newTime.getTime(); //Расчет времени, сколько выполнялся метод

        System.out.println(longTime);
    }

    public static void secondWorkArray () {

        Thread[] threads = new Thread[n];
        Arrays.fill(arr, 1.0f);

        long a = System.currentTimeMillis();

        float[][] newArr = new float[n][h];
        for (int i = 0; i < n; i++) {
            System.arraycopy(arr, i*h, newArr[i], 0, h);
        }

        long split = System.currentTimeMillis();


        for(int i=0; i<n; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> calcSecondArr(newArr, finalI));
            threads[i].start();
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        long concat = System.currentTimeMillis();



        for (int i = 0; i < n; i++) {
            System.arraycopy(newArr[i], 0, arr, i * h, h);
        }



        long end = System.currentTimeMillis();
        System.out.println(end);

    }


    private static void calcSecondArr(float[][] arr, int n) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < h; i++) {
            arr[n][i] = (float) (arr[n][i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long end = System.currentTimeMillis();

    }


}
