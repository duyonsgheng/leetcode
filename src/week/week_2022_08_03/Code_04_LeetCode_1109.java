package week.week_2022_08_03;

/**
 * @ClassName Code_04_LeetCode_1109
 * @Author Duys
 * @Description
 * @Date 2022/8/18 17:51
 **/
// 1109. 航班预订统计
public class Code_04_LeetCode_1109 {
    // 两种解法
    // 1.线段树
    // 2.差分
    // 差分-前缀和的逆运算
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] count = new int[n + 2];// 防止越界
        for (int[] book : bookings) {
            count[book[0]] += book[2];
            count[book[1] + 1] -= book[2];
        }
        // 前缀和
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        //
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = count[i + 1];
        }
        return ans;
    }
}
