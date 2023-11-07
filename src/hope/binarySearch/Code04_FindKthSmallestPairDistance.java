package hope.binarySearch;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code04_FindKthSmallestPairDistance
 * @date 2023年11月01日 10:11
 */
// 找出第K小的数对距离
// 数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
// 给你一个整数数组 nums 和一个整数 k
// 数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length
// 返回 所有数对距离中 第 k 小的数对距离。
// 测试链接 : https://leetcode.cn/problems/find-k-th-smallest-pair-distance/
public class Code04_FindKthSmallestPairDistance {
    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int l = 0, r = nums[n - 1] - nums[0], m; l <= r; ) {
            m = l + ((r - l) >> 1);
            if (find(nums, m) >= k) {
                ans = m;
                r = m - 1;
            } else l = m + 1;
        }
        return ans;
    }

    //arr中任意两数的差值 <= limit
    public int find(int[] arr, int limit) {
        int ans = 0;
        for (int l = 0, r = 0; l < arr.length; l++) {
            // 找到不符合的数据
            while (r + 1 < arr.length && arr[r + 1] - arr[l] <= limit) {
                r++;
            }
            // 那么 l到r这之间的数都满足差值小于等于limit的
            ans += r - l;
        }
        return ans;
    }
}
