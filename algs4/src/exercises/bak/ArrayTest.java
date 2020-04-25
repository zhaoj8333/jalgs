package exercises.bak;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ArrayTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
        test7();
    }

    public static void test7() {
        Scanner sc = new Scanner(System.in);
        int count  = 5;
        int index  = 0;
        int[] arr  = new int[count];
        while (index < count && sc.hasNext()) {
            arr[index] = sc.nextInt();
            index++;
        }
//        Random random = new Random();
//        int[] arr = new int[6];
//        for (int i = 0; i < 6; i++) {
//            arr[i] = random.nextInt(100);
//        }
//        System.out.println(Arrays.toString(arr));
//        System.out.println("aaaaaaaaaa: " + arr.length / 2);
        for (int i = 0; i < arr.length / 2; i++) {
            /**
             * i 与 第j个交换:
             *   0 -> arr.length - 1 - 0
             *   1 -> arr.length - 1 - 1
             *   2 -> arr.length - 2 - 1
             *
             *   j -> arr.length - i - 1
             *
             *  优化50%, 复杂度O(n)
             */
            int temp = arr[i];
            arr[i]   = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void test6() {
        int[] grade = {72, 89, 65, 87, 91, 82, 71, 93, 76, 68};
        int sum = 0;
        for (int i = 0; i < grade.length; i++) {
            sum += grade[i];
        }

        System.out.println("average grade: " + sum / grade.length);
    }

    public static void test5() {
        Random random = new Random();
        int[] arr = new int[5];
        for (int i = 0; i < 5; i++) {
            arr[i] = random.nextInt(10);
        }
        System.out.println(Arrays.toString(arr));
//        方法一
        String str = "";
        for (int i = 0; i < arr.length ; i++) {
            str += arr[i];
        }
        System.out.println(str);
        System.out.println("result: " + Integer.parseInt(str));


        System.out.println("============================");
//        方法二
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = (int)(arr[i] * Math.pow(10, arr.length - i - 1));
        }
        System.out.println(Arrays.toString(arr));
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        System.out.println("sum: " + sum);
    }

    public static void test4() {
        Random random = new Random();
        int[] arr = new int[6];
        for (int i = 0; i < 5; i++) {
            arr[i] = random.nextInt(100) + 1;
        }
        System.out.println(Arrays.toString(arr));
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += arr[i];
        }
        System.out.println("sum: " + sum);
    }

    public static void test3() {
        double[] arr = {12.9, 53.4, 75.0, 99.1, 3.14};
        double   min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        System.out.println("min: " + min);
    }

    public static void test2() {
        int[] arr = new int[5];
        for (int i = 0; i < 5; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void test1() {
        int[] arr = new int[4];
        arr[0] = 10;
        arr[1] = 100;
        arr[2] = 1000;
        arr[3] = 10000;
    }
}
