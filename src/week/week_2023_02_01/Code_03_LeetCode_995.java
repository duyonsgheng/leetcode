package week.week_2023_02_01;

/**
 * @ClassName Code_03_LeetCode_995
 * @Author Duys
 * @Description
 * @Date 2023/2/2 9:59
 **/
// 995. K 连续位的最小翻转次数
public class Code_03_LeetCode_995 {
    // 思路：一个贪心策略。最左前缀的优先处理
    // 比如 0 0 1 1 0 1 0 1 k=3
    // 先把前缀为0的变成1 -> 1 1 0 1 0 1 0 1
    // 然后在把前缀为0的位置开始变成1....
    // 使用一个双端队列，队列的首位置表示为0的位置，然后下一个位置是表示1的，
    // 如果存在和前一个位置相同的则不需要加入队列，保证队列里的数组长度保持在k，那么就会发现队列的长度是奇数，那么最后一个位置表示0，为偶数，那么最后一个位置一定是1.
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int[] queue = new int[n];
        int l = 0, r = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            // 队列里面有元素，且队列里面存在的元素表示的位置满足长度为k了
            // 反转。这一部分，然后队头出队
            if (l != r && i - queue[l] == k) {
                l++;
            }
            // 当前队列里的元素长度是否是奇数
            if (((r - l + 1) & 1) != nums[i]) {
                queue[r++] = i;
                ans++;
            }
        }
        // 如果最后翻转的位置还剩下k，说明可以翻转完成。否则不能
        return (l != r && queue[r - 1] + k > n) ? -1 : ans;
    }
}
