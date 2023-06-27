package custom.code_2023_05;

/**
 * @ClassName LeetCode_1753
 * @Author Duys
 * @Description
 * @Date 2023/5/19 9:14
 **/
// 1753. 移除石子的最大得分
public class LeetCode_1753 {
    // 找出最大
    public int maximumScore(int a, int b, int c) {
        int max = Math.max(a, Math.max(b, c));
        int sum = a + b + c;
        return Math.min(sum - max, sum / 2);
    }
}
