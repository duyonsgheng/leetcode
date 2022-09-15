package custom.code_2022_08;

/**
 * @ClassName LeetCode_396
 * @Author Duys
 * @Description
 * @Date 2022/8/8 15:51
 **/
//396. 旋转函数
public class LeetCode_396 {
    // 滑动窗口，
    // 数组扩展原来的两倍，拼接好，窗口大小固定n
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n * 2 + 10];
        for (int i = 1; i <= 2 * n; i++) {
            sum[i] = sum[i - 1] + nums[(i - 1) % n];
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += nums[i - 1] * (i - 1);
        }
        for (int i = n + 1, cur = ans; i < 2 * n; i++) {
            cur += nums[(i - 1) % n] * (n - 1);
            cur -= sum[i - 1] - sum[i - n];
            if (cur > ans) {
                ans = cur;
            }
        }
        return ans;
    }
}
