package custom.code_2022_07;

/**
 * @ClassName LeetCode_371
 * @Author Duys
 * @Description
 * @Date 2022/7/21 13:17
 **/
//371. 两整数之和
// 给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。
public class LeetCode_371 {
    // 位于运算
    // (a & b)<<1 进位
    // 2 + 3
    // 0 0 1 0
    // 0 0 1 1
    // cur =  0 1 0 0
    // a   =  0 0 1 1
    // b   =  0 1 0 0
    // cur =  0

    public int getSum(int a, int b) {
        while (b != 0) {
            // 每次都会产生进位的
            int cur = (a & b) << 1;
            a ^= b;
            b = cur;
        }
        return a;
    }
}
