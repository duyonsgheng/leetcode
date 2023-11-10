package hope.monotoneStack;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName Code07_RemoveDuplicateLetters
 * @date 2023年11月07日 11:48
 */
// 去除重复字母保证剩余字符串的字典序最小
// 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次
// 需保证 返回结果的字典序最小
// 要求不能打乱其他字符的相对位置
// 测试链接 : https://leetcode.cn/problems/remove-duplicate-letters/
public class Code07_RemoveDuplicateLetters {
    public static int MAXN = 26;

    // 每种字符词频
    public static int[] cnts = new int[MAXN];

    // 每种字符目前有没有进栈
    public static boolean[] enter = new boolean[MAXN];

    // 单调栈
    public static char[] stack = new char[MAXN];

    public static int r;

    // 统计一下词频
    // 栈里保持大压小，如果发现当前位置比栈顶更小，看看后面是否还有栈顶元素，如果有则可以弹出栈顶
    public static String removeDuplicateLetters(String str) {
        r = 0;
        Arrays.fill(cnts, 0);
        Arrays.fill(enter, false);
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            cnts[c - 'a']++;
        }
        for (char c : charArray) {
            if (!enter[c - 'a']) {
                // 栈顶元素比当前元素大，并且栈顶元素在后面还能收集，则后面去收集栈顶元素
                while (r > 0 && stack[r - 1] > c && cnts[stack[r - 1] - 'a'] > 0) {
                    enter[stack[r - 1] - 'a'] = false;
                    r--;
                }
                stack[r++] = c;
                enter[c - 'a'] = true;
            }
            cnts[c - 'a']--;
        }
        return new String(stack, 0, r);
    }
}
