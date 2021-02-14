package phase1.ds.uf.generic;

import java.util.Map;

public class UnionFindGeneric2<V> extends UFG<V> {
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        Map<V, Node<V>> nodes = this.getNodes();

        nodes.forEach((k, v) -> {
            ret.append(k);
            ret.append("->");
            ret.append(v);
            ret.append("\n");
        });

        return ret.toString();
    }

    public static void main(String[] args) {
        UnionFindGeneric2<Student> uf2 = new UnionFindGeneric2<>();
        uf2.add(new Student("a", 1, true));
        uf2.add(new Student("b", 2, false));
        uf2.add(new Student("c", 3, true));
        uf2.add(new Student("d", 4, false));


    }
}
