package custom.code_2022_11;

/**
 * @ClassName LeetCode_1015
 * @Author Duys
 * @Description
 * @Date 2022/11/2 17:13
 **/
// 1015. 可被 K 整除的最小整数
public class LeetCode_1015 {
    // 脑筋急转弯
    public static int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        int i = 1;
        int ans = 1;
        while (i % k != 0) {
            i = i % k;
            // 保证每一位数字都是1
            i = i * 10 + 1;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int k = 2;
        System.out.println(smallestRepunitDivByK(3));
    }
}
