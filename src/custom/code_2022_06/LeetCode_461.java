package custom.code_2022_06;

/**
 * @ClassName LeetCode_461
 * @Author Duys
 * @Description
 * @Date 2022/6/13 15:09
 **/
// 461. 汉明距离
// 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
//给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
public class LeetCode_461 {

    public static int hammingDistance(int x, int y) {
        if (x == y) {
            return 0;
        }
        int count = 0;
        for (int i = 31; i >= 0; i--) {
            int ix = x & (1 << i);
            int iy = y & (1 << i);
            if (ix != iy) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(hammingDistance(1,4));
        System.out.println(hammingDistance(3,1));
    }
}
