package duys_code.day_38;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_07_763_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/partition-labels/
 * @Date 2021/12/20 14:00
 **/
public class Code_07_763_LeetCode {
    // 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表

    // 记录每一个字符出现的最晚位置
    public List<Integer> partitionLabels(String s) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() <= 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        int[] indexMap = new int[26];
        for (int i = 0; i < n; i++) {
            indexMap[str[i] - 'a'] = i;
        }

        int left = 0;
        int right = indexMap[str[0] - 'a'];
        for (int i = 1; i < n; i++) {
            // 如果当前的位置都出现在了之前字符最晚位置的后面了，说明需要新开一个段了
            if (i > right) {
                ans.add(right - left + 1);
                left = i;
            }
            // 看看结束位置能不能被推高
            right = Math.max(right, indexMap[str[i] - 'a']);
        }
        // 别忘记了最后一个位置还没收集
        ans.add(right - left + 1);
        return ans;
    }
}
