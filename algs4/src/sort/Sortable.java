package sort;

public abstract class Sortable<T> implements Comparable {
    @Override
    public int compareTo(Object o) {

        return 0;
    }

    private static boolean more(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
