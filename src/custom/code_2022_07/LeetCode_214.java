package custom.code_2022_07;

/**
 * @ClassName LeetCode_214
 * @Author Duys
 * @Description
 * @Date 2022/7/7 9:18
 **/
//214. 最短回文串
// 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
// https://leetcode.cn/problems/shortest-palindrome/
public class LeetCode_214 {

    public static void main(String[] args) {
        String str = "abcd";
        // # a # a # c # e # c #  a  #  a  #  a  #
        // 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
        // i = 7 奇数 (i-1)/2
        // i
        System.out.println(shortestPalindrome(str));
    }

    // aacecaaa -> aaacecaaa
    // abcd
    // manacher 计算每一个位置的回文半径，记录最大的回文半径位置，然后往前推
    // coding
    public static String shortestPalindrome(String s) {
        char[] source = s.toCharArray();
        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int R = -1;
        int C = -1;
        int ans = Integer.MIN_VALUE;
        int index = 0; // 取得最大的时候中心点
        int sourceP = 0;// 当manacher取得最大的时候，在原串中的中心点
        int manacherP = 0;// 当manacher取得最大的时候半径
        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1
                    && str[i + pArr[i]] == str[i - pArr[i]]) {
                pArr[i]++;
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            if (pArr[i] > ans) {
                index = i;
                ans = pArr[i];
                if (ans == index + 1) {
                    sourceP = index >> 1;
                    manacherP = pArr[i] >> 1;
                }
            }
        }
        int right = sourceP + manacherP;
        int len = source.length - right;
        int n = source.length - 1;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < len; i++) {
            buffer.append(source[n--]);
        }
        return buffer.toString() + s;
    }

    // 得出s的回文半径
    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[charArr.length * 2 + 1];
        int index = 0;
        // 再所有的奇数位置添加 '#'
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }
}
