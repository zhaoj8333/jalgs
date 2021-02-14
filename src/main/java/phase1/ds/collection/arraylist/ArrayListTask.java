package phase1.ds.collection.arraylist;

import java.util.List;

public class ArrayListTask implements Runnable {

    private List<String> list;

    public ArrayListTask(List<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
//        synchronized (this) {
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            list.add(Thread.currentThread().getName());
//        }
    }
}
