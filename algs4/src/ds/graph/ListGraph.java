package ds.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ListGraph<V, E> implements Graph<V, E> {
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();

    @Override
    public int edgesSize() {
        return 0;
    }

    @Override
    public int vertexSize() {
        return 0;
    }

    @Override
    public void addVertex(V v) {

    }

    @Override
    public void addEdge(V from, V to, E weight) {

    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }
    @Override
    public void removeVertex(V v) {

    }

    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();   // 进来的边
        Set<Edge<V, E>> outEdges = new HashSet<>();  // 出去的边
    }

    private static class Edge<V, E> {
        Vertex<V, E> from; // 边起点
        Vertex<V, E> to;   // 边终点
        E weight;
    }
}
