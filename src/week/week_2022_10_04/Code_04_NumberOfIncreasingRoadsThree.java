package week.week_2022_10_04;

import java.util.Arrays;

/**
 * @ClassName Code_04_NumberOfIncreasingRoadsThree
 * @Author Duys
 * @Description
 * @Date 2022/10/27 14:11
 **/
// 来自拼多多
// 第一行有一个正整数n(3<=n<=100000)，代表小A拟定的路线数量
// 第二行有n个正整数，第i个代表第i条路线的起始日期
// 第三行有n个正整数，第i个代表第i条路线的终止日期
// 输入保证起始日期小于终止日期
// 日期最小是1，最大不超过1000000000
// 小A打算选三个路线进行旅游，比如 A -> B -> C
// 要求A的结束日期要小于B的开始日期，B的结束日期要小于C的开始日期
// 输出一个非负整数，代表线路的方案数量
// 例子
// 输入
// 6
// 4 1 3 2 1 2
// 4 1 3 3 2 2
// 输出
// 6
// 解释
// [1,1] -> [2,2] -> [3,3]
// [1,1] -> [2,2] -> [4,4]
// [1,1] -> [2,3] -> [4,4]
// [1,2] -> [3,3] -> [4,4]
// [1,1] -> [3,3] -> [4,4]
// [2,2] -> [3,3] -> [4,4]
public class Code_04_NumberOfIncreasingRoadsThree {
    // 思路
    // 根据roads的开始时间排序，然后看看每一个记录能不能扩展到3天去
    // 使用3个indexTree
    public static int num(int[][] roads) {
        int n = roads.length;
        int[] stored = new int[n << 1];
        for (int i = 0; i < n; i++) {
            stored[i << 1] = roads[i][0];
            stored[i << 1 | 1] = roads[i][1];
        }
        Arrays.sort(roads, (a, b) -> a[0] - b[0]);
        Arrays.sort(stored);
        IndexTree i1 = new IndexTree(n << 1);
        IndexTree i2 = new IndexTree(n << 1);
        IndexTree i3 = new IndexTree(n << 1);
        for (int[] road : roads) {
            int l = rank(stored, road[0]);
            int r = rank(stored, road[1]);
            i1.add(r, 1);
            i2.add(r, i1.sum(l - 1));
            i3.add(r, i2.sum(l - 1));
        }
        return i3.sum(n << 1);
    }

    public static int rank(int[] arr, int num) {
        int l = 0;
        int r = arr.length - 1;
        int m = 0;
        int ans = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] >= num) {
                r = m - 1;
                ans = m;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    public static class IndexTree {
        private int[] tree;
        private int n;

        public IndexTree(int size) {
            n = size;
            tree = new int[n + 1];
        }

        public int sum(int index) {
            int ans = 0;
            while (index > 0) {
                ans += tree[index];
                index -= index & -index;
            }
            return ans;
        }

        public void add(int index, int v) {
            while (index <= n) {
                tree[index] += v;
                index += index & -index;
            }
        }
    }
}
