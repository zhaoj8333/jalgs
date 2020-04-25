package exercises.bak;

import java.util.Random;
import java.util.Scanner;

public class LoopRandom {
    public static void main(String[] args) {
//        exe1();
//        exe2();
//        exe3();
        exe4();
    }

    public static void exe4() {
        Random random = new Random();
        int number = random.nextInt(50) + 51;
        int count  = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入猜到的数");
        while (true && scanner.hasNext()) {
            String guess = scanner.next();
            int guessNumber = Integer.parseInt(guess);
            if (guessNumber != number) {
                if (guessNumber > number) {
                    System.out.println("大了");
                } else {
                    System.out.println("小了");
                }
                count++;
            } else {
                System.out.println("恭喜，猜中了");
                System.exit(0);
            }
            if (count > 5) {
                System.out.println("猜数字失败，游戏结束");
                System.exit(0);
            }
        }
    }

    public static void exe3() {
        for (int i = 5; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print("@");
            }
            System.out.println();
        }
    }

    public static void exe2() {
        int sum = 0;
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(50) + 51; // 50-99
            sum += number;
            System.out.println(number);
        }
        System.out.println("sum: " + sum);
    }

    public static void exe1() {
        for (int i = 0; i < 10; i++) {
            if (i == 8) {
                break;
            }
        }
    }
}
