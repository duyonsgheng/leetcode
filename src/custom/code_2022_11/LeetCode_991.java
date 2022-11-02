package custom.code_2022_11;

/**
 * @ClassName LeetCode_991
 * @Author Duys
 * @Description
 * @Date 2022/11/2 10:13
 **/
// 991.坏了的计算器
public class LeetCode_991 {
    public int brokenCalc(int startValue, int target) {
        int ans = 0;
        // 先算除法
        while (target > startValue) {
            ans++;
            // 如果是奇数。扩大
            if (target % 2 == 1) {
                target++;
            } else { // 如果是偶数，缩小
                target /= 2;
            }
        }
        // 后算加法
        return ans + Math.abs(target - startValue);
    }
}
