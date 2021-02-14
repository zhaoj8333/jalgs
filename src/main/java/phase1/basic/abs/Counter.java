package phase1.basic.abs;

public class Counter {

    private String id;

    private Integer counter = new Integer(1);

    public Counter (String id) {
        this.id = id;
    }

    public void increment() {
        this.counter ++;
    }

    public Integer tally() {
        return this.counter;
    }

    @Override
    public String toString() {
        return this.id;
    }

    public static void main(String[] args) {
        Counter heads = new Counter("heads");
//        heads.increment();
        System.out.println(heads.tally());
        System.out.println(heads.tally() - heads.tally());

        Counter tails = new Counter("tails");
//        System.out.println(heads);
//        System.out.println(tails);
    }
}
