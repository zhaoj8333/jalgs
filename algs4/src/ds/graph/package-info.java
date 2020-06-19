/*
    图：

        图的组成： 顶点(vertex) 和 边(edge)组成，通常表示为G = (V, E)
        V: 顶点集，有穷且非空
        E: 边集，任意两个定点之间都可以用边来表示他们之间的关系，边集E可以为空

    图的应用：
        社交网络
        地图导航

    有向图：Directed Graph,有向图的边是有明确方向的
    有向无环图: Direct Acyclic Graph(DAG)： 如果一个有向图，从任意顶点出发无法经过若干条边回到该定点，则是有向无环图

    出度(适用于有向图)：out degree： 一个顶点的出度为x，是指x条边以该定点为起点
    入度(适用于有向图)：in degree： 有x条边以该顶点为终点

    无向图： Undirected Graph
        无向图的边没有方向，效果类似于双向有向图

    混合图： Mixed Graph, 可能无向，可能有向

    平行边：无向图中，关联一对顶点的无向边如果多于1条，则这些边称为平行边
           有向图中，关联一对顶点的有向边如果多于1条，且他们方向相同，则这些边为平行边


    多重图： Multigraph， 有平行边或自环的图
    简单图： 既没有平行边也没用自环的图

    无向完全图（Undirected Complete Graph）： 任意两个顶点之间都存在边
        n个顶点的无向完全图有 n(n-1)/2条边
    有向完全图（Directed Complate Graph）： 任意两个定点之间都存在相反的两条边
        n(n-1)

    稠密图(Dense Graph):  边数接近于或等于完全图
    稀疏图(Sparse Graph): 边数远远少于完全图

    有权图(Weighted Graph): 有权图的边拥有权值

    连通图(Connected Graph):
        如果顶点x和y之间存在可相互抵达的路径（直接或间接的路径），则x与y是联通的
        如果无向图G中任意2个顶点都是联通的，则G为连通图

    联通分量(Connected Component): 无向图的极大联通子图,连通图只有1个联通分量，即自身；非连通的无向图有多个联通分量

    强联通图(Strongly Connected Graph): 如果有向图G中任意2个顶点是联通的，则称G为强连通图

    强联通分量(Strongly Connected Component): 有向图的极大强联通子图
        强连通图只有一个强联通分量，即其自身；非强联通的有向图有多个强联通分量

    图的实现方案：
        邻接矩阵(Adjacency Matrix)： 一维数组存放顶点信息(定点数组)，二维数组存放边信息(边数组)，适合于稠密图,否则比较浪费内存
        邻接表(Adjacency List) 或 逆邻接表： 使用数组存放定点信息，每个数组成员以链表方式存放连接信息
 */