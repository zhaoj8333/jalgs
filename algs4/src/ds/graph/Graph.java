package ds.graph;

public interface Graph<V, E> {
    int edgesSize();
    int vertexSize();

    void addVertex(V v);
    void addEdge(V from, V to, E weight);
    void addEdge(V from, V to);

    void removeVertex(V v);
}
