package duys_code.day_35;

/**
 * @ClassName Code_01_StringKth
 * @Author Duys
 * @Description
 * @Date 2021/12/7 17:41
 **/
public class Code_07_StringKth {
    // 给定一个长度len，表示一共有几位
    // 所有字符都是小写(a~z)，可以生成长度为1，长度为2， 长度为3...长度为len的所有字符串
    // 如果把所有字符串根据字典序排序，每个字符串都有所在的位置。
    // 给定一个字符串str，给定len，请返回str是总序列中的第几个
    // 比如len = 4，字典序的前几个字符串为:
    // a aa aaa aaaa aaab ... aaaz ... azzz b ba baa baaa ... bzzz c ...
    // a是这个序列中的第1个，bzzz是这个序列中的第36558个

    // 思路：
    // cdb，总共长度为7，请问cdb是第几个？
    // 第一位c :
    // 以a开头，剩下长度为(0~6)的所有可能性有几个
    // +
    // 以b开头，剩下长度为(0~6)的所有可能性有几个
    // +
    // 以c开头，剩下长度为(0)的所有可能性有几个
    // 第二位d :
    // +
    // 以ca开头的情况下，剩下长度为(0~5)的所有可能性有几个
    // +
    // 以cb开头的情况下，剩下长度为(0~5)的所有可能性有几个
    // +
    // 以cc开头的情况下，剩下长度为(0~5)的所有可能性有几个
    // +
    // 以cd开头的情况下，剩下长度为(0)的所有可能性有几个
    // 第三位b
    // +
    // 以cda开头的情况下，剩下长度为(0~4)的所有可能性有几个
    // +
    // 以cdb开头的情况下，剩下长度为(0)的所有可能性有几个
    public static int kth(String s, int len) {
        if (s == null || s.length() <= 0 || len > s.length()) {
            return 0;
        }
        char[] str = s.toCharArray();
        int ans = 0;
        for (int i = 0, rest = len - 1; i < str.length; i++, rest--) {
            ans += (str[i] - 'a') * process(rest) + 1;
        }
        return ans;
    }

    // 不管以什么开头，剩下的长度0到len一共多少个
    // 例如 a开头 剩下长度4 a-0 1个
    // a x - 26
    // a x x - 26*26
    public static int process(int len) {
        int ans = 1;
        for (int i = 1, base = 26; i <= len; i++, base *= 26) {
            ans += base;
        }
        return ans;
    }

}
