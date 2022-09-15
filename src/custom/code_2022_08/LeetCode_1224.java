package custom.code_2022_08;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1224
 * @Author Duys
 * @Description
 * @Date 2022/8/18 9:02
 **/
// 1224. 最大相等频率
// 给你一个正整数数组nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回该前缀的长度：
//从前缀中 恰好删除一个 元素后，剩下每个数字的出现次数都相同。
//如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。
//链接：https://leetcode.cn/problems/maximum-equal-frequency
public class LeetCode_1224 {

    /**
     * nums = [2,2,1,1,5,3,3,5] -> 对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4] = 5，就可以得到 [2,2,1,1,3,3]，里面每个数字都出现了两次。
     * nums = [1,1,1,2,2,2,3,3,3,4,4,4,5] -> 就是数组本身
     */
    // 先词频统计
    public static int maxEqualFreq(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        // nums中数出现的次数
        Map<Integer, Integer> count = new HashMap<>();
        // 出现次数的数有几个
        Map<Integer, Integer> freq = new HashMap<>();
        int ans = 0;
        int maxFreq = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 当前数已经出现了
            // [2,2,1,1,5,3,3,5]
            // 比如之前出现的2 一次，现在2来了。那么之前出现1次的就应该-去当前这个数了，因为当前这个数，马上就是出现2次了
            if (count.getOrDefault(nums[i], 0) > 0) {
                freq.put(count.get(nums[i]), freq.get(count.get(nums[i])) - 1);
            }
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            maxFreq = Math.max(count.get(nums[i]), maxFreq);
            freq.put(count.get(nums[i]), freq.getOrDefault(count.get(nums[i]), 0) + 1);
            boolean ok =
                    // 所有数出现次数都是1
                    maxFreq == 1 ||
                            // 所有数出现次数都是maxFreq 或者maxFreq-1，并且出现次数最多的数只有一个，那么删除这个数就好了
                            (freq.get(maxFreq) * maxFreq + freq.get(maxFreq - 1) * (maxFreq - 1) == i + 1) && (freq.get(maxFreq) == 1)
                            ||
                            // 只有一个数出现次数是1，其他的数出现次数都是相同的
                            (freq.get(maxFreq) * maxFreq + 1 == i + 1 && freq.get(1) == 1);
            if (ok) {
                ans = Math.max(ans, i + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 1, 5, 3, 3, 5};
        maxEqualFreq(arr);
    }
}
