package ds.array;

import java.util.Arrays;

public class ResizableArray {
    public static void main(String[] args) {
        // 数组扩容
//        increseCapacity();
//        shallowCopy();
//        deepCopy();
    }

    /**
     * 为何数组长度不可改变：
     *  增加数组长度时，可能会导致别的变量被覆盖
     */

    /**
     * 深拷贝：
     *  会拷贝所有的属性，并拷贝属性指向的动态分配的内存
     *  对象和其引用一起被拷贝时发生深拷贝
     *  深拷贝开销较大
     */
    private static void deepCopy() {

    }

    /**
     * 浅拷贝：
     *  如果属性时基本类型，拷贝的是变量的值
     *  如果属性是内存地址，拷贝的就是内存地址
     */
    private static void shallowCopy() {
        Person p1 = new Person("allen");
        Person[] ps1 = new Person[]{p1};
        Person[] ps2 = Arrays.copyOf(ps1, ps1.length);

        System.out.println("ps1: before " + Arrays.toString(ps1));
        System.out.println("ps2: before " + Arrays.toString(ps2));
        ps2[0].setName("AAAAA");
        System.out.println("ps1: after  " + Arrays.toString(ps1));
        System.out.println("ps2: after  " + Arrays.toString(ps2));
        System.out.println("=======================");
    }

    static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person: " + this.getName() + " + " + this.hashCode();
        }
    }

    // ArrayList add 扩容处理
    public static void increseCapacity() {
        Integer[] arr = new Integer[] {1, 2, 3, 4, 5};

        System.out.println(Arrays.toString(expand(arr, 10)));
        System.out.println(Arrays.toString(expand(arr)));
        System.out.println(Arrays.toString(expandMulti(arr, 3)));

    }

    public static <T> T[] expand(T[] array, int newLen) {
        // List 它的思路是将原始数组拷贝到新数组中，新数组是原始数组长度的 1.5 倍。
        newLen = newLen < 0 ? array.length : newLen;

        return Arrays.copyOf(array, newLen);
    }

    public static <T> T[] expand(T[] array) {
        int newLen = (int)(array.length * 1.5 + 1);

        return Arrays.copyOf(array, newLen);
    }

    public static <T> T[] expandMulti(T[] array, int multiple) {
        int newLen = array.length * Math.max(1, multiple);

        return Arrays.copyOf(array, newLen);
    }
}
