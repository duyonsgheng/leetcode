package duys_code.day_49;

/**
 * @ClassName Code_03_546_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/find-the-closest-palindrome/
 * @Date 2021/10/27 13:34
 **/
public class Code_03_546_LeetCode {
    /**
     * 给一个字符串类型的数字 找离这个数字最近的回文数字
     * 例如 62345 -> 62326
     */
    /**
     * 1.先找到 数字的粗回文，先根据数字的长度然后从中点把中点之前的数字逆序排列后的新数字就是粗回文
     * 2.然后再中点处+1 或者-1 就是要得答案
     * 但是需要考虑进位和借位的情况，编码及其复杂
     */

    public String nearestPalindromic(String n) {
        Long num = Long.valueOf(n);
        // 1.获取粗回文
        Long raw = getRawPalindrome(n);
        // 2.中点处+1
        Long big = raw > num ? raw : getBigPalindrome(raw);
        // 3.中点处-1
        Long small = raw < num ? raw : getSmallPalindrome(raw);
        return String.valueOf(big - num >= num - small ? small : big);
    }

    // 1.获取粗回文
    public static Long getRawPalindrome(String n) {
        char[] chars = n.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len / 2; i++) {
            chars[len - i - 1] = chars[i];
        }
        return Long.valueOf(String.valueOf(chars));
    }

    public static void main(String[] args) {
        Long num = 6342436L;
        Long bigPalindrome = getBigPalindrome(num);
    }

    // 2.中点处+1
    public static Long getBigPalindrome(Long raw) {
        // 原字符串
        // 9999
        char[] chs = String.valueOf(raw).toCharArray();
        // 新的字符串，要比之前的多一位。因为可能要进位
        char[] res = new char[chs.length + 1];
        // 先给高位来一个0，占位
        res[0] = '0';
        // 原来的字符串搞到相应的位置去
        for (int i = 0; i < chs.length; i++) {
            res[i + 1] = chs[i];
        }
        int size = chs.length;
        // 从中位开始+1 如果有进位，一致加到我们预备的最高位哪一个占位0
        for (int j = (size - 1) / 2 + 1; j >= 0; j--) {
            if (++res[j] > '9') {
                res[j] = '0';
            } else {
                break;
            }
        }
        // 结束了，看看我们预先的占位数是否是1，是否进位到了这一位
        int offset = res[0] == '1' ? 1 : 0;
        size = res.length;
        // 中位数后面的字符 就是前面的逆序
        for (int i = size - 1; i >= (size + offset) / 2; i--) {
            res[i] = res[size - i - offset];
        }
        return Long.valueOf(String.valueOf(res));
    }

    // 3.中点处-1
    public static Long getSmallPalindrome(Long raw) {
        char[] chs = String.valueOf(raw).toCharArray();
        char[] res = new char[chs.length];
        int size = res.length;
        for (int i = 0; i < size; i++) {
            res[i] = chs[i];
        }
        for (int j = (size - 1) / 2; j >= 0; j--) {
            if (--res[j] < '0') {
                res[j] = '9';
            } else {
                break;
            }
        }
        if (res[0] == '0') {
            res = new char[size - 1];
            for (int i = 0; i < res.length; i++) {
                res[i] = '9';
            }
            return size == 1 ? 0 : Long.parseLong(String.valueOf(res));
        }
        for (int k = 0; k < size / 2; k++) {
            res[size - 1 - k] = res[k];
        }
        return Long.valueOf(String.valueOf(res));
    }
}
