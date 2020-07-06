package ds.graph;

import ds.uf.generic.UnionFindGeneric2;
import edu.princeton.cs.algs4.StdOut;
import java.util.*;

public class ListGraph3<V, W> extends Graph<V, W> {

    HashMap<V, Vertex<V, W>> vertices = new HashMap<>();
    HashSet<Edge<V, W>> edges = new HashSet<>();

    public ListGraph3() {
        super(null);
    }

    private static class Vertex<V, W> {
        V value;
        HashSet<Edge<V, W>> inEdges  = new HashSet<>();
        HashSet<Edge<V, W>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return value == null ? 0 : value.hashCode();
        }

        @Override
        public String toString() {
            return value + "";
        }
    }

    private static class Edge<V, W> {
        Vertex<V, W> from;
        Vertex<V, W> to;
        W weight;

        public Edge(W weight) {
            this.weight = weight;
        }

        public Edge(Vertex<V, W> from, Vertex<V, W> to) {
            this.from = from;
            this.to   = to;
            this.weight = null;
        }

        public Edge(Vertex<V, W> fromVertex, Vertex<V, W> toVertex, W weight) {
            this.from = fromVertex;
            this.to   = toVertex;
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
            Edge<?, ?> edge = (Edge<?, ?>) o;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }

        public EdgeInfo<V, W> info() {
            return new EdgeInfo(from, to, weight);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            sb.append("[");
            sb.append(from);
            sb.append(" -> ");
            sb.append(to);
            sb.append("] ");
            sb.append(weight);
            return sb.toString();
        }
    }

    public ListGraph3(WeightManager<W> weightManager) {
        super(weightManager);
    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int vertexSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) {
            return;
        }
        Vertex<V, W> newVertex = new Vertex<>(v);
        vertices.put(v, newVertex);
    }

    @Override
    public void addEdge(V from, V to, W weight) {
        Vertex<V, W> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }
        Vertex<V, W> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }
        Edge<V, W> newEdge = new Edge<>(fromVertex, toVertex, weight);
        if (edges.remove(newEdge)) {
            fromVertex.outEdges.remove(newEdge);
            toVertex.inEdges.remove(newEdge);
        }
        edges.add(newEdge);
        fromVertex.outEdges.add(newEdge);
        toVertex.inEdges.add(newEdge);
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void removeVertex(V v) {
        Vertex<V, W> vertexTobeRemoved = vertices.remove(v);
        if (vertexTobeRemoved == null) {
            return;
        }
        // 删除vertex 的 inEdges
        Iterator<Edge<V, W>> itrIn = vertexTobeRemoved.inEdges.iterator();
        while (itrIn.hasNext()) {
            Edge<V, W> edge = itrIn.next();
            edge.from.outEdges.remove(edge);
            itrIn.remove();
            edges.remove(edge);
        }
        // 删除vertex 的 outEdges
        Iterator<Edge<V, W>> itrOut = vertexTobeRemoved.outEdges.iterator();
        while (itrOut.hasNext()) {
            Edge<V, W> edge = itrOut.next();
            edge.to.inEdges.remove(edge);
            itrOut.remove();
            edges.remove(edge);
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, W> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            return;
        }
        Vertex<V, W> toVertex = vertices.get(to);
        if (toVertex == null) {
            return;
        }
        Edge<V, W> edge = new Edge<>(fromVertex, toVertex);
        fromVertex.outEdges.remove(edge);
        toVertex.inEdges.remove(edge);
        edges.remove(edge);
    }

    @Override
    public void bfs(V begin, VertexVisitor<V> visitor) {
        bfsByQueue(begin, visitor);
        StdOut.println("----------------------------------");
        Vertex<V, W> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return;
        }
        bfsRecursively(beginVertex, visitor, new HashSet<>());
    }

    private void bfsRecursively(Vertex<V, W> beginVertex, VertexVisitor<V> visitor, HashSet<Vertex<V, W>> visited) {
        if (visited.contains(beginVertex)) {
            return;
        }
        visitor.visit(beginVertex.value);
        visited.add(beginVertex);
        for (Edge<V, W> vwEdge : beginVertex.outEdges) {
            bfsRecursively(vwEdge.to, visitor, visited);
        }
    }

    private void bfsByQueue(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) {
            return;
        }
        Vertex<V, W> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return;
        }
        Queue<Vertex<V, W>> queue = new LinkedList<>();
        HashSet<Vertex<V, W>> visited = new HashSet<>();
        queue.offer(beginVertex);
        visited.add(beginVertex);
        while (! queue.isEmpty()) {
            Vertex<V, W> vertex = queue.poll();
            if (visitor.visit(vertex.value)) {
                return;
            }
            for (Edge<V, W> vwEdge : vertex.outEdges) {
                Vertex<V, W> to = vwEdge.to;
                if (visited.contains(to)) {
                    continue;
                }
                queue.offer(to);
                visited.add(to);
            }
        }
    }

    @Override
    public void dfs(V begin, VertexVisitor<V> visitor) {
        Vertex<V, W> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return;
        }
        dfsRecursively(beginVertex, visitor, new HashSet<>());
        StdOut.println("--------------------------------");
        dfsByStack(beginVertex, visitor, new HashSet<>());
    }

    private void dfsRecursively(Vertex<V, W> beginVertex, VertexVisitor<V> visitor, HashSet<Vertex<V, W>> visited) {
        visitor.visit(beginVertex.value);
        visited.add(beginVertex);
        for (Edge<V, W> vwEdge : beginVertex.outEdges) {
            Vertex<V, W> to = vwEdge.to;
            if (visited.contains(to)) {
                continue;
            }
            dfsRecursively(to, visitor, visited);
        }
    }

    private void dfsByStack(Vertex<V, W> beginVertex, VertexVisitor<V> visitor, HashSet<Vertex<V, W>> visited) {
        Stack<Vertex<V, W>> stack = new Stack<>();
        stack.push(beginVertex);
        visited.add(beginVertex);
        if (visitor.visit(beginVertex.value)) {
            return;
        }
        while (! stack.isEmpty()) {
            Vertex<V, W> vertex = stack.pop();
            for (Edge<V, W> edge : vertex.outEdges) {

            }
        }
    }

    @Override
    public HashSet<EdgeInfo<V, W>> mst(boolean prim) {
        if (prim) {
            return prim();
        } else {
            return kruskal();
        }
    }

    private Comparator<Edge<V, W>> edgeComparator = (Edge<V,W> a, Edge<V, W> b)
            -> weightManager.compare(a.weight, b.weight);

    private HashSet<EdgeInfo<V, W>> prim() {
        Iterator<Vertex<V, W>> itr = vertices.values().iterator();
        if (! itr.hasNext()) {
            return null;
        }
        HashSet<EdgeInfo<V, W>> edgeInfos = new HashSet<>();
        HashSet<Vertex<V, W>> visitedVert = new HashSet<>();
        Vertex<V, W> beginVertex = itr.next();
        visitedVert.add(beginVertex);
        
        int tmp = 0;
        MinHeap<Edge<V, W>> heap = new MinHeap<>(beginVertex.outEdges, edgeComparator);
        int edgeSize = vertices.size() - 1;
        // 连通图的极小联通子图，含有图中全部 vertices.size()个顶点，但是恰好只有 n-1条边
        // 可优化掉近一半的的循环次数
        while (! heap.isEmpty() && edgeInfos.size() < edgeSize) {
            Edge<V, W> edge = heap.remove();
            tmp++;
            if (visitedVert.contains(edge.to)) {
                continue;
            }
            visitedVert.add(edge.to);
            edgeInfos.add(edge.info());
            heap.addAll(edge.to.outEdges);
        }
//        StdOut.println(vertices.size());
        StdOut.println("prim: " + tmp);
        return edgeInfos;
    }

    /**
     *  按照权重顺序（有小到大）将边加入生成树中，知道含有 vertices.size() - 1条边为止
     */
    private HashSet<EdgeInfo<V, W>> kruskal() {
        if (vertices.size() == 0) {
            return null;
        }
        UnionFindGeneric2<Vertex<V, W>> uf = new UnionFindGeneric2<>();
        vertices.forEach((V key, Vertex<V, W> vertex) -> {
            uf.add(vertex);
        });
        HashSet<EdgeInfo<V, W>> edgeInfos = new HashSet<>();
        int tmp = 0;
        MinHeap<Edge<V, W>> heap = new MinHeap<>(edges, edgeComparator);
        int edgeSize = vertices.size() - 1;
        while (! heap.isEmpty() && edgeInfos.size() < edgeSize) {
            Edge<V, W> edge = heap.remove();
            tmp ++;
            if (uf.isSame(edge.to, edge.from)) {
                continue;
            }
            edgeInfos.add(edge.info());
            uf.union(edge.to, edge.from);
        }
        StdOut.println("kruskal: " + tmp);
        StdOut.println("vertices: " + vertices.size());
        return edgeInfos;
    }

    @Override
    public String toString() {
        return "vertices: " + vertices + "\n" + "edges   : " + edges + '}';
    }

    @Override
    public Set<EdgeInfo<Object, Double>> print() {
        StdOut.println(this.toString());
        return null;
    }

    @Override
    public ArrayList<V> topoSort() {
        ArrayList<V> list = new ArrayList<>();
        Queue<Vertex<V, W>> queue = new LinkedList<>();
        HashMap<Vertex<V, W>, Integer> inDegrees = new HashMap<>();
        vertices.forEach((V key, Vertex<V, W> vertex) -> {
            int inDegree = vertex.inEdges.size();
            if (inDegree == 0) {
                queue.offer(vertex);
            } else {
                inDegrees.put(vertex, inDegree);
            }
        });
        int tmp = 0;
        while (! queue.isEmpty()) {
            Vertex<V, W> vertex = queue.poll();
            list.add(vertex.value);
            for (Edge<V, W> edge : vertex.outEdges) {
                tmp ++;
                Vertex<V, W> to = edge.to;
                int inDge = inDegrees.get(to) - 1;
                if (inDge == 0) {
                    queue.offer(to);
                } else {
                    inDegrees.put(to, inDge);
                }
            }
        }
        StdOut.println("topo: " + tmp);
        return list;
    }


    @Override
    public Map<V, W> dijkstra1(V begin) {
        return null;
    }

    @Override
    public Map<V, PathInfo<V, W>> dijkstra2(V begin) {
        return null;
    }

    @Override
    public Map<V, PathInfo<V, W>> bellmanFord(V begin) {
        return null;
    }

    @Override
    public Map<V, Map<V, PathInfo<V, W>>> floyd() {
        return null;
    }

}
