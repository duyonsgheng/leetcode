package week.week_2023_05_04;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @ClassName Code_04_EquipmentFirstToEnd
 * @Author Duys
 * @Description
 * @Date 2023/5/25 9:55
 **/
// 来自招商银行
// 给定一个数组arr，长度为n，表示有0~n-1号设备
// arr[i]表示i号设备的型号，型号的种类从0~k-1，一共k种型号
// 给定一个k*k的矩阵map，来表示型号之间的兼容情况
// map[a][b] == 1，表示a型号兼容b型号
// map[a][b] == 0，表示a型号不兼容b型号
// 兼容关系是有向图，也就是a型号兼容b型号，不代表b型号同时兼容a型号
// 如果i设备的型号兼容j设备的型号，那么可以从i设备修建一条去往j设备的线路
// 修建线路的代价是i设备到j设备的距离：|i-j|
// 你的目标是从0号设备到达n-1号设备，并不一定每个设备都联通，只需要到达即可
// 返回最小的修建代价，如果就是无法到达返回-1
// 1 <= n <= 1000
// 1 <= k <= 50
public class Code_04_EquipmentFirstToEnd {
    // 根据题意
    // 建图
    // 跑最短路径
    public static int minCost(int[] arr, int[][] map, int n, int k) {
        // 设备在哪些点
        List<List<Integer>> drivers = new ArrayList<>();
        //设备能去哪里
        List<List<Integer>> nexts = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            drivers.add(new ArrayList<>());
            nexts.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            drivers.get(arr[i]).add(i);
        }
        // 设备之间的包容关系
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (i == j || map[i][j] != 1) {
                    continue;
                }
                // i号设备到j号设备之间有路
                nexts.get(i).add(j);
            }
        }

        // 开始的迪杰斯特拉
        // 1.建立一个小根堆
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        // 2.走过的路，就不要回头了
        boolean[] visited = new boolean[n];
        heap.add(new int[]{0, 0});// 从0 0 开始
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int x = cur[0];
            int cost = cur[1];
            if (visited[x]) {
                continue;
            }
            visited[x] = true;
            if (x == n - 1) {
                return cost;
            }
            int model = arr[x]; // 型号是啥
            // 型号相连的
            for (int nextModel : nexts.get(model)) {
                // 型号所在的位置
                for (int nextX : drivers.get(nextModel)) {
                    if (visited[nextX]) {
                        continue;
                    }
                    heap.add(new int[]{nextX, cost + Math.abs(nextX - x)});
                }
            }
        }
        // 一直都没遇到n-1 ，则返回-1
        return -1;
    }
}
