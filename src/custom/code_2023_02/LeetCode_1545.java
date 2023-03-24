package custom.code_2023_02;

/**
 * @ClassName LeetCode_1545
 * @Author Duys
 * @Description
 * @Date 2023/2/9 11:20
 **/
// 1545. 找出第 N 个二进制字符串中的第 K 位
public class LeetCode_1545 {
    // s的长度是 2^n  -1
    // s的左部分 2^(n-1) -1 中间一个字符是 ’1‘ 右边是左边的 2^(n-1) - 1个字符翻转和反转后的结果
    public char findKthBit(int n, int k) {
        // 0111001
        // 1000110
        if (k == 1) {
            return '0';
        }
        int mid = 1 << (n - 1);
        if (k == mid) {
            return '1';
        } else if (k < mid) {
            // 在左部分 直接就找第k个
            return findKthBit(n - 1, k);
        } else {
            // 在右部分，找到左部分的第 2^(n) -k 个，然后翻转
            k = mid * 2 - k;
            return invert(findKthBit(n - 1, k));
        }
    }

    public char invert(char c) {
        return (char) ('0' + '1' - c);
    }
}
