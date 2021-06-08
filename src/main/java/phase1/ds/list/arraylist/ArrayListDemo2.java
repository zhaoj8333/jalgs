package phase1.ds.list.arraylist;

import java.io.*;
import java.util.*;
import phase1.ds.common.Skill;
import phase1.ds.common.Student;

public class ArrayListDemo2 {
    public static void main(String[] args) throws Exception {
//        demo1();
//        serialize();
//        serialize1();
//        serialize3();
//        objclone();
//        demo6();
//        shadowcopy();
//        shallowcopy1();
//        randomaccess();
//        randomAndSequence();
//        initWithoutArgs();
//        addWithArgs();
//        addAll0();
//        arraycopy();
//        arrayCopyTest();
//        listSet();
//        listGet();
//        listToString();
//        listIterator();
        listConcurrentModificationException1();
//        itrDefaultRemove();
//        listClear();
//        listContains();
//        listIsEmpty();
//        capacityExpansionPerformance();
//        listAddDeletePerformance();
//        listNonThreadSafe();
        /*
        ArrayList安全版本：
            Vector
            Collections.synchronizedList
         */
//        whenThreadSafe();
//        listCopy();
//        multiThreadUpdateAndRead();
//        arrayAndLinkList();
//        customizedArrayList();
//        capacityShink();
    }

    private static void capacityShink() {
        // ArrayList 的 trimToSize 将缩小到ArrayList.size的大小，
        // 该方法为手动触发

        /**
         * 复杂度震荡：
         *
         */
    }

    private static void customizedArrayList() {
//        MyArrayList<String> mylist = new MyArrayList<>();
//        System.out.println(mylist);
    }

    private static void arrayAndLinkList() {
        /*
            ArrayList:
                基于动态数组的数据结构
                随机访问get,set优于LinkedList
                随机操作add，move，ArrayList不一定比LinkedList慢，ArrayList并不是每次都要创建新数组
         */

        /*
            LinkedList:
                基于链表
                对于顺序操作，LinkedList不一定比ArrayList慢
                随机操作，LinkedList效率明显较低
         */
    }

    /*
        已知成员变量集合有n个用户，
        多线程环境下，使用迭代器读取，同时保证可以正常写入数据到集合
     */
    private static void multiThreadUpdateAndRead() {
        CollectionThread ct = new CollectionThread();
        for (int i = 0; i < 10; i++) {
            new Thread(ct).start(); // 并发修改异常
        }
    }

