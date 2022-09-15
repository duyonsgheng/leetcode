package duys_code.day_52;

/**
 * @ClassName Code_02_683_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/k-empty-slots/
 * @Date 2021/11/8 16:45
 **/
public class Code_02_683_LeetCode {

    // 滑动窗口 窗口 k+2个元素，中间k个的最大值小于 两边元素的最小值，中间元素的最小值大于两边元素的最大值
    public static int kEmptySlots1(int[] bulbs, int k) {
        int n = bulbs.length;
        // 灯泡亮的时间
        int[] days = new int[n];
        for (int i = 0; i < n; i++) {
            days[bulbs[i] - 1] = i + 1;
        }
        int ans = Integer.MAX_VALUE;
        if (k == 0) {
            for (int i = 1; i < n; i++) {
                ans = Math.min(ans, Math.max(days[i - 1], days[i]));
            }
        } else {
            int[] minq = new int[n];
            int l = 0;
            int r = -1;
            // 初步形成的窗口
            for (int i = 1; i < n && i < k; i++) {
                while (l <= r && days[minq[r]] >= days[i]) {
                    r--;
                }
                minq[++r] = i;
            }
            for (int i = 1, j = k; j < n - 1; i++, j++) {
                while (l <= r && days[minq[r]] >= days[j]) {
                    r--;
                }
                minq[++r] = j;
                int cur = Math.max(days[i - 1], days[j + 1]);
                if (days[minq[l]] > cur) {
                    ans = Math.min(ans, cur);
                }
                if (i == minq[l]) {
                    l++;
                }
            }
        }
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }

    // 双指针
    public static int kEmptySlots2(int[] bulbs, int k) {
        int n = bulbs.length;
        int[] days = new int[n];
        for (int i = 0; i < n; i++) {
            days[bulbs[i] - 1] = i + 1;
        }
        int ans = Integer.MAX_VALUE;
        for (int left = 0, right = k + 1, mid = 1; right < n; mid++) {
            if (days[mid] <= Math.max(days[left], days[right])) {
                if (mid == right) {
                    ans = Math.min(ans, Math.max(days[left], days[right]));
                }
                left = mid;
                right = mid + k + 1;
            }
        }
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }
}
