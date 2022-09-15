package week.week_2022_08_03;

/**
 * @ClassName Code_05_LeetCode_798
 * @Author Duys
 * @Description
 * @Date 2022/8/18 17:56
 **/
// 798. 得分最高的最小轮调
public class Code_05_LeetCode_798 {
    // 差分的应用
    // 任何值小于或等于其索引的项都可以记作一分
    public int bestRotation(int[] nums) {
        int n = nums.length;
        // 整体向右移动 不同步数得到的总分数
        int[] count = new int[n + 2];
        for (int i = 0; i < n; i++) {
            if (nums[i] >= n) {
                continue;
            }
            // 如果我的编号小于了实际的值，那么向右移动的时候只有一部分
            if (i <= nums[i]) {
                add(count, nums[i] - i, n - i - 1);
            } else {
                // 两部分
                add(count, 0, n - i - 1);
                add(count, n - i + nums[i], n - 1);
            }
        }
        for (int i = 1; i <= n; i++) {
            count[i] += count[i - 1];
        }
        // 最大得分是啥！已经求出来了
        int max = count[0];
        int ans = 0;
        for (int i = n - 1; i >= 1; i--) {
            // 整体移动的i 0 n-1 n-2 n-3 1
            //         k 0  1   2   3   n-1
            // 实际求的是向左移动，而我们解答是向右移动
            if (count[i] > max) {
                max = count[i];
                ans = i;
            }
        }
        return ans == 0 ? 0 : (n - ans);
    }

    // 就是差分的做法
    public static void add(int[] cnt, int l, int r) {
        cnt[l]++;
        cnt[r + 1]--;
    }
}
