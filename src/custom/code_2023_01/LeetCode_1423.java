package custom.code_2023_01;

/**
 * @ClassName LeetCode_1423
 * @Author Duys
 * @Description
 * @Date 2023/1/4 11:00
 **/
// 1423. 可获得的最大点数
public class LeetCode_1423 {

    // 不管是从开头拿 还是从结尾拿，那么剩下的拿n-k张牌一定是一块连续的
    // 所以我们使用一个n-k大小的窗口，获取最小窗口内的和，就可以获得拿走的最大
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int windowSize = n - k;
        int sum = 0;
        // 窗口
        for (int i = 0; i < windowSize; i++) {
            sum += cardPoints[i];
        }
        // 窗口内最小，那么获得得就一定最大
        int min = sum;
        for (int i = windowSize; i < n; i++) {
            // 窗口向右，左边得出窗口
            sum += cardPoints[i] - cardPoints[i - windowSize];
            min = Math.min(sum, min);
        }
        sum = 0;
        for (int num : cardPoints)
            sum += num;
        return sum - min;
    }
}
