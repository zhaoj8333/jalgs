package phase1.ds.tree;

/*
    哈弗曼编码： 现代压缩算法的基础

    哈夫曼树： 最优二叉树
        1. 先计算每个字母出现的频率（权值，可以直接使用次数）
        2. 以权值作为根节点构建n棵二叉树，组成森林
        3. 在森林中选出2个根节点最小的树合并，作为一棵新树的左右子树，且新树的根节点为其左右子树根节点之和
        4. 从森林中删除所选取的2棵树，并将新树加入森林
        5. 重复3，4步骤，直到森林只剩下一棵树为止，该树即为哈夫曼树

        所有编码保障谁都不是谁的前缀后缀，
        出现次数最多的编码最短
        每一个元素都是叶子节点，编码的长度就是根节点到该叶子节点的长度

        n个权值构建出来的哈弗曼树拥有n个叶子节点
        每个哈夫曼编码都不是另一个哈弗曼编码的前缀
        哈弗曼树是带权值路径长短最短的树，权值较大的节点离根节点较近
        带权路径长度：树中所有的叶子节点的权值乘上其到根节点的路径长度与最终的哈夫曼编码总长度成正比关系
 */
@SuppressWarnings("all")
public class HuffmanTree {
}
