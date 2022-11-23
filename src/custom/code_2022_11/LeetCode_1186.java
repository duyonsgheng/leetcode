package custom.code_2022_11;

/**
 * @ClassName LeetCode_1186
 * @Author Duys
 * @Description
 * @Date 2022/11/22 15:26
 **/
// 1186. 删除一次得到子数组最大和
public class LeetCode_1186 {
    public int maximumSum(int[] arr) {
        // dp0表示没删除
        // dp1表示删除了，初始状态是空的
        int ans = arr[0], dp0 = ans, dp1 = 0;
        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i];
            // 如果当前值大于0，则可以不删除
            if (cur > 0) {
                dp0 = Math.max(0, dp0) + cur;
                dp1 += cur;
            } else {
                // 当前删除，那么就需要用到之前不删除的。
                dp1 = Math.max(dp0, dp1 + cur);
                dp0 = Math.max(0, dp0) + cur;
            }
            ans = Math.max(ans, dp0);
            ans = Math.max(ans, dp1);
        }
        return ans;
    }
}
