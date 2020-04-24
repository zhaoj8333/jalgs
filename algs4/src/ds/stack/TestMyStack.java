package ds.stack;

public class TestMyStack {
    public static void main(String[] args) {
        MyStack s = new MyStack(5);

//        while (!StdIn.isEmpty() && !s.isFull()) {
//            s.push(StdIn.readInt());
//            System.out.println(Arrays.toString(s.arr));
//            System.out.println("top: " + s.top);
//        }
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        s.push(6);

        System.out.println(s.isEmpty());
        System.out.println(s.isFull());

        System.out.println(s.peek());
        System.out.println();


//        while (!s.isEmpty()) {
//            System.out.println(s.pop() + ", ");
//        }
//        System.out.println(s.isEmpty());
//        System.out.println(s.isFull());
    }
}
