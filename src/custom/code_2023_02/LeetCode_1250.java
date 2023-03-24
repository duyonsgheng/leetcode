package custom.code_2023_02;

/**
 * @ClassName LeetCode_1250
 * @Author Duys
 * @Description
 * @Date 2023/2/15 10:42
 **/
// 1250. 检查「好数组」
public class LeetCode_1250 {
    // 裴蜀定理
    public boolean isGoodArray(int[] nums) {
        int div = nums[0];
        for (int num : nums) {
            div = gcd(div, num);
            if (div == 1) {
                break;
            }
        }
        return div == 1;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
