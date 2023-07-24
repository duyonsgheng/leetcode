package custom.code_2023_07;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1985
 * @date 2023年07月24日
 */
// 1985. 找出数组中的第 K 大整数
// https://leetcode.cn/problems/find-the-kth-largest-integer-in-the-array/
public class LeetCode_1985 {

    public String kthLargestNumber(String[] nums, int k) {
        // 就一个自定义排序
        Arrays.sort(nums, (a, b) -> {
            if (a.length() > b.length()) {
                return -1;
            } else if (a.length() < b.length()) {
                return 1;
            } else {
                // 谁大谁排在后面去
                return b.compareTo(a);
            }
        });
        return nums[k - 1];
    }
}
