package custom.code_2022_11;

import java.util.BitSet;

/**
 * @ClassName LeetCode_1016
 * @Author Duys
 * @Description
 * @Date 2022/11/2 17:23
 **/
// 1016. 子串能表示从 1 到 N 数字的二进制串
public class LeetCode_1016 {
    public static boolean queryString(String s, int n) {
        int len = s.length();
        if (n > len * len) {
            return false;
        }
        char[] cs = s.toCharArray();
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = cs[i] - '0';
        }
        boolean[] b = new boolean[n + 1];
        b[0] = true;
        int size = Integer.toBinaryString(n).length();
        int cnt = 0;
        for (int i = 1; i <= size; i++) {
            int window = (1 << i) - 1;
            int num = 0, p = 0;
            while (p < len) {
                num <<= 1;
                if (nums[p] == 1) {
                    num |= 1;
                }
                num &= window;
                if (num <= n && !b[num]) {
                    cnt++;
                    b[num] = true;
                }
                p++;
            }
        }
        return cnt == n;
    }

    public static void main(String[] args) {
        String str = "0110";
        int a = 3;
        System.out.println(queryString(str, 3));
    }
}
