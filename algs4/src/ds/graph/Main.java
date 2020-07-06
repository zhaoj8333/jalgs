package ds.graph;

import ds.collection.linkedlist.doubly.DoublyLinkedList;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    static Graph.WeightManager<Double> weightManager = new Graph.WeightManager<Double>() {
        @Override
        public int compare(Double a, Double b) {
            return a.compareTo(b);
        }

        @Override
        public Double add(Double a, Double b) {
            return a + b;
        }

        @Override
        public Double zero() {
            return 0.0;
        }
    };

    public static void main(String[] args) {
//        test1();
//        testBfs1();
//        testBfs2();
//        testDfs1();
//        testDfs2();
//        testMst();
//        testTopo();
//        testSp();
//        testBellmanFord();
        testFloyd();
    }

    private static void testFloyd() {
        Graph<Object, Double> graph = directedGraph(Data.sp);
        graph.print();
        Map<Object, Map<Object, Graph.PathInfo<Object, Double>>> floyd = graph.floyd();
        StdOut.println("\nfloyd pathInfos: ");
        if (floyd == null) {
            return;
        }
        floyd.forEach((Object from, Map<Object, Graph.PathInfo<Object, Double>> paths) -> {
            StdOut.println();
            StdOut.println("from : " + from);
            paths.forEach((Object to, Graph.PathInfo<Object, Double> path) -> {
                StdOut.println("       to : " + to);
                StdOut.println("              : " + path);
            });

            StdOut.println("====================================================================");
        });
    }

    private static void testBellmanFord() {
//        Graph<Object, Double> graph = directedGraph(Data.negative_weight1);
        Graph<Object, Double> graph = directedGraph(Data.negative_weight1);
        graph.print();
        Map<Object, Graph.PathInfo<Object, Double>> pathInfos = graph.bellmanFord("A");
        StdOut.println("\nbellman-ford pathInfos: ");
        if (pathInfos == null) {
            return;
        }
        pathInfos.forEach((Object v, Graph.PathInfo<Object, Double> path) -> {
            StdOut.println(v + " : " + path);
        });
    }

    private static void testSp() {
        Graph<Object, Double> graph = unDirectedGraph(Data.sp);
        graph.print();
        StdOut.println("----------------------------------");
        Map<Object, Double> pathInfos = graph.dijkstra1("A");
        StdOut.println("\ndijkstra pathInfos: ");
        pathInfos.forEach((Object v, Object w) -> {
            StdOut.println(v + " : " + w);
        });
        StdOut.println("----------------------------------");
        Map<Object, Graph.PathInfo<Object, Double>> pathInfos2 = graph.dijkstra2("A");
        StdOut.println("\nshortest pathInfos: ");
        pathInfos2.forEach((Object v, Graph.PathInfo<Object, Double> path) -> {
            StdOut.println(v + " : " + path);
        });
    }

    private static void testMst() {
        Graph<Object, Double> graph = unDirectedGraph(Data.mst1);
        graph.print();
        StdOut.println("========================================");
        Set<Graph.EdgeInfo<Object, Double>> set = graph.mst(true);
        StdOut.println();
        StdOut.println(set);
        StdOut.println();
        StdOut.println("========================================");
        Set<Graph.EdgeInfo<Object, Double>> set1 = graph.mst(false);
        StdOut.println();
        StdOut.println(set1);
    }

    private static void testTopo() {
        Graph<Object, Double> graph = directedGraph(Data.topo);
        graph.print();
        List<Object> list = graph.topoSort();
        StdOut.println();
        StdOut.println(list);
    }

    private static void testDfs2() {
        Graph<Object, Double> graph= directedGraph(Data.dfs02);
        graph.print();
        StdOut.println("=====================");
        graph.dfs("a", o->{
            StdOut.println(o);
            // return "e".equals(o);
            return false;
        });
    }

    private static void testDfs1() {
        Graph<Object, Double> graph= unDirectedGraph(Data.dfs01);
        graph.print();
        StdOut.println("=====================");
        graph.dfs(1, new Graph.VertexVisitor<Object>() {
            @Override
            public boolean visit(Object o) {
                StdOut.println(o);
                return false;
            }
        });
        StdOut.println("=====================");
    }

    private static void testBfs1() {
//        Graph<Object, Double> graph = unDirectedGraph(Data.bfs02);
        Graph<Object, Double> graph = directedGraph(Data.bfs02);
        graph.print();
        StdOut.println("============================");
        graph.bfs(5, o->{
            StdOut.println(o);
            return false;
        });
        StdOut.println("============================");
//        graph.dfs(0);
    }

    private static Graph<Object, Double> directedGraph (Object[][] data) {
        Graph<Object, Double> graph = new ListGraph1<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    private static Graph<Object, Double> unDirectedGraph (Object[][] data) {
//        Graph<Object, Double> graph = new ListGraph3<>(weightManager);
        Graph<Object, Double> graph = new ListGraph1<>(weightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }

    /*
    private static void testBfs1() {
        Graph<String, Integer> graph = new ListGraph1<>();

        graph.addEdge("v1", "v0", 9);
        graph.addEdge("v1", "v2", 3);
        graph.addEdge("v2", "v0", 2);
        graph.addEdge("v2", "v3", 5);
        graph.addEdge("v3", "v4", 1);
        graph.addEdge("v0", "v4", 6);
//        graph.addEdge("v4", "v0", 6);
//        graph.addEdge("v4", "v0", 1);

//        graph.removeEdge("v0", "v4");
//        graph.removeVertex("v0");
//        graph.print();

//        graph.bfs("v1");
    }

    private static void test3() {
        Graph<String, Integer> graph = new ListGraph2<>();
        graph.addEdge("v1", "v0", 9);
        graph.addEdge("v1", "v2", 3);
        graph.addEdge("v2", "v0", 2);
        graph.addEdge("v2", "v3", 5);
        graph.addEdge("v3", "v4", 1);
        graph.addEdge("v0", "v4", 6);
        graph.addEdge("v4", "v0", 6);
//        graph.addEdge("v4", "v0", 1);

//        graph.removeEdge("v0", "v4");
//        graph.removeVertex("v0");
//        graph.print();

//        graph.bfs("v0");
    }

     */

    /**
     * 使用有向图表达无向图
     */
    /*
    private static void test2() {

        Graph<String, Integer> graph = new ListGraph1<>();
        graph.addEdge("v1", "v0", 9);
        graph.addEdge("v0", "v1", 9);
        graph.addEdge("v1", "v2", 3);
        graph.addEdge("v2", "v1", 3);
        graph.addEdge("v2", "v0", 2);
        graph.addEdge("v0", "v2", 2);
        graph.addEdge("v2", "v3", 5);
        graph.addEdge("v3", "v2", 5);
        graph.addEdge("v3", "v4", 1);
        graph.addEdge("v4", "v3", 1);
        graph.addEdge("v0", "v4", 6);
        graph.addEdge("v4", "v0", 6);

        graph.print();
    }
     */

    private static void test1() {
        ListGraph3<String, Double> graph = new ListGraph3<>(weightManager);
        graph.addEdge("v1", "v0", 9.0);
        graph.addEdge("v1", "v2", 3.0);
        graph.addEdge("v2", "v0", 2.0);
        graph.addEdge("v2", "v3", 5.0);
        graph.addEdge("v3", "v4", 1.0);
//        graph.addEdge("v0", "v4", 6.0);
        graph.addEdge("v4", "v0", 6.2);
        //graph.addEdge("v4", "v0", 1);

        graph.print();
        StdOut.println("===================================================");
//        graph.removeEdge("v4", "v0");
        graph.removeVertex("v1");

        graph.print();
    }

}
