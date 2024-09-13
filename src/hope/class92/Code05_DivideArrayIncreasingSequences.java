package hope.class92;

/**
 * @author Mr.Du
 * @ClassName Code05_DivideArrayIncreasingSequences
 * @date 2024年09月10日 下午 04:21
 */
// 将数组分成几个递增序列
// 给你一个有序的正数数组 nums 和整数 K
// 判断该数组是否可以被分成一个或几个 长度至少 为 K 的 不相交的递增子序列
// 数组中的所有数字，都要被，若干不相交的递增子序列包含
// 测试链接 : https://leetcode.cn/problems/divide-array-into-increasing-sequences/
public class Code05_DivideArrayIncreasingSequences {

    // 看看最大词频是多少，如果每个数都要进入一个子序列中，并且子序列还是递增的

    public static boolean canDivideIntoSubsequences(int[] nums, int k) {
        int cnt = 1;
        int maxCnt = 1; // 最大词频
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                maxCnt = Math.max(maxCnt, cnt);
                cnt = 1;
            } else cnt++;
        }
        maxCnt = Math.max(maxCnt, cnt);
        return nums.length / maxCnt >= k;
    }
}
