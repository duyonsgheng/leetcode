package week.week_2022_02_04;

import java.util.Arrays;

/**
 * @ClassName Code_04_NumberOfDescendingTriples
 * @Author Duys
 * @Description TODO
 * @Date 2022/3/23 16:42
 **/
public class Code_04_NumberOfDescendingTriples {

// 返回一个数组中，所有降序三元组的数量
// 比如 : {5, 3, 4, 2, 1}
// 所有降序三元组为 :
// {5, 3, 2}、{5, 3, 1}、{5, 4, 2}、{5, 4, 1}
// {5, 2, 1}、{3, 2, 1}、{4, 2, 1}
// 所以返回数量7

    // 暴力方法
    // 对数器
    public static int num1(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        return process(arr, 0, new int[3], 0);
    }

    // size 选了几个数了
    // index来到哪一个位置做抉择
    // path 之前选的哪些
    public static int process(int[] arr, int index, int[] path, int size) {
        if (size == 3) {
            return path[0] > path[1] && path[1] > path[2] ? 1 : 0;
        }
        int ans = 0;
        if (index < arr.length) {
            ans += process(arr, index + 1, path, size);
            path[size] = arr[index]; // 这里不用还原现场，因为size是会重置的
            ans += process(arr, index + 1, path, size + 1);
        }
        return ans;
    }

    public static int num2(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int n = arr.length;
        int[] stored = Arrays.copyOf(arr, n);
        Arrays.sort(stored);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            arr[i] = rank(stored, arr[i]);
            max = Math.max(max, arr[i]);
        }
        IndexTree it2 = new IndexTree(max);
        IndexTree it3 = new IndexTree(max);
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            ans += arr[i] == 1 ? 0 : it3.sum(arr[i] - 1);
            it2.add(arr[i], 1);
            it3.add(arr[i], arr[i] == 1 ? 0 : it2.sum(arr[i] - 1));
        }
        return ans;
    }

    // 返回 >= num最左的位置
    public static int rank(int[] arr, int num) {
        int l = 0;
        int r = arr.length - 1;
        int m = 0;
        int ans = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] >= num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans + 1;
    }

    public static class IndexTree {
        private int[] tree;
        private int n;

        // 0位置不用
        public IndexTree(int size) {
            tree = new int[size + 1];
            n = size + 1;
        }

        // 1到index的累加和
        public int sum(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }

        public void add(int index, int d) {
            while (index <= n) {
                tree[index] += d;
                index += index & -index;
            }
        }
    }
}
