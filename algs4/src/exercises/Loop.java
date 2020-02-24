package exercises;

public class Loop {
    public static void main(String[] args) {
//        subj1();
//        subj2();
//        subj3();
        subj4();
    }

    public static void subj1() {
        for (int i = 0; i < 4; i++) {
            System.out.print(" ");
            for (int j = 0; j < 4; j ++) {
                System.out.print("@");
            }
        }
    }

    public static void subj2() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("@");
            }
            System.out.println();
        }
    }

    public static void subj3() {
        for (int i = 1; i < 10; i++) {
            System.out.println("1元 " + i + " 张， 5角 " + (10 - i) * 2 + "个");
        }
    }

    public static void subj4() {
        for (int i = 1988; i <= 2019; i++) {
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                System.out.println(i + "： 是闰年");
            }
        }
    }
}