    private static void listCopy() {
        // clone
        /*
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("java");
        list.add("php"); // modCount = 3

        ArrayList<String> list1 = new ArrayList<>();
        list1 = (ArrayList<String>) list.clone();
        list.phase1.ds.set(1, "HELLO");
        System.out.println(list1);
        System.out.println();
        */
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("A", 1));
        students.add(new Student("B", 1));
        students.add(new Student("C", 1));
        students.add(new Student("D", 1));

//        ArrayList<Student> students1 = new ArrayList<>();
//        students1 = (ArrayList<Student>) students.clone();
//        students.phase1.ds.set(1, new Student("BB", 11));
//        showList(students1);
//        showList(students);

//        ArrayList<Student> students1 = new ArrayList<>(students);
//        students.phase1.ds.set(1, new Student("BB", 11));
//        showList(students1);

//        ArrayList<Student> students1 = new ArrayList<>();
//        students1.addAll(students);
//        students.phase1.ds.set(1, new Student("BB", 11));
//        showList(students1);

    }

    private static void whenThreadSafe() {
        // 如果arraylist是局部变量，意味着每个线程都有一份各自的变量，所以没必要加同步
        // 成员变量会被各个线程共享，最好加上同步块

        /*

         */
    }

    private static void listNonThreadSafe() throws InterruptedException {
        ArrayList<String> list1 = new ArrayList<>();
//        Vector<String> list = new Vector<>();
        List<String> list = Collections.synchronizedList(list1);
        ArrayListTask at = new ArrayListTask(list);
        for (int i = 0; i < 50; i++) {
            new Thread(at).start();
        }
        Thread.sleep(3000);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("length : " + list.size());
    }

    /**
     * ArrayList添加删除行不一定比LinkedList差
     */
    private static void listAddDeletePerformance() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20000000; i++) {
            list.add(i + " \t");
        }
        list.remove(19000000);

        System.out.println();

        LinkedList<String> link = new LinkedList<>();
        for (int i = 0; i < 20000000; i++) {
            link.add(i + "\t");
        }
        link.remove(19000000);
    }

    private static void capacityExpansionPerformance() {
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("java");
        list.add("php"); // modCount = 3
        // 第一次扩容为 10
        // 每次扩容都是原容量的1.5倍
        // 频繁扩容
        for (int i = 0; i < 1000000; i++) {
            list.add(i + " \t");
        }

        // 一次性初始化大容量集合,避免频繁扩容
        ArrayList<String> list1 = new ArrayList<>(100000);
        // 第一次扩容为 10
        // 每次扩容都是原容量的1.5倍
        // 频繁扩容
        for (int i = 0; i < 1000000; i++) {
            list1.add(i + " \t");
        }


    }

    private static void listIsEmpty() {
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("java");
        list.add("php"); // modCount = 3
        /**
         * size == 0
         */
        System.out.println(list.isEmpty());
    }

    private static void listContains() {
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("java");
        list.add("php"); // modCount = 3
        /**
         * contains
         * indexOf >= 0
         *
         * 循环并equals，算法为简单查找
         */
        System.out.println(list.contains("java"));
        System.out.println(list.contains(null));
    }

    private static void listClear() {
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("java");
        list.add("php"); // modCount = 3
        System.out.println(list);
        list.clear();
        System.out.println(list);
    }

    private static void itrDefaultRemove() {
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("java");
        list.add("php"); // modCount = 3
        Iterator<String> itr = list.iterator();

        while (itr.hasNext()) {
            String s = itr.next();
            if ("php".equals(s)) {
                itr.remove(); // 迭代器的remove方法
            }
        }
        /*
        迭代器自带remove
        itr.remove():
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            删除之前检查
            checkForComodification();

            try {
                根据索引删除元素:
                modCount++
                size--
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                更新了预期修改次数为实际修改次数
                expectedModCount = modCount;
                之后不会出现expectedModCount != modCount
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

         */
        System.out.println(list);

    }

    /**
     * why ConcurrentModificationException:
     *     you broke the rule of not modifying a Collection during iteration
     * Uses the Structural modifications are changing the size of the list, which may affect
     * the progress of iteration and may yield incorrect results
     */
    private static void listConcurrentModificationException1() {
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("java");
        list.add("php"); // modCount = 3
        // 集合调用add时，实际修改次数变量值 会自增1
        // ensureExplicitCapacity: modCount++

        // 获取迭代器时，集合只执行一次将集合实际修改次数赋给预期修改集合的次数
        Iterator<String> itr = list.iterator();
        /**
         * Itr:
         *  expectedModCount = modCount 3
         *
         * hashNext: cursor != size
         *
         */
        while (itr.hasNext()) {
            /**
             * next: checkForComodification
             *     cursor ++
             *     modCount != expectedModCount -> ConcurrentModification
             *     cursor >= elementData.length -> ConcurrentModification
             *
             */
            String s = itr.next();
            if ("java".equals(s)) {
//                System.out.println(s);
                /**
                 * remove:
                 * public boolean remove(Object o) {
                 *    if (o == null) {
                 *        for (int index = 0; index < size; index++)
                 *            if (elementData[index] == null) {
                 *                fastRemove(index);
                 *                return true;
                 *            }
                 *       } else {
                 *          遍历集合,每一个比对，如果相等，则删除
                 *          for (int index = 0; index < size; index++)
                 *              if (o.equals(elementData[index])) {
                 *                  fastRemove(index);
                 *              return true;
                 *          }
                 *      }
                 *      return false;
                 *  }
                 *
                 * fastRemove(index)
                 *     集合实际修改次数：modCount++   4
                 *     但是集合预期修改次数： expectedModCount没变 3
                 * elementData[--size] = null 置null，让gc尽快回收
                 *
                 * 删除成功之后并不会抛出异常
                 * add也会出现
                 *
                 * 在下次next()调用时，会checkForModification，抛出并发修改异常
                 */
//                list.remove("hello");
                list.remove("java");
                // 集合删除时，也会针对实际修改次数的变量进行自增操作
//                list.add("javascript")
//          如果是hello,也会有并发修改异常

//          如果删除的是java: 集合的倒数第二个元素
//              cursor++ && size--
//              hasNext中的判断 cursor != size 就为false，也就是没有下一个了，
//              就不会进入next方法，就不会校验modCount != expectedModCount
//              就没有并发修改异常

            }
        }
        System.out.println(list);
    }

    private static void listIterator() {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("A", 4));
        list.add(new Student("B", 4));
        list.add(new Student("C", 4));
        list.add(new Student("D", 4));

