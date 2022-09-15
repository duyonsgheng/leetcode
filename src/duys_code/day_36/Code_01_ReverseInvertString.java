package duys_code.day_36;

/**
 * @ClassName Code_01_ReverseInvertString
 * @Author Duys
 * @Description
 * @Date 2021/12/13 13:08
 **/
public class Code_01_ReverseInvertString {
    // 来自网易
    // 规定：L[1]对应a，L[2]对应b，L[3]对应c，...，L[25]对应y
    // S1 = a
    // S(i) = S(i-1) + L[i] + reverse(invert(S(i-1)));
    // 解释invert操作：
    // S1 = a
    // S2 = aby
    // 假设invert(S(2)) = 甲乙丙
    // a + 甲 = 26, 那么 甲 = 26 - 1 = 25 -> y
    // b + 乙 = 26, 那么 乙 = 26 - 2 = 24 -> x
    // y + 丙 = 26, 那么 丙 = 26 - 25 = 1 -> a
    // 如上就是每一位的计算方式，所以invert(S2) = yxa
    // 所以S3 = S2 + L[3] + reverse(invert(S2)) = aby + c + axy = abycaxy
    // invert(abycaxy) = yxawyba, 再reverse = abywaxy
    // 所以S4 = abycaxy + d + abywaxy = abycaxydabywaxy
    // 直到S25结束
    // 给定两个参数n和k，返回Sn的第k位是什么字符，n从1开始，k从1开始
    // 比如n=4，k=2，表示S4的第2个字符是什么，返回b字符

    public static int[] lens = null;// 记录s1 ~ s25 每一个串的长度

    public static void fillLens() {
        lens = new int[26];
        lens[1] = 1;
        for (int i = 2; i < 26; i++) {
            lens[i] = (lens[i - 1] << 1) + 1;
        }
    }

    // 求s n中的第k个字符是啥
    // 我们已知 s n 是由s n-1 + l n + s n-1 转化后的反转
    // 所以我们看看k来自于s n的哪一个区域 k<= s n-1 长度 一定在左半区， k = s n-1 + 1 那就是ln  k > s n-1 +1 一定在右半区
    public static char kth(int n, int k) {
        if (n == 1) {
            return 'a';
        }
        // s n-1 的长度
        int half = lens[n - 1];
        if (k <= half) {
            // 左半区，继续去递归吧
            return kth(n - 1, k);
        } else if (k == half + 1) {
            return (char) ('a' + n - 1);
        } else {
            // 右半区很特殊，需要先反转，然后再invert
            // 比如再左半区的第k，那么在右半区，相当于从 n-1 位置往前数k
            //  1 2 3 4 5 6 x a b c d e f
            // 找第11个 k = 11 ，half = 6
            return invert(kth(n - 1, ((half + 1) << 1) - k));
        }
    }

    public static char invert(char c) {
        return (char) (('a' << 1) + 24 - c);
    }
}
