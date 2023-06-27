package custom.code_2023_05;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @ClassName LeetCode_1839
 * @Author Duys
 * @Description
 * @Date 2023/5/29 13:33
 **/
// 1839. 所有元音按顺序排布的最长子字符串
public class LeetCode_1839 {
    // 窗口
    public int longestBeautifulSubstring(String word) {
        int n = word.length();
        // a e i o u
        int l = 0, r = 0, ans = 0;
        Stack<Integer> stack = new Stack<>();
        Set<Character> set = new HashSet<>();
        while (r < n) {
            if (stack.isEmpty() || word.charAt(r) >= word.charAt(stack.peek())) {
                stack.push(r);
                set.add(word.charAt(r));
                if (set.size() == 5) {
                    ans = Math.max(ans, stack.size());
                }
            } else {
                stack.clear();
                set.clear();
                l = r; // 接着当前位置往后尝试
                set.add(word.charAt(l));
                stack.push(l);
            }
            r++;
        }
        return ans;
    }
}
