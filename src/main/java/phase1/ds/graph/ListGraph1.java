package phase1.ds.graph;

import phase1.ds.tree.uf.generic.UnionFindGeneric2;
import java.util.*;

/**
 * @author allen
 */
public class ListGraph1<V, W> extends Graph<V, W> {
    private Map<V, Vertex<V, W>> vertices = new HashMap<>();
    private Set<Edge<V, W>> edges = new HashSet<>();

    public ListGraph1() {
        super(null);
    }

    public ListGraph1(WeightManager<W> weightManager) {
        super(weightManager);
    }

    /**
     *  V与vertex是一对一的关系
     */
    private static class Vertex<V, W> {
        V value;
        Set<Edge<V, W>> inEdges  = new HashSet<>();   // 进来的边
        Set<Edge<V, W>> outEdges = new HashSet<>();  // 出去的边

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
            return value == null ? "null" : value.toString();
        }
    }

    private static class Edge<V, W> {
        Vertex<V, W> from; // 边起点
        Vertex<V, W> to;   // 边终点
        W weight;

        Edge(Vertex<V, W> from, Vertex<V, W> to, W weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        Edge(Vertex<V, W> from, Vertex<V, W> to) {
            this.from = from;
            this.to = to;
        }

        EdgeInfo<V, W> info() {
            return new EdgeInfo<>(from.value, to.value, weight);
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

        @Override
        public String toString() {
            return from + "->" + to + " (" + weight + ')';
        }
    }

    @Override
    public Set<EdgeInfo<Object, Double>> print() {
        System.out.print("vertices: ");
        vertices.forEach((V key, Vertex<V, W> vertex)->{
            System.out.print(key + " ");
        });
        System.out.println();
        System.out.println();
        System.out.println("edges: ");
        edges.forEach(System.out::println);
        return null;
    }

    @Override
    public List<V> topoSort() {
        List<V> list = new ArrayList<>();
        Queue<Vertex<V, W>> q = new LinkedList<>();
        Map<Vertex<V, W>, Integer> ins = new HashMap<>();

        vertices.forEach((V v, Vertex<V, W> vertex) -> {
            int inCount = vertex.inEdges.size();
            if (inCount == 0) {
                q.offer(vertex);
            } else {
                ins.put(vertex, inCount);
            }
        });

        while (! q.isEmpty()) {
            Vertex<V, W> vertex = q.poll();
            list.add(vertex.value);
            for (Edge<V, W> edge : vertex.outEdges) {
                int toIn = ins.get(edge.to) - 1;
                if (toIn == 0) {
                    q.offer(edge.to);
                } else {
                    ins.put(edge.to, toIn);
                }
            }

        }
        return list;
    }

    @Override
    public HashMap<V, W> dijkstra1(V begin) {
        Vertex<V, W> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return null;
        }
        // paths: 记录由beginVertex 到达 点Vertex 的 edge权值
        // 键值对为 edge.to, edge.weight
        HashMap<Vertex<V, W>, W> paths = new HashMap<>();
        HashMap<V, W> selected = new HashMap<>();
        for (Edge<V, W> edge : beginVertex.outEdges) {
            paths.put(edge.to, edge.weight);
        }
        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, W>, W> minEntry = getMinPath1(paths);
            // minEntry要离开桌面,对其outEdges进行松弛操作
            Vertex<V, W> minVertex = minEntry.getKey();
            selected.put(minEntry.getKey().value, minEntry.getValue());
//            if (minEntry.getValue().equals(30.0)) {
//                System.out.println(minEntry.getKey());
//            }
            paths.remove(minEntry.getKey());
            // 对minVertex的outEdges进行松弛操作
            for (Edge<V, W> edge : minVertex.outEdges) {
                // 如果 edge.to已经离开桌面，就没必要进行松弛
                if (selected.containsKey(edge.to.value)) {
                    continue;
                }
                // 对 D E进行松弛，实质就是 查看 原有的 A -> E， 与现有的路径 A -> D -> E 那个路径更短
                // 以前的路径:  beginVertex -> edge.to
                // 现在的路径： beginVertex -> edge.from + edge.weight
                W oldWeight = paths.get(edge.to);
                // 为null时为无穷大
                W newWeight = weightManager.add(minEntry.getValue(), edge.weight);
                if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0) {
                    paths.put(edge.to, newWeight);
                }
            }
        }
        selected.remove(begin);
        return selected;
    }

    private Map.Entry<Vertex<V, W>, W> getMinPath1(HashMap<Vertex<V, W>, W> paths) {
        Iterator<Map.Entry<Vertex<V, W>, W>> itr = paths.entrySet().iterator();
        HashMap.Entry<Vertex<V, W>, W> minEntry = itr.next();
        while (itr.hasNext()) {
            Map.Entry<Vertex<V, W>, W> entry = itr.next();
            if (weightManager.compare(entry.getValue(), minEntry.getValue()) < 0) {
                minEntry = entry;
            }
        }
        return minEntry;
    }

    /**
     * 不能有负权边 dijkstra类型拽石头
     * @param begin V
     * @return Map
     */
    @Override
    public Map<V, PathInfo<V, W>> dijkstra2(V begin) {
        Vertex<V, W> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return null;
        }
        HashMap<V, PathInfo<V, W>> selected = new HashMap<>();
        HashMap<Vertex<V, W>, PathInfo<V, W>> paths = new HashMap<>();
        paths.put(beginVertex, new PathInfo<>(weightManager.zero()));
        /*
        for (Edge<V, W> edge : beginVertex.outEdges) {
            PathInfo<V, W> pathInfo = new PathInfo<>();
            pathInfo.weight = edge.weight;
            pathInfo.edgeInfos.add(edge.info());
            paths.put(edge.to, pathInfo);
        }
         */
        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, W>, PathInfo<V, W>> minEntry = getMinPath2(paths);
            Vertex<V, W> minVertex = minEntry.getKey();
            selected.put(minVertex.value, minEntry.getValue());
            paths.remove(minVertex);
            for (Edge<V, W> edge : minVertex.outEdges) {
                if (selected.containsKey(edge.to.value)) {
                    continue;
                }
                relax(paths, minEntry.getValue(), edge);
            }
        }
        selected.remove(begin);
        return selected;
    }

    @Override
    public Map<V, PathInfo<V, W>> bellmanFord(V begin) {
        Vertex<V, W> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return null;
        }
        HashMap<V, PathInfo<V, W>> selected = new HashMap<>();
        PathInfo<V, W> beginPath = new PathInfo<>();
        beginPath.weight = weightManager.zero();
