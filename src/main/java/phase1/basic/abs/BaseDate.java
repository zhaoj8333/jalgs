package phase1.basic.abs;

public class BaseDate {
    private final int month;
    private final int day;
    private final int year;

    public BaseDate(int m, int d, int y) {
        month = m;
        day   = d;
        year  = y;
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int year() {
        return year;
    }

    public String toString() {
        return month() + "/" + day() + "/" + year();
    }

    public boolean equals(Object x) {
        if (this == x) {
            return true;
        }

        if (x == null) {
            return false;
        }

        if (this.getClass() != x.getClass()) {
            return false;
        }

        BaseDate that = (BaseDate)x;

        if (this.day != that.day) {
            return false;
        }

        if (this.month != that.month) {
            return false;
        }

        if (this.year != that.year) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        BaseDate a = new BaseDate(12, 31, 1999);
        BaseDate b = new BaseDate(1, 3, 2000);

        a = b;

        System.out.println(a);
        System.out.println(b);
    }
}
