package custom.code_2022_07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode_368
 * @Author Duys
 * @Description
 * @Date 2022/7/21 10:35
 **/
// 368. 最大整除子集
// 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
//  answer[i] % answer[j] == 0 ，或
//  answer[j] % answer[i] == 0
//如果存在多个有效解子集，返回其中任何一个均可。
//链接：https://leetcode.cn/problems/largest-divisible-subset
public class LeetCode_368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return ans;
        }
        Arrays.sort(nums);
        int n = nums.length;
        // dp[i] 含义，表示最大是i的时候整数集元素最大多少个
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxSize = 1;
        int maxValue = dp[0];
        for (int i = 1; i < n; i++) {
            // 枚举每一个能和自己成为子集
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    // 再j的基础上多了i这个元素
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 当前推高了
            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxValue = nums[i];
            }
        }
        if (maxSize == 1) {
            ans.add(nums[0]);
            return ans;
        }
        for (int i = n - 1; i >= 0 && maxSize > 0; i--) {
            if (dp[i] == maxSize && maxValue % nums[i] == 0) {
                ans.add(nums[i]);
                maxValue = nums[i];
                maxSize--;
            }
        }
        return ans;
    }

    // 第二种解法
    public List<Integer> largestDivisibleSubset1(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return ans;
        }
        Arrays.sort(nums);
        int n = nums.length;
        // dp[i] 表示以 i位置数字结尾的最长整数子集长度
        int[] dp = new int[n];
        // gps[i]  记录dp[i]由哪一个位置转移来的
        int[] gps = new int[n];
        for (int i = 0; i < n; i++) {
            // 至少包含了子集
            int len = 1;
            int pre = i;
            // 枚举
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[j] + 1 > len) {
                        len = dp[j] + 1;
                        pre = j;
                    }
                }
            }
            // 记录
            dp[i] = len;
            gps[i] = pre;
        }
        // 获取最大长度和下标
        int max = -1;
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (dp[i] > max) {
                index = i;
                max = gps[i];
            }
        }
        while (ans.size() != max) {
            ans.add(nums[index]);
            index = gps[index];
        }
        return ans;
    }
}
