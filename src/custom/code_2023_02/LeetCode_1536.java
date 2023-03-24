package custom.code_2023_02;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1536
 * @Author Duys
 * @Description
 * @Date 2023/2/7 17:48
 **/
// 1536. 排布二进制网格的最少交换次数
public class LeetCode_1536 {
    // 贪心策略
    // 从上到下逐行确定，比如来到第i行，那么0到i-1行已经确定了
    // 第i行满足的情况是末尾连续的0一定要大于等于 n-i-1，那么我们考虑交换哪一行到第i行来呢，交换离第i行最近的且满足条件的行号
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        // 记录每一行最右侧的1在什么位置，可以算出末尾连续的0有几个
        int[] arr = new int[n];
        Arrays.fill(arr, -1);
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    arr[i] = j;
                    break;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int k = -1;
            for (int j = i; j < n; j++) {
                if (arr[j] <= i) { // 下一行的1的位置，如果是小于等于i的，说明末尾连续的1一定是比n-i-1 要多的
                    ans += j - i;
                    k = j;
                    break;
                }
            }
            // 每一次只能两行交换，所以需要挨着交换位置
            if (k >= 0) {
                for (int j = k; j > i; j--) {
                    int tmp = arr[i];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            } else {
                return -1;
            }
        }
        return ans;
    }
}
