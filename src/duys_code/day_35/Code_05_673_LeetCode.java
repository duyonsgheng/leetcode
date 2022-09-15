package duys_code.day_35;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @ClassName Code_05_673_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/
 * @Date 2021/12/8 13:30
 **/
public class Code_05_673_LeetCode {
    // 给定一个未排序的整数数组，找到最长递增子序列的个数。
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        List<TreeMap<Integer, Integer>> dp = new ArrayList<>();
        int len = 0;
        int count = 0;
        for (int num : nums) {
            // num之前的长度
            len = search(dp, num);
            // 之前长度是0，count现在有一个了
            if (len == 0) {
                count = 1;
            } else {
                // 之前是有的
                TreeMap<Integer, Integer> map = dp.get(len - 1);
                // 一定要减去大于num的这一部分，
                count = map.firstEntry().getValue() - (map.ceilingEntry(num) != null ? map.get(map.ceilingKey(num)) : 0);
            }
            // 如果当前len来到了最后了，需要新加一个
            if (len == dp.size()) {
                dp.add(new TreeMap<>());
                dp.get(len).put(num, count);
            } else {
                // 否则在之前的结构上补录，并且把当前满足的加上之前的
                dp.get(len).put(num, dp.get(len).firstEntry().getValue() + count);
            }
        }
        return dp.get(dp.size() - 1).firstEntry().getValue();
    }

    // 找到小于等于num的总共有多少个
    public static int search(List<TreeMap<Integer, Integer>> dp, int num) {
        int l = 0;
        int r = dp.size() - 1;
        int m = 0;
        int ans = dp.size();
        while (l <= r) {
            m = (l + r) / 2;
            if (dp.get(m).firstKey() >= num) {
                r = m - 1;
                ans = m;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
