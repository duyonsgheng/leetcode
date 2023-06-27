package week.week_2023_06_02;

/**
 * @ClassName Code_03_LeetCode_646
 * @Author Duys
 * @Description
 * @Date 2023/6/15 9:44
 **/
// 316. 去除重复字母
// https://leetcode.cn/problems/remove-duplicate-letters/
public class Code_03_LeetCode_316 {
    // 单调栈，必须大压小，如果违反了，就讨论后续还存在相应元素不
    public String removeDuplicateLetters(String s) {
        int[] cnt = new int[26];
        boolean[] visited = new boolean[26];
        for (int i = 0; i < s.length(); i++)
            cnt[s.charAt(i) - 'a']++;
        // 栈
        char[] stack = new char[26];
        int size = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            // 没用过
            if (!visited[cur - 'a']) {
                // 如果当前元素违反了 大压小的规则，那么就看看栈顶元素后面还有没有，如果还有，可以弹出，如果没有，就不能弹出了
                while (size > 0 && stack[size - 1] > cur && cnt[stack[size - 1] - 'a'] > 0) {
                    visited[stack[size - 1] - 'a'] = false;
                    size--;
                }
                stack[size++] = cur;
                visited[cur - 'a'] = true;
            }
            // 当前位置的数可算用了一次
            cnt[cur - 'a']--;
        }
        return new String(stack, 0, size);
    }
}
