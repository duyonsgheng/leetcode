package duys_code.day_20;

import java.util.HashMap;

/**
 * @ClassName Code_02_952_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/largest-component-size-by-common-factor/
 * @Date 2021/11/5 11:05
 **/
public class Code_02_952_LeetCode {
    /**
     * 结论：计算某一个数的因子，只需要从1 到 根号当前数 这区间，机型取模运算
     */
    public static int largestComponentSize(int[] arr) {
        int n = arr.length;
        // arr中，N个位置，在并查集初始时，每个位置自己是一个集合
        UnionFind unionFind = new UnionFind(n);
        // 当前因子是属于哪一个位置的数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            // 开根号
            int limit = (int) Math.sqrt(num);
            for (int j = 1; j <= limit; j++) {
                if (num % j != 0) {
                    continue;
                }
                if (j != 1) {
                    if (map.containsKey(j)) {
                        // 位置合并去吧
                        unionFind.union(i, map.get(j));
                    } else {
                        map.put(j, i);
                    }
                }
                int other = num / j;
                if (other != 1) {
                    if (map.containsKey(other)) {
                        // 位置合并去吧
                        unionFind.union(i, map.get(other));
                    } else {
                        map.put(other, i);
                    }
                }
            }
        }
        return unionFind.maxSize();
    }

    public static class UnionFind {
        private int[] parents;
        private int[] sizes;
        private int[] help;

        public UnionFind(int n) {
            parents = new int[n];
            sizes = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        public int maxSize() {
            int ans = 0;
            for (int size : sizes) {
                ans = Math.max(ans, size);
            }
            return ans;
        }

        public void union(int a, int b) {
            int i = find(a);
            int j = find(b);
            if (i != j) {
                int big = sizes[i] > sizes[j] ? i : j;
                int small = sizes[i] > sizes[j] ? j : i;
                parents[small] = big;
                sizes[big] += sizes[small];
            }
        }

        private int find(int i) {
            int helpIndex = 0;
            // 记录整条链路
            while (parents[i] != i) {
                help[helpIndex++] = i;
                i = parents[i];
            }
            // 把整条链路全部挂在 相同的父结点上去
            for (helpIndex--; helpIndex >= 0; helpIndex--) {
                parents[help[helpIndex]] = i;
            }
            return i;
        }
    }

    // O(1)
    // m,n 要是正数，不能有任何一个等于0
    // 计算两个数的最大公约数
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(gcd(72,60));
    }
}
