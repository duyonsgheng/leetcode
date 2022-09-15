package custom.code_2022_06;

/**
 * @ClassName LeetCode_338
 * @Author Duys
 * @Description
 * @Date 2022/6/13 13:25
 **/
//338. 比特位计数
// 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
public class LeetCode_338 {

    public static int[] countBits(int n) {
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 先把高位算上，看看当前数是不是奇数。
            arr[i] = arr[i >> 1] + (i & 1);
        }
        return arr;
    }

    public static void main(String[] args) {
        countBits(123);
    }
}
