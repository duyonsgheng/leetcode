package week.week_2022_01_02;

/**
 * @ClassName Code_03_LeetCode_2111
 * @Author Duys
 * @Description
 * @Date 2022/4/1 15:20
 **/
public class Code_03_LeetCode_2111 {
    //给你一个下标从 0开始包含 n个正整数的数组arr，和一个正整数k。
    //如果对于每个满足k <= i <= n-1的下标i，都有arr[i-k] <= arr[i]，那么我们称arr是 K递增 的。
    //比方说，arr = [4, 1, 5, 2, 6, 2]对于k = 2是 K 递增的，因为：
    //arr[0] <= arr[2] (4 <= 5)
    //arr[1] <= arr[3] (1 <= 2)
    //arr[2] <= arr[4] (5 <= 6)
    //arr[3] <= arr[5] (2 <= 2)
    //但是，相同的数组arr对于k = 1不是 K 递增的（因为arr[0] > arr[1]），对于k = 3也不是 K 递增的（因为arr[0] > arr[3]）。
    //每一次 操作中，你可以选择一个下标i 并将arr[i] 改成任意正整数。
    //请你返回对于给定的 k，使数组变成 K 递增的 最少操作次数。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/minimum-operations-to-make-the-array-k-increasing

    public int kIncreasing(int[] arr, int k) {

        int n = arr.length;
        // a/b 向上取整 (a+b-1)/b
        int[] help = new int[(n + k - 1) / k];
        int ans = 0;
        for (int i = 0; i < k; i++) {
            ans += need(arr, help, n, i, k);
        }
        return ans;
    }

    // arr[start,start+k,start+2k,start+3k]
    public static int need(int[] arr, int[] help, int n, int start, int k) {
        int j = 0;
        int size = 0;
        for (; start < n; start += k, j++) {
            size = insert(help, size, arr[start]);
        }
        return j - size;
    }

    public static int insert(int[] help, int size, int num) {
        int l = 0;
        int r = size - 1;
        int m = 0;
        int ans = size;
        while (l <= r) {
            m = (l + r) >> 1;
            if (help[m] > num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        help[ans] = num;
        return ans == size ? size + 1 : size;
    }

}
