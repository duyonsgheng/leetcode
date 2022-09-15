package week.week_2022_07_03;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_04_LeetCode_761
 * @Author Duys
 * @Description
 * @Date 2022/7/21 9:51
 **/
// 761. 特殊的二进制序列
// 其实就是一个括号嵌套问题 ，把 二进制1 当成左括号，0当成右括号
public class Code_04_LeetCode_761 {
    // 我们主函数怎么调用、
    // 当遇到右括号的时候，我去向我的下一个位置要子过程，子过程返回两个值，一个是值是多少，一个是算到什么位置了
    public static String makeLargestSpecial(String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        List<String> ans = new ArrayList<>();
        char[] str = s.toCharArray();
        int n = str.length;
        for (int index = 0; index < n; ) {
            Info info = process(str, index + 1);
            ans.add(info.ans);
            index = info.end + 1;
        }
        StringBuilder builder = new StringBuilder();
        ans.sort((a, b) -> b.compareTo(a));
        for (String cur : ans) {
            builder.append(cur);
        }
        return builder.toString();
    }

    public static Info process(char[] str, int index) {
        List<String> ans = new ArrayList<>();
        // 遇到0就停下 ，否则我继续向我的子过程要信息
        while (index < str.length && str[index] != '0') {
            Info info = process(str, index + 1);
            ans.add(info.ans);
            index = info.end + 1;
        }
        StringBuilder builder = new StringBuilder();
        ans.sort((a, b) -> b.compareTo(a));
        for (String s : ans) {
            builder.append(s);
        }
        // 返回我的信息。我从1开始算的，从0位置结束的，整个区间范围内都准备好
        return new Info("1" + builder.toString() + "0", index);
    }

    public static class Info {
        public String ans;
        public int end;

        public Info(String a, int e) {
            ans = a;
            end = e;
        }
    }
}
