package duys_code.day_32;

/**
 * @ClassName Code_07_190_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/reverse-bits/
 * @Date 2021/12/6 10:57
 **/
public class Code_07_190_LeetCode {

    public int reverseBits(int n) {
        n = (n >>> 16) | (n << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }
}