//        beginPath.edgeInfos; // xxxxxx
        selected.put(begin, beginPath);

        int count = vertices.size() - 1;
        for (int i = 0; i < count; i++) { // v - 1次能保证松弛成功
            for (Edge<V, W> edge : edges) {
                PathInfo<V, W> fromPath = selected.get(edge.from.value);
                if (fromPath == null) {
                    continue;
                }
                relaxforbf(edge, fromPath, selected);
            }
        }
        // 检测负权环
        for (Edge<V, W> edge : edges) {
            PathInfo<V, W> fromPath = selected.get(edge.from.value);
            if (fromPath == null) {
                continue;
            }
            if (relaxforbf(edge, fromPath, selected)) {
                System.out.println("有负权环");
                return null;
            };
        }
        selected.remove(begin);
        return selected;
    }

    @Override
    public Map<V, Map<V, PathInfo<V, W>>> floyd() {
        Map<V, Map<V, PathInfo<V, W>>> paths = new HashMap<>();
        // 初始化，保证paths中有路径
        for (Edge<V, W> edge : edges) {
            Map<V, PathInfo<V, W>> map = paths.computeIfAbsent(edge.from.value, k->new HashMap<>());
            PathInfo<V, W> newPath = new PathInfo<>(edge.weight);
            newPath.edgeInfos.add(edge.info());
            map.put(edge.to.value, newPath);
        }
        // 从 v1->v2， v2->v3 与 v1->v3的距离
        vertices.forEach((V v2, Vertex<V, W> vertex2) ->{
            vertices.forEach((V v1, Vertex<V, W> vertex1)->{
                vertices.forEach((V v3, Vertex<V, W> vertex3)->{
                    if (v1.equals(v2) || v2.equals(v3) || v1.equals(v3)) {
                        return;
                    }
                    // v1 -> v2
                    PathInfo<V, W> path12 = getPathInfo(v1, v2, paths);
                    if (path12 == null) {
                        return;
                    }
                    // v2 -> v3
                    PathInfo<V, W> path23 = getPathInfo(v2, v3, paths);
                    if (path23 == null) {
                        return;
                    }
                    // v1 -> v3
                    PathInfo<V, W> path13 = getPathInfo(v1, v3, paths);
                    W newWeight = weightManager.add(path12.getWeight(), path23.getWeight());
                    if (path13 != null && weightManager.compare(newWeight, path13.getWeight()) >= 0) {
                        return;
                    }
                    if (path13 == null) {
                        path13 = new PathInfo<>();
                        paths.get(v1).put(v3, path13);
                    } else {
                        path13.edgeInfos.clear();
                    }
                    path13.weight = newWeight;
                    path13.edgeInfos.addAll(path12.edgeInfos);
                    path13.edgeInfos.addAll(path23.edgeInfos);
                    paths.get(v1).put(v3, path13);
                });
            });
        });
        return paths;
    }

    private PathInfo<V, W> getPathInfo(V from, V to, Map<V, Map<V, PathInfo<V, W>>> paths) {
        Map<V, PathInfo<V, W>> map = paths.get(from);
        return map == null ? null : map.get(to);
    }

    /**
     * bellman-ford不存在移除操作
     * @param edge    需要松弛的边
     * @param minPath edge.from的最短路径信息
     * @param paths   存放其他点（dijkstra中未离开桌面的点）的最短路径信息
     */
    private boolean relaxforbf(Edge<V, W> edge, PathInfo<V, W> minPath, HashMap<V, PathInfo<V, W>> paths) {
        W newWeight = weightManager.add(minPath.getWeight(), edge.weight);
        PathInfo<V, W> oldPath = paths.get(edge.to.value);
        if (oldPath != null && weightManager.compare(newWeight, oldPath.getWeight()) >= 0) {
            return false;
        }
        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to.value, oldPath);
        } else {
            oldPath.edgeInfos.clear();
        }
        // 更新weight 和 edgeInfos
        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(minPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());
        paths.put(edge.to.value, oldPath);
        return true;
    }

    private void relax(HashMap<Vertex<V, W>, PathInfo<V, W>> paths, PathInfo<V, W> minPath, Edge<V, W> edge) {
        W newWeight = weightManager.add(minPath.getWeight(), edge.weight);
        PathInfo<V, W> oldPath = paths.get(edge.to);
        if (oldPath != null && weightManager.compare(newWeight, oldPath.getWeight()) >= 0) {
            return;
        }
        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to, oldPath);
        } else {
            oldPath.edgeInfos.clear();
        }
        // 更新weight 和 edgeInfos
        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(minPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());
        paths.put(edge.to, oldPath);
    }

    private Map.Entry<Vertex<V, W>, PathInfo<V, W>> getMinPath2(HashMap<Vertex<V, W>, PathInfo<V, W>> paths) {
        Iterator<Map.Entry<Vertex<V, W>, PathInfo<V, W>>> itr = paths.entrySet().iterator();
        Map.Entry<Vertex<V, W>, PathInfo<V, W>> minEntry = itr.next();
        while (itr.hasNext()) {
            Map.Entry<Vertex<V, W>, PathInfo<V, W>> entry = itr.next();
            if (weightManager.compare(entry.getValue().getWeight(), minEntry.getValue().getWeight()) < 0) {
                minEntry = entry;
            }
        }
        return minEntry;
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
        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void addEdge(V from, V to, W weight) {
        // 判断顶点from，to是否存在
        Vertex<V, W> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            // addVertex(from);
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }
        Vertex<V, W> toVertex = vertices.get(to);
        if (toVertex == null) {
            // addVertex(to);
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }
        Edge<V, W> edge = new Edge<>(fromVertex, toVertex, weight);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void removeVertex(V v) {
        Vertex<V, W> vertex = vertices.remove(v);
        if (vertex == null) {
            return;
        }
        // 使用迭代器删除
        Iterator<Edge<V, W>> itr = vertex.outEdges.iterator();
        for (; itr.hasNext(); ) {
            Edge<V, W> edge = itr.next();
            edge.to.inEdges.remove(edge);
            itr.remove();
            edges.remove(edge);
        }
        itr = vertex.inEdges.iterator();
        for (; itr.hasNext(); ) {
            Edge<V, W> edge = itr.next();
            edge.from.outEdges.remove(edge);
            itr.remove();
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
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }

    @Override
    public void bfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) {
            return;
        }
        if (begin == null) {
            return;
        }
        Vertex<V, W> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return;
        }
        Queue<Vertex<V, W>> q = new LinkedList<>();
        Set<Vertex<V, W>> visited = new HashSet<>();
        q.offer(beginVertex);
        visited.add(beginVertex);
        while (! q.isEmpty()) {
            Vertex<V, W> vertex = q.poll();
            if (visitor.visit(vertex.value)) {
                return;
            }
            for (Edge<V, W> outEdge : vertex.outEdges) {
                if (! visited.contains(outEdge.to)) {
                    q.offer(outEdge.to);
                    visited.add(outEdge.to);
                }
            }
        }
    }

    @Override
    public void dfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) {
            return;
        }
        if (begin == null) {
            return;
        }
        Stack<Vertex<V, W>> stack = new Stack<>();
        Set<Vertex<V, W>> visited = new HashSet<>();
        Vertex<V, W> beginVertex = vertices.get(begin);
        stack.push(beginVertex);
        visited.add(beginVertex);
        if (visitor.visit(beginVertex.value)) {
            return;
        }
        while (! stack.isEmpty()) {
            Vertex<V, W> vertex = stack.pop();
            for (Edge<V, W> edge : vertex.outEdges) {
                if (visited.contains(edge.to)) {
                    continue;
                }
                stack.push(edge.from);
                stack.push(edge.to);
                if (visitor.visit(edge.to.value)) {
                    return;
                }
                visited.add(edge.to);
                // 只选择一条边，往深处遍历
                break;
            }
        }
    }

    @Override
    public Set<EdgeInfo<V, W>> mst(boolean prim) {
        if (prim) {
            return prim();
        } else {
            return kruskal();
        }
    }

    private Comparator<Edge<V, W>> edgeComparator = (Edge<V, W> a, Edge<V, W> b) -> weightManager.compare(b.weight, a.weight);

    public Set<EdgeInfo<V, W>> prim() {
        Iterator<Vertex<V, W>> itr = vertices.values().iterator();
        if (! itr.hasNext()) {
            return null;
        }
        Set<EdgeInfo<V, W>> edgeInfos   = new HashSet<>();
        Set<Vertex<V, W>> addedVertices = new HashSet<>();
        Vertex<V, W> vertex = itr.next();
        addedVertices.add(vertex);
        MinHeap<Edge<V, W>> heap = new MinHeap<>(vertex.outEdges, edgeComparator);
        int edgeSize = vertices.size() - 1;
        while (! heap.isEmpty() && edgeInfos.size() < edgeSize) {
            Edge<V, W> edge = heap.remove();
            if (addedVertices.contains(edge.to)) {
                continue;
            }
            edgeInfos.add(edge.info());
            addedVertices.add(edge.to);
            heap.addAll(edge.to.outEdges);
        }
        return edgeInfos;
    }

    public Set<EdgeInfo<V, W>> kruskal() {
        int edgeSize = vertices.size() - 1;
        if (edgeSize == -1) {
            return null;
        }
        // UnionFind
        UnionFindGeneric2<Vertex<V, W>> uf = new UnionFindGeneric2<>();
        vertices.forEach((V key, Vertex<V, W> vertex) -> {
            uf.add(vertex);
        });

        Set<EdgeInfo<V, W>> edgeInfos = new HashSet<>();
        MinHeap<Edge<V, W>> heap = new MinHeap<>(edges, edgeComparator);
        while (! heap.isEmpty() && edgeInfos.size() < edgeSize) {
            Edge<V, W> edge = heap.remove();
            // 判断是否属于环: 利用幷查集判断
            // 如果多个点中，如果某些顶点已经连接在一起，则属于同一个集合，如果连接已经连接的的顶点则会形成环
            if (uf.isSame(edge.from, edge.to)) {
                continue;
            }
            edgeInfos.add(edge.info());
            uf.union(edge.from, edge.to);
        }

        return edgeInfos;
    }

    /*
    @Override
    public void bfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return;
        }
        Queue<Vertex<V, E>> q = new LinkedList<>();
        Set<Vertex<V, E>> visited = new HashSet<>();
        q.offer(beginVertex);
        visited.add(beginVertex);
        while (! q.isEmpty()) {
            Vertex<V, E> vertex = q.poll();
//            visited.add(vertex);
            System.out.println(vertex);
            for (Edge<V, E> outEdge : vertex.outEdges) {
                if (! visited.contains(outEdge.to)) {
                    q.offer(outEdge.to);
                    visited.add(outEdge.to);
                }
            }

//            System.out.println("visi: " + visited);
//            System.out.println("q   : " + q);
//            System.out.println("");
        }
    }

    protected Set<Vertex<V, E>> visited = new HashSet<>();
    public void resetVisited() {
        visited = new HashSet<>();
    }

    @Override
    public void dfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return;
        }
        dfs(beginVertex);
        System.out.println("=====================");
        resetVisited();
        dfsByStack(beginVertex);
    }

    public void dfsByStack(Vertex<V, E> beginVertex) {
        Stack<Vertex<V, E>> stack = new Stack<>();
        stack.push(beginVertex);
        visited.add(beginVertex);
        System.out.println(beginVertex);
        while (! stack.isEmpty()) {
            Vertex<V, E> vertex = stack.pop();
            for (Edge<V, E> edge : vertex.outEdges) {
                if (visited.contains(edge.to)) {
                    continue;
                }
                stack.push(edge.from);
                stack.push(edge.to);
                System.out.println(edge.to);
                visited.add(edge.to);
                // 只选择一条边，往深处遍历
                break;
            }
        }

        /*
        stack.push(beginVertex);
        visited.add(beginVertex);
        while (! stack.isEmpty()) {
            Vertex<V, E> vertex = stack.pop();
            System.out.println(vertex);
            for (Edge<V, E> edge : vertex.outEdges) {
                if (visited.contains(edge.to)) {
                    continue;
                }
                stack.push(edge.to);
                visited.add(edge.to);
            }
        }

    }

    private void dfs(Vertex<V, E> vertex) {
        if (visited.contains(vertex)) {
            return;
        }
        visited.add(vertex);
        System.out.println(vertex);
        for (Edge<V, E> edge : vertex.outEdges) {
            dfs(edge.to);
        }
    }
    */
}
