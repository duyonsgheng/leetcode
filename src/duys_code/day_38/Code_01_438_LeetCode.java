package duys_code.day_38;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Code_01_438_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 * @Date 2021/12/20 13:07
 **/
public class Code_01_438_LeetCode {
    // 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
    //  s = "cbaebabacd", p = "abc"  -> [0,6]
    // 具有单调性，s逐渐变长，字符的种类只可能不变或者增加，使用滑动窗口，经典的欠债模型
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return ans;
        }
        char[] str = s.toCharArray();
        char[] tar = p.toCharArray();
        int n = str.length;
        int m = tar.length;
        // 窗口的大小就是m
        // 统计词频
        Map<Character, Integer> map = new HashMap<>();
        for (char t : tar) {
            if (!map.containsKey(t)) {
                map.put(t, 1);
            } else
                map.put(t, map.get(t) + 1);
        }
        // 初始化窗口大小
        int all = m;
        // 窗口右边进一个，说明还了一个
        for (int end = 0; end < m - 1; end++) {
            if (!map.containsKey(str[end])) {
                continue;
            }
            int curCount = map.get(str[end]);
            if (curCount > 0) {
                all--;
            }
            map.put(str[end], curCount - 1);
        }
        // right 窗口的右边界
        // left  窗口的左边界
        for (int right = m - 1, left = 0; right < n; right++, left++) {
            if (map.containsKey(str[right])) {
                int curCount = map.get(str[right]);
                if (curCount > 0) {
                    all--;
                }
                map.put(str[right], curCount - 1);
            }
            if (all == 0) {
                ans.add(left);
            }
            // 左边该出去了，出去的时候该欠着了，如果欠的次数>0了，说明all需要增加了
            if (map.containsKey(str[left])) {
                int count = map.get(str[left]);
                if (count >= 0) {
                    all++;
                }
                map.put(str[left], count + 1);
            }
        }
        return ans;
    }
}
