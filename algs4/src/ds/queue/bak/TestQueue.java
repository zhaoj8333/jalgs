package ds.queue.bak;

public class TestQueue {
    public static void main(String[] args) {
//        testMyQueue();
        testMyLoopQueue();

    }

    public static void testMyLoopQueue() {
        MyLoopQueue q = new MyLoopQueue();
//        for (int i = 0; i < 10 && !q.isFull(); i ++) {
//            q.insert(i);
////            System.out.println(q.end);
//        }
////        System.out.println(q.arr.length);
//        while (!q.isEmpty()) {
//            System.out.println("remove: " + q.remove());
//        }
        q.insert(1);
        q.insert(2);
        q.insert(3);
        q.insert(4);
        q.insert(5);
        q.insert(6);
        System.out.println("===============================");

        System.out.println("ele: " + q.ele);
//        System.out.println(q.isFull());
//        System.out.println(q.isEmpty());
//        System.out.println(q.peek());
//        System.out.println("===============================");
//        System.out.println(q.peek());
//        System.out.println(q.remove());
//        System.out.println(q.remove());
//        System.out.println(q.remove());
//        System.out.println(q.remove());
//        System.out.println("--------------------------------");
//        System.out.println(q.remove());
//        System.out.println(q.remove());
//        System.out.println(q.remove());
//        System.out.println(q.remove());
//        System.out.println(q.remove());
//        System.out.println("--------------------------------");
//        System.out.println("===============================");
//        System.out.println(q.isEmpty());
//        System.out.println(q.isFull());
        while (!q.isEmpty()) {
            System.out.println(q.remove() + "  ");
        }
        System.out.println("==============================");
        System.out.println(q.isEmpty());
        System.out.println();
    }

    public static void testMyQueue() {
        MyQueue q = new MyQueue(4);

//        q.insert(1);
//        q.insert(2);
//        q.insert(3);
//        q.insert(4);

        System.out.println(q.isEmpty());
        System.out.println(q.isFull());
//        System.out.println(q.peek());

//        q.remove();
//        System.out.println(q.peek());

        int a = 0;
        while (!q.isFull()) {
            q.insert(a);
            a++;
//            System.out.println();
        }

        while (!q.isEmpty()) {
            System.out.println("remove: " + q.remove() + " ");
        }

//        q.peek();
        q.insert(111);  // ArrayIndexOutOfBoundsException
        // 使用循环队列解决问题
    }

}

