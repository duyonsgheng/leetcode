package custom.code_2023_09;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2165
 * @date 2023年09月05日
 */
public class LeetCode_2165 {
    public static long smallestNumber(long num) {
        if (num == 0) {
            return 0;
        }
        long cur = num;
        int bit = countBit(cur);
        boolean flag = false;
        if (num < 0) {
            flag = true;
            cur = -num;
        }
        long[] arr = new long[bit];
        int i = 0;
        long offset = 1;
        int curBit = bit;
        while (--curBit != 0) {
            offset *= 10;
        }
        while (offset != 0) {
            arr[i++] = ((cur / offset) % 10);
            offset /= 10;
        }
        Arrays.sort(arr);
        long ans = 0;
        if (flag) { // 负数
            ans = -arr[bit - 1];
            for (int k = bit - 2; k >= 0; k--) {
                ans *= 10l;
                ans -= (long) arr[k];
            }
        } else {
            // 0 0 0 1 2 3
            int k = 0;
            while (arr[k] == 0) {
                k++;
            }
            int cnt = k;
            ans = arr[k++];
            while (cnt != 0) {
                ans *= 10;
                cnt--;
            }
            while (k < bit) {
                ans *= 10;
                ans += arr[k++];
            }
        }
        return ans;
    }

    public static int countBit(long cur) {
        int i = 0;
        while (cur != 0) {
            cur /= 10;
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        long a = 310;
        System.out.println(smallestNumber(a));
    }
}
