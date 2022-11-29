package custom.code_2022_11;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName LeetCode_1248
 * @Author Duys
 * @Description
 * @Date 2022/11/29 10:54
 **/
// 1248. 统计「优美子数组」
public class LeetCode_1248 {
    public static void main(String[] args) {
        int[] arr = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        System.out.println(numberOfSubarrays(arr, 2));
    }

    public static int numberOfSubarrays(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        int l = 0, r = 0, cnt = 0;
        while (r < n) {
            if ((nums[r++] & 1) == 1) {
                cnt++;
            }
            if (cnt == k) {
                int index = r;
                // 刚刚有k个奇数，然后右边还有几个偶数，直到遇到下一个奇数或者越界位置
                while (r < n && ((nums[r] & 1) == 0)) {
                    r++;
                }
                int rCnt = r - index;
                int lCnt = 0;
                // 第一个奇数开始，左边有几个偶数
                while ((nums[l] & 1) == 0) {
                    lCnt++;
                    l++;
                }
                // 左边可以取全部和一个都不取，所以lcnt+1种
                // 右边可以取全部和一个都不取，所以rcnt+1种
                ans += (lCnt + 1) * (rCnt + 1);
                // 左边第一个奇数出去
                l++;
                // 对应计数减一
                cnt--;
            }
        }
        return ans;
    }
}