//        for ( Student sut : list) {
//            System.out.println(sut);
//        }
        /**
         * public Iterator<E> iterator() {
         *     return new Itr();
         * }
         *
         */
        Iterator<Student> itr = list.iterator();
        while (itr.hasNext()) {
            /**
             * next():
             *     并发修改异常 检查
             */
            Student stu = itr.next();
            System.out.println(stu);
        }
    }

    private static void listToString() {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("A", 4));
        list.add(new Student("B", 4));
        list.add(new Student("C", 4));
        list.add(new Student("D", 4));

        /**
         * AbstractCollection:
         *     public String toString() {
         *         Iterator<E> it = iterator();
         *         if (! it.hasNext())
         *             return "[]";
         *
         *         StringBuilder sb = new StringBuilder();
         *         sb.append('[');
         *         for (;;) {
         *             E e = it.next();
         *             sb.append(e == this ? "(this Collection)" : e);
         *             if (! it.hasNext())
         *                 return sb.append(']').toString();
         *             sb.append(',').append(' ');
         *         }
         *     }
         */
        String s = list.toString();
        System.out.println(s);
    }

    private static void listGet() {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("A", 4));
        list.add(new Student("B", 4));
        list.add(new Student("C", 4));
        list.add(new Student("D", 4));

        /**
         * rangeCheck(int index)
         *
         * elementData[index]
         * return
         */
        System.out.println(list.get(2));

    }

    private static void listSet() {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("A", 4));
        list.add(new Student("B", 4));
        list.add(new Student("C", 4));
        list.add(new Student("D", 4));
        showList(list);
        /**
         * phase1.ds.set(int index, value)
         *    public E phase1.ds.set(int index, E element) {
         *         rangeCheck(index);
         *
         *         E oldValue = elementData(index);
         *         elementData[index] = element;
         *         return oldValue;
         *     }
         *  返回 被替换的元素
         */
        list.set(1, new Student("b", 44));
        showList(list);
    }

    private static void showList(ArrayList<Student> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println();
    }

    private static void arrayCopyTest() {
        String[] src = {"a", "b", "c"};
        String[] dst = {"A", "B", null, null, null, null};

        int srcSize = src.length;
        int dstSize = 0;
        for (int i = 0; i < dst.length; i++) {
            if (dst[i] != null)
                ++dstSize;
        }

        int index = 1;
        // 集合真实长度 - 要存的索引位置
        int numMoved = dstSize - 1;
        System.out.println(numMoved);
        if (numMoved > 0) {
            System.arraycopy(dst, index, dst, srcSize + index, numMoved);
        }
        System.out.println(Arrays.toString(dst));
        System.out.println();
        System.arraycopy(src, 0, dst, 1, srcSize);
        System.out.println(Arrays.toString(dst));

    }

    private static void arraycopy() {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("A", 4));
        list.add(new Student("B", 4));
        list.add(new Student("C", 4));
        list.add(new Student("D", 4));
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println();
        ArrayList<Student> list1 = new ArrayList<>();
        list1.add(new Student("a", 3));
        list1.add(new Student("b", 3));
        list1.add(new Student("c", 3));
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }
        System.out.println();
        /**
         * public boolean addAll(int index, Collection<? extends E> c) {
         *     rangeCheckForAdd(index);
         *
         *     Object[] a = c.toArray();
         *     int numNew = a.length;
         *     ensureCapacityInternal(size + numNew);  // Increments modCount
         *
         *     int numMoved = size - index; 要移动的元素个数
         *     if (numMoved > 0)
         *         // 此处是移动元素
         *         System.arraycopy(elementData, index, elementData, index + numNew, numMoved);
         *     // 此处是 数组元素赋值
         *     System.arraycopy(a, 0, elementData, index, numNew);
         *     size += numNew;
         *     return numNew != 0;
         * }
         */
        list1.addAll(1, list);
        list.get(0).setName("AA");
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }
    }

    /**
     * addAll(Collection<? extends E> c)
     * 按照指定集合Iterator返回的顺序将制定集合的所有元素追加到此列表末尾
     */
    private static void addAll0() {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        /**
         * public boolean addAll(Collection<? extends E> c) {
         *     Object[] a = c.toArray();
         *     int numNew = a.length;
         *     ensureCapacityInternal(size + numNew);  // Increments modCount
         *     System.arraycopy(a, 0, elementData, size, numNew);
         *     size += numNew;
         *     return numNew != 0;
         * }
         *
         * System.arraycopy()
         */
        ArrayList<String> list1 = new ArrayList<>();
        list1.addAll(list);
        System.out.println(list);
        System.out.println(list1);
    }

    private static void addWithArgs() {
        ArrayList<String> list = new ArrayList<>();
        /**
         * public boolean add(E e) {
         *     ensureCapacityInternal(size + 1);  // Increments modCount!!
         *     elementData[size++] = e;
         *     return true;
         * }
         *
         * private void ensureExplicitCapacity(int minCapacity) {
         *     modCount++;
         *
         *     // overflow-conscious code
         *     if (minCapacity - elementData.length > 0)  // 容量不够时，才会进行扩容
         *         grow(minCapacity);
         * }
         *
         * 扩容算法: 原容量的1.5倍
         * int newCapacity = oldCapacity + (oldCapacity >> 1);
         *
         * private void grow(int minCapacity) {
         *     // overflow-conscious code
         *     int oldCapacity = elementData.length;
         *     int newCapacity = oldCapacity + (oldCapacity >> 1);
         *     if (newCapacity - minCapacity < 0)
         *         newCapacity = minCapacity;
         *     if (newCapacity - MAX_ARRAY_SIZE > 0)
         *         newCapacity = hugeCapacity(minCapacity);
         *        // minCapacity is usually close to size, so this is a win:
         *        elementData = Arrays.copyOf(elementData, newCapacity);
         * }
         *
         *                     数据源     源偏移    数据目的地   目标偏移    拷贝数量
         * System.arraycopy(elementData, index, elementData, index + 1, size - index)
         *
         */
        list.add("a"); // 追加
        list.add("c"); // 追加
        list.add("d"); // 追加

//        list.add(1, "b"); // 容量不够时才会扩容
        list.add(3, "e"); // 添加时，index不能大于 大小size, 否则IndexOutOfBoundsException, 不是ArrayIndexOutOfBoundsException
        System.out.println(list);
    }

