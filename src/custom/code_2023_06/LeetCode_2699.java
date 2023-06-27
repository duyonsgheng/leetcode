package custom.code_2023_06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_2699
 * @Author Duys
 * @Description
 * @Date 2023/6/9 9:16
 **/
// 2699. 修改图中的边权
public class LeetCode_2699 {
    HashMap<Integer, Integer>[] maps = null; //边
    int mN = 0; //n
    long[][] diss = null; //floyd，不计算-1的距离
    int targetDis = 0; //目标长度target

    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        mN = n;
        maps = new HashMap[mN];
        targetDis = target;
        for (int i = 0; i < mN; i++) {
            maps[i] = new HashMap<>();
        }

        diss = new long[mN][mN];
        for (int i = 0; i < diss.length; i++) {
            Arrays.fill(diss[i], -1L);
            diss[i][i] = 0;
        }

        //加入边到maps 以及 diss
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            maps[u].put(v, w);
            maps[v].put(u, w);
            diss[u][v] = w;
            diss[v][u] = w;
        }
        //floyd，计算除-1边的所有点之间的距离
        floyd(diss, null);
        if (diss[source][destination] != -1 && diss[source][destination] < target) {
            return new int[0][0];
        }
        //dijstra 改-1的边的权
        long d = dijstra(source, destination);

        if (d == -1 || d > target) {
            return new int[0][0];
        }

        //没来得及改的-1边权，改为target，或者改为最大值也可以
        for (int[] e : edges) {
            if (e[2] == -1) {
                int w = maps[e[0]].get(e[1]);
                if (w == -1) {
                    e[2] = target;
                } else {
                    e[2] = w;
                }
            }
        }
        return edges;
    }

    private long dijstra(int s, int t) {
        if (s == t) {
            return 0L;
        }
        PriorityQueue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] a, long[] b) {
                return a[0] < b[0] ? -1 : (a[0] == b[0] ? 0 : 1);
            }
        });
        long[] dis = new long[mN];
        Arrays.fill(dis, Long.MAX_VALUE);
        dis[s] = 0;
        //加入优先队列
        pq.offer(new long[]{0, s});
        boolean found = false;
        while (!pq.isEmpty()) {
            //取最小的点，加入点集
            long[] ele = pq.poll();
            long dEle = ele[0];
            int p = (int) ele[1];

            if (dEle > dis[p]) {
                continue;
            }

            for (Map.Entry<Integer, Integer> entry : maps[p].entrySet()) {
                //q，w为邻接点
                int q = entry.getKey();
                int w = entry.getValue();
                if (w == -1) {
                    //当w为-1时，特殊处理
                    if (dis[q] > dis[p] + 1) {
                        //判断这条边改为1的时候，是不是source到des的距离小于target
                        if (diss[q][t] != -1 && dis[p] + 1 + diss[q][t] <= targetDis) {
                            //标记为找到了正确的路，同时把边权改为合适的值
                            found = true;
                            int change = (int) (targetDis - diss[q][t] - dis[p]);
                            maps[p].put(q, change);
                            maps[q].put(p, change);
                            dis[t] = targetDis;
                            break;
                        } else {
                            //否则把边权改为1，继续找
                            dis[q] = dis[p] + 1;
                            maps[p].put(q, 1);
                            maps[q].put(p, 1);
                            pq.offer(new long[]{dis[q], q});
                        }
                    }
                } else {
                    //普通的点入队列
                    if (dis[q] > dis[p] + w) {
                        dis[q] = dis[p] + w;
                        pq.offer(new long[]{dis[q], q});
                    }
                }
            }
            //找到路，结束dijstra
            if (found) {
                break;
            }
        }
        return dis[t];
    }

    private void floyd(long[][] diss, int[][] path) {
        for (int k = 0; k < mN; k++) {
            for (int i = 0; i < mN; i++) {
                for (int j = 0; j < mN; j++) {
                    if (diss[i][k] == -1 || diss[k][j] == -1) {
                        continue;
                    }
                    if (diss[i][j] == -1 || diss[i][j] > diss[i][k] + diss[k][j]) {
                        diss[i][j] = diss[i][k] + diss[k][j];
                        if (path != null) {
                            path[i][j] = k;
                        }
                    }
                }
            }
        }
    }
}
