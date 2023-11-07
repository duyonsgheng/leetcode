package hope.binarySearch;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code02_SplitArrayLargestSum
 * @date 2023年11月01日 9:39
 */
// 分割数组的最大值(画匠问题)
// 给定一个非负整数数组 nums 和一个整数 m
// 你需要将这个数组分成 m 个非空的连续子数组。
// 设计一个算法使得这 m 个子数组各自和的最大值最小。
// 测试链接 : https://leetcode.cn/problems/split-array-largest-sum/
public class Code02_SplitArrayLargestSum {
    public int splitArray(int[] nums, int k) {
        long sum = Arrays.stream(nums).sum();
        long ans = 0;
        for (long l = 0, r = sum, m; l <= r; ) {
            m = l + ((r - l) >> 1);
            // 每一部分的累加和都小于等于m，且划分的部分小于等于k
            // 达标
            if (find(nums, m) <= k) {
                ans = m;
                r = m - 1;
            } else l = m + 1;
        }
        return (int) ans;
    }

    // 必须让数组每一部分的累加和 <= limit，请问必须划分为几个部分
    public int find(int[] arr, long limit) {
        int parts = 1;
        int sum = 0;
        for (int num : arr) {
            if (num > limit) {
                return Integer.MAX_VALUE;
            }
            // 如果和大于了limit，则需要新开一个数组
            if (sum + num > limit) {
                parts++;
                sum = num;
            } else {
                sum += num;
            }
        }
        return parts;
    }
}
