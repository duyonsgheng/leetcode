package custom.code_2022_10;

import java.util.Map;

/**
 * @ClassName LeetCode_985
 * @Author Duys
 * @Description
 * @Date 2022/10/28 13:22
 **/
// 985. 查询后的偶数和
public class LeetCode_985 {
    public static int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int oSum = 0;
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
            if (arr[i] % 2 == 0) {
                oSum += arr[i];
            }
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int[] cur = queries[i];
            int v = cur[0];
            int index = cur[1];
            int old = arr[index];
            if (old % 2 == 0) {
                oSum -= old;
            }
            if ((old + v) % 2 == 0) {
                oSum += old + v;
            }
            arr[index] = old + v;
            ans[i] = oSum;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1};
        int[][] a = {{4, 0}};
        // 2  2  3  4   8
        // 2 -1  3  4   6
        //
        int[] ints = sumEvenAfterQueries(arr, a);
        System.out.println();
    }
}
