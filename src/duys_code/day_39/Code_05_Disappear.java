package duys_code.day_39;

/**
 * @ClassName Code_05_Disappear
 * @Author Duys
 * @Description
 * @Date 2021/12/21 11:31
 **/
public class Code_05_Disappear {

    // 一个子序列的消除规则如下:
    // 1) 在某一个子序列中，如果'1'的左边有'0'，那么这两个字符->"01"可以消除
    // 2) 在某一个子序列中，如果'3'的左边有'2'，那么这两个字符->"23"可以消除
    // 3) 当这个子序列的某个部分消除之后，认为其他字符会自动贴在一起，可以继续寻找消除的机会
    // 比如，某个子序列"0231"，先消除掉"23"，那么剩下的字符贴在一起变成"01"，继续消除就没有字符了
    // 如果某个子序列通过最优良的方式，可以都消掉，那么这样的子序列叫做“全消子序列”
    // 一个只由'0'、'1'、'2'、'3'四种字符组成的字符串str，可以生成很多子序列，返回“全消子序列”的最大长度
    // 字符串str长度 <= 200
    // 体系学习班，代码46节，第2题+第3题

    public static int maxLength(String s) {
        if (s == null || s.length() <= 0) {
            return 0;
        }
        return process(s.toCharArray(), 0, s.length() - 1);
    }

    //
    public static int process(char[] str, int L, int R) {
        // 如果L>=R 是无效的
        if (L >= R) {
            return 0;
        }
        // 只有两个字符了
        if (L == R - 1) {
            return (str[L] == '0' && str[R] == '1') || (str[L] == '2' && str[R] == '3') ? 2 : 0;
        }

        // 多余两种字符
        // 完全不考虑L位置
        int p1 = process(str, L + 1, R);
        // 必须考虑L位置
        // 如果L位置的字符都不是0或者不是2，那么
        if (str[L] == '1' || str[L] == '3') {
            return p1;
        }
        int p2 = 0;
        // 当前开始的字符是啥，需要找配对的字符是啥
        char find = str[L] == '0' ? '1' : '3';
        for (int i = L + 1; i <= R; i++) {
            if (str[i] == find) {
                // 找到了其中的一个字符，如果L+1到当前i-1位置字符 和 i+1 到 R 以及L位置和i位置两个字符，总共的长度
                p2 = Math.max(p2, process(str, L + 1, i - 1) + 2 + process(str, i + 1, R));
            }
        }
        return Math.max(p1, p2);
    }
}
