package duys_code.day_36;

/**
 * @ClassName Code_03_MatchCount
 * @Author Duys
 * @Description
 * @Date 2021/12/13 13:33
 **/
public class Code_03_MatchCount {
    // 来自美团
    // 给定两个字符串s1和s2
    // 返回在s1中有多少个子串等于s2
    // kmp的具体用法
    public static int contains(String s1, String s2) {
        return count(s1.toCharArray(), s2.toCharArray());
    }

    public static int count(char[] str1, char[] str2) {
        int x = 0;
        int y = 0;
        int count = 0;
        int[] next = getNextArray(str2);
        while (x < str1.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
                if (y == str2.length) {
                    count++;
                    y = next[y];
                }
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return count;
    }

    // kmp
    // next数组多求一位
    // 比如：str2 = aaaa
    // 那么，next = -1,0,1,2,3
    // 最后一个3表示，终止位置之前的字符串最长前缀和最长后缀的匹配长度
    // 也就是next数组补一位
    public static int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1, 0};
        }
        int[] next = new int[str2.length + 1];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < str2.length) {
            if (str2[i - 1] == str2[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
