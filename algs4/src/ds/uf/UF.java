package ds.uf;

public interface UF {
    int find(int index); // 查找index所属的根节点
    void union(int index1, int index2);   // 合并index1， index2所属的集合
    boolean isSame(int index1, int index2); // 检查index1,index2是否属于同一集合
}
