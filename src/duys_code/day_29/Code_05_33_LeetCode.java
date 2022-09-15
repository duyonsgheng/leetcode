package duys_code.day_29;

/**
 * @ClassName Code_01_33_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 * @Date 2021/11/23 17:41
 **/
public class Code_05_33_LeetCode {
    //整数数组 nums 按升序排列，数组中的值 互不相同 。
    //在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
    //使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如，
    //[0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
    //给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。

    // 二分法
    // L ... M ...R
    // 如果l位置得数小于了M位置得数
    public int search(int[] nums, int target) {
        int L = 0;
        int M = 0;
        int R = nums.length - 1;
        while (L <= R) {
            M = L + ((R - L) >> 1);
            if (nums[M] == target) {
                return M;
            }
            // 三个位置得数已经相等了
            if (nums[L] == nums[M] && nums[M] == nums[R]) {
                // 找到不等或者直到L == M
                while (L != M && nums[L] == nums[M]) {
                    L++;
                }
                // L=M了，那么就要到M+1 ~R范围上去继续了
                if (L == M) {
                    L = M + 1;
                    continue;
                }
            }
            // 三个位置的数不都一样，至少有一个和其他两个位置的数不同
            if (nums[L] != nums[M]) { // M位置 = R位置
                // 如果 中点位置都大于了L了，那么说明区域划大了
                if (nums[M] > nums[L]) { // 这里也说明再L到M的区域上，虽然数组经过转动了，但是L到M范围上一定是有序的，断点不在这区间
                    if (target >= nums[L] && target < nums[M]) {
                        R = M - 1;
                    } else {
                        L = M + 1;
                    }
                }
                // 说明L位置大于了M位置了，那么说明断点再这区间
                else {
                    if (target > nums[M] && target <= nums[R]) {
                        L = M + 1;
                    } else {
                        R = M - 1;
                    }
                }
            }
            //  M位置！=R位置
            else {
                if (nums[M] < nums[R]) {
                    if (target > nums[M] && target <= nums[R]) {
                        L = M + 1;
                    } else {
                        R = M - 1;
                    }
                } else {
                    if (target >= nums[L] && target < nums[M]) {
                        R = M - 1;
                    } else {
                        L = M + 1;
                    }
                }
            }
        }
        return -1;
    }
}
