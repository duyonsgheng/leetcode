package duys_code.day_02;

/**
 * @ClassName Code_06_SortChild
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
 * @Date 2021/9/15 16:35
 **/
public class Code_06_SortChild {
    /**
     * 给定一个数组arr，只能对arr中的一个子数组排序，
     * 但是想让arr整体都有序
     * 返回满足这一设定的子数组中，最短的是多长
     */
    /**
     * 例如： 4，3，6，2，1，0，8，9
     * 那么只需要对 4，3，6，2，1，0 排序，因为  8 9 已经有序
     */
    // 从左往右遍历，用一个maxLeft 记录左边的最大值在哪一个位置，从1位置开始，因为1位置的左边最大是0位置的数，记录最右的违规位置
    // 从右往左，记录最左的违规位置
    // 那么需要排序的位置就是 左边违规位置 到 右边违规的位置
    public static int findMinLengthByArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int N = nums.length;
        // 从左往右，最左最开始在-1位置
        int right = -1;
        int maxLeft = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            // 找到最右的没有乱序的位置的前一个 比如 1 2 3 6 5 4 7 8 9 那么right 就会停在 4 这个数的下标上
            if (maxLeft > nums[i]) {
                right = i;
            }
            maxLeft = Math.max(maxLeft, nums[i]);
        }

        int left = N;
        int minRight = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // 找到最左的没有乱序的位置的后一个 比如 1 2 3 6 5 4 7 8 9 那么right 就会停在 6 这个数的下标上
            if (minRight < nums[i]) {
                left = i;
            }
            minRight = Math.min(minRight, nums[i]);
        }
        return Math.max(0, right - left + 1);
    }
}