//    private static JdbcTemplate jt = new JdbcTemplate(JdbcUtils.getResultSetValue());
    // ArrayList源码分析
    private static void initWithoutArgs() {
        // 无参构造
        ArrayList<String> listWithoutArgs = new ArrayList<>();
        /**
         *  // transient：序列化时某些敏感信息比如密码不希望被写出，可以在序列化之前使用transient，
         *  // jvm在序列化之前自动忽略该字段
         *  // elementData为集合真正存储数据的容器
         *  transient Object[] elementData;
         *
         * // 集合真正存储数据的容器，默认长度为0
         * private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
         *
         * public ArrayList() {
         *     this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA; Object类型的数组
         * }
         */

        // 有参构造
        ArrayList<String> listWithArgs = new ArrayList<>(5);

        // 构造一个包含指定集合的元素列表，按照他们由集合的迭代器返回的顺序
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("a", 1));
        list.add(new Student("b", 2));
        list.add(new Student("c", 3));


        /*
         * public ArrayList(Collection<? extends E> c) {
         *     elementData = c.toArray();
         *     if ((size = elementData.length) != 0) {
         *         // c.toArray might (incorrectly) not return Object[] (see 6260652)
         *         if (elementData.getClass() != Object[].class)
         *             elementData = Arrays.copyOf(elementData, size, Object[].class);
         *         } else {
         *             // replace with empty array.
         *             this.elementData = EMPTY_ELEMENTDATA;
         *         }
         *     }
         *
         * public Object[] toArray() {
         *     return Arrays.copyOf(elementData, size);
         * }
         *
         * public static <T> T[] copyOf(T[] original, int newLength) {
         *     return (T[]) copyOf(original, newLength, original.getClass());
         * }
         *
         * public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
         *     @SuppressWarnings("unchecked")
         *     T[] copy = ((Object)newType == (Object)Object[].class)
         *         ? (T[]) new Object[newLength]
         *         : (T[]) Array.newInstance(newType.getComponentType(), newLength);
         *     System.arraycopy(original, 0, copy, 0,
         *                      Math.min(original.length, newLength));
         *     return copy;
         * }
         */
