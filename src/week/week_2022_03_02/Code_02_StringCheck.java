package week.week_2022_03_02;

/**
 * @ClassName Code_02_StringCheck
 * @Author Duys
 * @Description
 * @Date 2022/3/18 16:57
 **/
public class Code_02_StringCheck {
// 来自字节飞书团队
// 小歪每次会给你两个字符串：
// 笔记s1和关键词s2，请你写一个函数
// 判断s2的排列之一是否是s1的子串
// 如果是，返回true
// 否则，返回false

    // 窗口。字串，窗口
    // s1: a c v n b n m
    // s2: v n b
    public static boolean check(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return false;
        }
        if (s1.length() == s2.length()) {
            return s1.equals(s2);
        }
        // 对s2进行词频统计
        char[] str2 = s2.toCharArray();
        int[] count = new int[256];
        for (int i = 0; i < str2.length; i++) {
            count[str2[i]]++;
        }
        int m = str2.length;
        char[] str1 = s1.toCharArray();
        // 欠账记录
        int all = 0;
        int r = 0;
        // 形成窗口
        for (; r < m; r++) {
            if (count[str1[r]]-- <= 0) {
                all++;
            }
        }
        for (; r < str1.length; r++) {
            if (all == 0) {
                return true;
            }
            // 右边进一个，词频-1 ，小于=0 说明多欠债了一个
            if (count[str1[r]]-- <= 0) {
                all++;
            }
            // 左边吐一个，词频+1，小于
            if (count[str1[r - m]]++ < 0) {
                all--;
            }
        }
        return all == 0;
    }

}
