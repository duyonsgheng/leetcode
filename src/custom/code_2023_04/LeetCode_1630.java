package custom.code_2023_04;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1631
 * @Author Duys
 * @Description
 * @Date 2023/4/21 9:52
 **/
// 1630. 等差子数组
public class LeetCode_1630 {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int n = l.length;
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int left = l[i];
            int right = r[i];
            int cmax = nums[left];
            int cmin = nums[left];
            // 当前区间内的首项和尾项
            for (int j = left + 1; j <= right; j++) {
                cmin = Math.min(cmin, nums[j]);
                cmax = Math.max(cmax, nums[j]);
            }
            // 差值
            if (cmin == cmax) {
                ans.add(true);
                continue;
            }
            // 如果差值能被长度整除，说明可能是一个等差，否则完全不是
            if ((cmax - cmin) % (right - left) != 0) {
                ans.add(false);
                continue;
            }
            // 公差
            int diff = (cmax - cmin) / (right - left);
            boolean flag = true;
            boolean[] seen = new boolean[right - left + 1];
            for (int j = left; j <= right; j++) {
                // 如果当前项减去最小项，都不是公差的整数倍，不是等差数列
                if ((nums[j] - cmin) % diff != 0) {
                    flag = false;
                    break;
                }
                // 看看是属于第几项
                int t = (nums[j] - cmin) / diff;
                // 如果已经存在了，说明不能排列成为一个等差数列
                if (seen[t]) {
                    flag = false;
                    break;
                }
                seen[t] = true;
            }
            ans.add(flag);
        }
        return ans;
    }
}
