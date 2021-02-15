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

    广度优先搜索(BFS)：

    深度优先搜索(DFS)：

    AOV网(Activity On Vertex Net)： 一项大的工程通常被分为多个子工程，子工程有一定的顺序，某些子工程必须在其他子工程完成后才开始
        一般使用有向图来描述和分析一项工程的计划和实施过程，子工程被称为活动；
    以顶点表示活动，有向边表示活动之间的先后关系，这样的图称为AOV网
    AOV网必须是一个有向无环图(Directed Acyclic Graph, DAG)

    拓扑排序(Topological Sort)：
        前驱活动： 有向边起点的活动称为终点的前驱活动
            只有当一个活动的前驱活动都完成后，这个活动才能进行
        后继活动：
        拓扑排序：将AOV网中所有活动排成一个序列，使得每个活动的前驱活动都排在该活动的前面

    1. 拓扑排序： 卡恩算法
        ①. 假设L存放拓扑排序结果的列表，寻找入度为0的顶点（没有任何依赖）放入L中，然后把这些顶点从图中去掉
        ②. 重复操作①，直到找不到入度为0的顶点
        ③. 如果此时L中的元素个数与顶点数相同，说明拓扑排序完成
        ④. 如果此时L中元素少于顶点总数，说明原图中有环，无法进行拓扑排序

    AOE网(Activity On Edge Net)： 具有权值的边的有向图中，用顶点表示事件，用边表示活动，边上的权值表示持续时间

    生成树：（Spanning Tree）， 支撑树
        （连通图是无向的,任意两点之间都有可抵达的路径，间接的到达也可以）

        连通图的极小联通子图，含有图中全部的n个顶点，恰好只有n-1条边

    最小生成树(Minimum Spanning Tree) : MST
        所有生成树中，总权值最小的那一棵,适用于有权的无向连通图
        应用：
            各个城市之间铺设光缆
        如果图的每一条边权值都不相同，那么最小生成树将只有一个，否则可能会有多个最小生成树

    最小生成树算法：
        Prim（普里姆算法）
        Kruskal（克鲁斯克尔算法）

    切分定理：
        切分(Cut)：把图中节点分为两部分，称为一个切分
        C = (S, T), S = {A, B, D}, T = {C, E}

        横切边(Crossing Edge): 如果一个边的两个端点，分别属于切分的两部分，则这个边称为横切边
        BC, BE, DE, DC, AC, AE:

        定理： 给定任意切分，横切边中权值最小的边必然属于最小生成树

    Prim算法 —— 执行流程
        1. 假设G = (V, E)是有权的连通图（无向），A是G中最小生成树的边集
        2. 从S = {u0} (uo 属于 V，为起点), A = { } 开始，重复执行一下操作，直到 S = V为止
        3. 找到切分 C = (S, V - S) 的最小横切边(u0, v0)并入集合A，同时将V0并入集合S
    Kruskal算法 —— 执行流程
        1. 按照边的权重（从小到大）将边加入生成树中，知道生成树中含有v - 1条边为止（v是顶点数量）
        2. 若加入该边会与生成树形成环，则不加入该边
        3. 从第三条边开始，则会与生成树形成环

    最短路径（Shortest Path）
        有权：两顶点之间权值之和和最小的路径（有向图，无向图均使用，不能有负权环）
        无权：相当于权值全为1的有权图
        负权：
            有负权边，但没有负权环，存在最短路径
            有负权环，不存在最短路径
        应用： 路径规划
            ①. 单源最短路径算法:
                Dijkstra 迪克斯特拉算法
                    等价思考：
                        每块顶点是小石头，每个石头被不同长度的绳子拴在一起，小石头与绳子平放于桌面，就其中一个小石头A往上提起，
                        石头离开桌面的顺序取决于与石头A的最短路径
                        全部被提起后，有些绳子会被绷直，有些绳子依然是松弛状态
                        *** 某个小石头被提起，就意味着A到该小石头的最短路径就被确定
                        *** 最后绷直的绳子就是A到其他小石头的最短路径
                        *** 后离开的小石头都是被先离开桌面的小石头拉起来的

                    松弛操作(Relaxation)：尝试找出两个顶点之间的更短路径，并更新之
                        确定了a到d的最短路径后，对dc，de边进行松弛操作，也就是尝试求取(更新)a到c，a到e的最短路径
                        一旦某个点离开桌面，就会对该点的outEdges.to进行松弛操作,即 求取(更新)最短路径(最开始该点到其他点的路径为无穷大)
                        松弛操作的意义在于尝试找出更短的路径

                Bellman-Ford 贝尔曼-福特算法： 也属于单源路径算法，支持负权边，还能检测出是否有负权环：
                    原理  ：对所有边进行 V-1 次松弛操作(V是节点数量),得到所有可能的路径
                    复杂度：O(EV)，E是边数量，V是节点数量
                    最好情况： 对所有边进行一次松弛操作即可完成计算出A到其他所有顶点的最短路径，再次多次松弛时结果都与第一次一致
                    最坏情况： 与最好情况反过来，部分节点松弛操作会失败，对所有边需要进行V-1次松弛操作才能计算出起点A到其他顶点的最短路径
                        每一轮只能松弛成功一条边
                        一条边能否松弛，判断依据为 edge.from的最短路径是否已经计算出

            ②. 多源最短路径算法：
                Floyd 弗洛伊德算法
                    多源最短路径路径算法，能够求出任意两个顶点之间的最短路径，支持负权边
                    可以做多次dijkstra或bellman-ford，但是有更好的方法
                事件复杂度： O(v^3)，执行效率比V次dijkstra效率要好（V为定点数量）

                原理：
                    从任意顶点i到任意顶点j的最短路径不外乎两种可能
                        * 直接从i到j
                        * 从i经过若干个顶点到j
                    假设 dist(i, j)为顶点i到顶点j的最短路径的距离，对于每一个顶点k，检查dist(i, k) + dist(k,j) < dist(i, j)是否成立
                    × 如果成立，证明从i到k再到j的路径比i直接到j的路径短，设置 dist(i,j) = dist(i, k) + dist(k, j)
                    × 当遍历完所有节点k, dist(i, j)记录的便是i到j的最短路径的距离

 */