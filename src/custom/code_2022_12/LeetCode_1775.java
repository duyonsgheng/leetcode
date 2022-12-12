package custom.code_2022_12;

/**
 * @ClassName LeetCode_1775
 * @Author Duys
 * @Description
 * @Date 2022/12/7 10:18
 **/
// 1775. 通过最少操作次数使数组的和相等
public class LeetCode_1775 {
    public int minOperations(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        if (6 * n < m || 6 * m < n) {
            return -1;
        }
        int diff = 0;
        for (int i : nums1) {
            diff += i;
        }
        for (int i : nums2) {
            diff -= i;
        }
        // 如果diff大于0，说明nums1中的和大于nums2，为了统一流程，让nums1中变大，nums2中变小，交换一次
        if (diff > 0) {
            diff = -diff;
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }
        int[] cnt = new int[6];
        // nums1中的数变成6
        for (int x : nums1) {
            cnt[6 - x]++;
        }
        // nums2中的数变成1
        for (int x : nums2) {
            cnt[x - 1]++;
        }
        // 枚举变化的可能 5到1
        for (int i = 5, ans = 0; ; i--) {
            if (i * cnt[i] >= diff) {
                return ans + (diff + i - 1) / i;
            }
            ans += cnt[i];
            diff -= i * cnt[i];
        }
    }

    public int process(int[] arr1, int[] arr2, int diff) {
        int[] cnt = new int[7];
        for (int i = 1; i < 7; i++) {
            cnt[6 - i] += arr1[i];
            cnt[i - 1] += arr2[i];
        }
        int ans = 0;
        for (int i = 5; i > 0 && diff > 0; i--) {
            int t = Math.min((diff + i - 1) / i, cnt[i]);
            ans += t;
            diff -= t * i;
        }
        return ans;
    }
}
