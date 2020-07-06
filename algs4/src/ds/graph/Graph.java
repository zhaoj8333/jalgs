package ds.graph;

import java.nio.file.Path;
import java.util.*;

/**
 * @author allen
 */
public abstract class Graph<V, W> {
    protected WeightManager<W> weightManager;

    public Graph(WeightManager<W> weightManager) {
        this.weightManager = weightManager;
    }

    public abstract int edgesSize();
    public abstract int vertexSize();

    public abstract void addVertex(V v);
    public abstract void addEdge(V from, V to, W weight);
    public abstract void addEdge(V from, V to);

    public abstract void removeVertex(V v);
    public abstract void removeEdge(V from, V to);

    public abstract void bfs(V begin, VertexVisitor<V> visitor);
    public abstract void dfs(V begin, VertexVisitor<V> visitor);

    public abstract Set<EdgeInfo<V, W>> mst(boolean prim);

    public abstract Set<EdgeInfo<Object, Double>> print();

    public abstract List<V> topoSort();

    /**
     * 单源路径算法 简单版
     * @param begin 起点
     * @return 起点begin到其他顶点的最短路径
     */
    public abstract Map<V, W> dijkstra1(V begin);

    // 复杂版
    public abstract Map<V, PathInfo<V, W>> dijkstra2(V begin);

    public abstract Map<V, PathInfo<V, W>> bellmanFord(V begin);

    public abstract Map<V, Map<V, PathInfo<V, W>>> floyd();

    public static class PathInfo<V, W> {
        protected W weight;       // 总权值
        protected List<EdgeInfo<V, W>> edgeInfos = new LinkedList<>();

        public PathInfo() {
        }

        public PathInfo(W weight) {
            this.weight = weight;
        }

        public W getWeight() {
            return weight;
        }

        public void setWeight(W weight) {
            this.weight = weight;
        }

        public List<EdgeInfo<V, W>> getEdgeInfos() {
            return edgeInfos;
        }

        public void setEdgeInfos(List<EdgeInfo<V, W>> edgeInfos) {
            this.edgeInfos = edgeInfos;
        }

        @Override
        public String toString() {
            return "PathInfo {" + "weight=" + weight + "  edgeInfos=" + edgeInfos + '}';
        }
    }

    public interface WeightManager<W> {
        int compare(W a, W b);
        W add(W a, W b);
        W zero();
    }

    interface VertexVisitor<V> {
        boolean visit(V v);
    }

    static class EdgeInfo<V, W> {
        private V from;
        private V to;
        private W weight;

        public V getFrom() {
            return from;
        }

        public void setFrom(V from) {
            this.from = from;
        }

        public V getTo() {
            return to;
        }

        public void setTo(V to) {
            this.to = to;
        }

        public W getWeight() {
            return weight;
        }

        public void setWeight(W weight) {
            this.weight = weight;
        }

        public EdgeInfo(V from, V to, W weight) {
            this.from = from;
            this.to   = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            EdgeInfo<?, ?> edgeInfo = (EdgeInfo<?, ?>) o;
            return Objects.equals(from, edgeInfo.from) && Objects.equals(to, edgeInfo.to);
        }

        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }

        @Override
        public String toString() {
            return " " + from + "->" + to + "(" + weight + ')';
        }
    }
}
