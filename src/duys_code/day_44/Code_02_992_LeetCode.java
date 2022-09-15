package duys_code.day_44;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_02_992_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/subarrays-with-k-different-integers/
 * @Date 2022/1/6 13:30
 **/
public class Code_02_992_LeetCode {
    // 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定不同的子数组为好子数组


    // 两个窗口
    // 一个窗口维持 k-1 种数
    // 一个窗口位置 k种数
    public int subarraysWithKDistinct(int[] nums, int k) {
        int n = nums.length;
        int[] lessCounts = new int[n + 1];// k-1种数的词频统计
        int[] equalCounts = new int[n + 1];// k种数的词频统计

        int lessLeft = 0;
        int equalLeft = 0;
        int lessKinds = 0;
        int equalKinds = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 表示一个新得词频出现了，窗口内的词频个数需要增加
            if (lessCounts[nums[i]] == 0) {
                lessKinds++;
            }
            if (equalCounts[nums[i]] == 0) {
                equalKinds++;
            }
            // 词频统计
            lessCounts[nums[i]]++;
            equalCounts[nums[i]]++;
            // 这个只维持k-1个词的词频，当达到了k，说明需要缩窗口了
            while (lessKinds == k) {
                if (lessCounts[nums[lessLeft]] == 1) {
                    lessKinds--;
                }
                lessCounts[nums[lessLeft++]]--;
            }
            while (equalKinds > k) {
                if (equalCounts[nums[equalLeft]] == 1) {
                    equalKinds--;
                }
                equalCounts[nums[equalLeft++]]--;
            }
            ans += lessLeft - equalLeft;
        }
        return ans;
    }

    // 贪心的解法
    // 我们算出整个数组有多少子数组的词频是 <= k 的
    // 再找出整个数组有多少个子数组的词频是 <= k-1 的
    // 用 <= k 的 - <= K-1的就是 = k的了
    public static int subarraysWithKDistinct2(int[] arr, int k) {
        return process(arr, k) - process(arr, k - 1);
    }

    // 1 1 2 2 3 3 4 4  k=3
    // 和窗口一样的道理
    public static int process(int[] arr, int k) {
        int i = 0;
        int ans = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int j = 0; j < arr.length; j++) {
            if (count.getOrDefault(arr[j], 0) == 0) {
                k--;
            }
            // 把当前的词频+1
            count.put(arr[j], count.getOrDefault(arr[j], 0) + 1);
            while (k < 0) {
                count.put(arr[i], count.get(arr[i]) - 1);
                if (count.get(arr[i]) == 0) {
                    k++;
                }
                i++;
            }
            ans += j - i + 1;
        }
        return ans;
    }
}
