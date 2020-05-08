/*
   用线性搜索的话，数组和链表都是O(n)的复杂度来找到插入的位置，
   为什么链表会慢呢？在理论上可能是一样块的，

   但在现实中不是这样的。原因是因为链表不是cache friendly的。
   你在访问线性访问数组的时候，因为局部性原理，系统会把之后连续的内存都读过来，
   所以你之后访问数组元素可能都在 L1 cache 里面，但链表不是这样的，
   谁都不知道它的next指针指到哪块内存，导致访问每个节点都对应一次memory而不是cache的访问。


    gc root对象：
        1 > 被栈指针（局部变量）指向的对象

        LinkedList<String> list = new LinkedList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");

        list为一个gc root对象


    静态链表：
        一般的链表依赖于指针（引用）实现
        但某些语言如BASIC，FORTRAN语言没有指针
        实现链表时通过数组模拟链表

        每个数组存放两个值： 分别是值，下一个元素的索引


   ArrayList优化思路：
        使用int first记录首元素位置，从头删除时first往后移动
        头部添加时，first自尾部向前移动，通过取模访问

        中间插入或删除，哪边短则移动哪边的元素

 */
package ds.collection.linkedlist;