//        System.out.println(Object[].class);
        ArrayList<Student> listWithCollection = new ArrayList<>(list);
        list.set(0, new Student("A", 111));
//        System.out.println(list);
        // 以下输出不受影响
        for (Student s : listWithCollection) {
            System.out.println(s);
        }
    }

    /**
     * 随机访问： 通过index下标访问，类似数组
     * 顺序访问：类似链表，迭代器遍历
     * List都定义了迭代器访问 和 下标访问方法，但是性能差异很明显
     */
    private static void randomAndSequence() {
        /*
         ArrayList: 为数组结构，访问具有常量时间
         LinkedList是链表结构

         判断时用 instanceof RandomAccess
         是使用 随机遍历
         否则使用 顺序遍历

         随机访问分为两步：
            根据index查找Node，通常是for寻找index对应的Node，然后返回Node中的元素
         */
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i + " a");
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        System.out.println("random: " + (System.currentTimeMillis() - start));  // 128

        start = System.currentTimeMillis();
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            itr.next();
        }
        System.out.println("order: " + (System.currentTimeMillis() - start)); // 2

    }

    private static void randomaccess() {
        // RandomAccess: 标名支持快速（恒定时间）随机访问
        // 该接口主要是 允许通用算法更改其行为，以便在随机访问列表或顺序访问时提供良好的性能
        // 用于操纵访问列表的最佳算法 可以在应用于顺序访问列表时产生二次行为
        // 随机访问和顺序访问之间的区别如果是模糊的，例如一些List实现渐进的线性访问时间，
        // 如果实践中获得巨大但是恒定的时间。这样的List应该实现这个接口
        /**
         * 使用RandomAccess表明: 使用fori循环通常比foreach循环更快
         * // 以下是随机访问
         * for (int i = 0; i < list.size(); i++) {
         * }
         * // 以下是顺序访问
         * for (Iterator i = list.iterator(); i.hasNext()
         */
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5000000; i++) {
            list.add(i + " a");
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        System.out.println("random: " + (System.currentTimeMillis() - start));  // 5

        start = System.currentTimeMillis();
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            itr.next();
        }
        System.out.println("order: " + (System.currentTimeMillis() - start)); // 9

    }

    private static void shallowcopy1() throws CloneNotSupportedException {
        Skill skill = new Skill("标枪");
        Student stu1 = new Student("A", 1);
        stu1.setSkill(skill);

        Student clone = stu1.clone();
        System.out.println(stu1 == clone);
        System.out.println(stu1);
        System.out.println(clone);
        System.out.println("==========================");
        stu1.setName("b");
        skill.setSkillName("按实际的快乐飞就是");
        System.out.println(stu1);
        System.out.println(clone);
    }

    private static void shadowcopy() throws CloneNotSupportedException {
        // 浅拷贝 及其  局限性
        // 浅拷贝：
        //     基本数据类型可以打到完全复制，但是引用数据则不可以
        //     skill仅仅是拷贝了一份引用
        Skill skill = new Skill("标枪");
        Student stu1 = new Student("A", 1);
        stu1.setSkill(skill);

        Student clone = stu1.clone();
        System.out.println(stu1 == clone);
        System.out.println(stu1);
        System.out.println(clone);
        System.out.println("==========================");
        stu1.setAge(2);
        skill.setSkillName("按实际的快乐飞就是");
        System.out.println(stu1);
        System.out.println(clone);
    }

    private static void demo6() {
        Student stu1 = new Student("A", 1);
        Student stu2 = new Student();
        stu2.setName(stu1.getName());
        stu2.setAge(stu1.getAge());

        System.out.println(stu1 == stu2);
//        System.out.println(stu1.hashCode());
//        System.out.println(stu2.hashCode()); // hashcode不一致
//        System.out.println(stu1);
//        System.out.println(stu2);

        stu1.setName("B");
        System.out.println(stu1);
        System.out.println(stu2);

    }

    /**
     * cloneable实现object.clone()，该方法对于该类的实例进行字段复制是合法的
     *
     * Cloneable接口，根据已有对象，创造一份完全新的拷贝
     */
    private static void objclone() {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
//        System.out.println(list.hashCode());
        Object o = list.clone();
        System.out.println("==? " + (list == o)); // false
        System.out.println(o.hashCode());
    }

    private static void serialize3() throws IOException, ClassNotFoundException {
        Student s1 = new Student("aaa", 1);
        Student s2 = new Student("bbb", 1);
        Student s3 = new Student("ccc", 1);
        Student s4 = new Student("ddd", 1);
        // 反序列化集合
        ArrayList<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("files/students.txt"));
        oos.writeObject(students);
        oos.close();
        System.out.println("list written");

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("files/students.txt"));
        ArrayList<Student> students1 = (ArrayList<Student>) ois.readObject();
        for (Student student : students1) {
            System.out.println(student.toString());
        }

    }

    private static void serialize1() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("files/students.txt"));
        Student s1 = (Student) ois.readObject();
        ois.close();
        System.out.println("read");
        System.out.println(s1);
    }

    private static void serialize() throws IOException {
        // serializable：
        // 序列化： 将对象的数据写入到文件（写对象）
        // 反序列化：将文件中的对象数据读取出来（读对象）

        // 不实现serializable接口的类将不会使状态序列化或反序列化。
        // 可序列化类的所有子类都是可序列化的，序列化接口仅表示可序列化的语义
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("files/students.txt"));
        Student s1 = new Student("Allen", 20);
        oos.writeObject(s1);
        oos.close();
    }

    /**
     * 1. 线程安全性：线程不安全,比如ArrayList添加的顺序为 Object[size]存放被添加元素，再次将size += 1
     *    多个线程并发访问和操作统一数据且执行结果与访问发生的特定顺序有关，称为竞争条件。
     *    为了防止竞争条件，需要确保一段时间内只有一个线程能操作变量该数据。
     *    为实现这种保证，要求一定形式的线程间同步。
     *
     *    ArrayList的实现不是同步的
     *    多线程环境下使用ArrayList，使用synchronized关键字，也可使用Collections静态方法synchronizedList调用
     *
     * 2. 每个ArrayList实例都有一个容量，该容量是指用来存储元素列表元素的大小。初始值为10。
     *    每次添加时，都会检查是否需要进行扩容个操作，扩容操作带来数据向新数组的重新拷贝
     *
     */

    /**
     * ArrayList的底层数据结构是一个数组，数组元素为Object类型，对ArrayList的操作都基于数组
     * ArrayList就是一个 以数组形式实现的集合
     *
     *  数组：如果已知数组长度为５，追加一个元素必须copy数组
     */
    public static void demo1() {
        int[] arr = new int[4];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;
        System.out.println(arr);
    }
}
