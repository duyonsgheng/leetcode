package duys_code.day_37;

/**
 * @ClassName Code_04_394_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/decode-string/
 * @Date 2021/12/15 13:54
 **/
public class Code_04_394_LeetCode {
    // 转字符串 比如3[2[b3[a]]]
    public String decodeString(String s) {
        return process(s.toCharArray(), 0).ans;
    }

    // 当前来到了什么位置
    // 当遇到右括号或者字符串结束就停下
    public static Info process(char[] str, int index) {
        int count = 0;
        StringBuilder ans = new StringBuilder();
        while (index < str.length && str[index] != ']') {
            if ((str[index] >= 'a' && str[index] <= 'z') || (str[index] >= 'A' && str[index] <= 'Z')) {
                ans.append(str[index++]);
            } else if (str[index] >= '0' && str[index] <= '9') {
                count = count * 10 + str[index++] - '0';
            } else {
                // 只可能是左括号
                // 遇到左括号了，我需要向我的后续要答案
                Info next = process(str, index + 1);
                ans.append(gerStr(count, next.ans));
                // 这一轮的count已经用过了，需要归0了，也是一个深度优先遍历
                count = 0;
                index = next.end + 1;
            }
        }
        return new Info(ans.toString(), index);
    }

    public static String gerStr(int count, String str) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < count; i++) {
            ans.append(str);
        }
        return ans.toString();
    }

    public static class Info {
        // 当前的答案
        public String ans;
        // 当前处理到什么位置了
        public int end;

        public Info(String a, int e) {
            ans = a;
            end = e;
        }
    }
}
