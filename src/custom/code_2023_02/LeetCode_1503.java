package custom.code_2023_02;

/**
 * @ClassName LeetCode_1503
 * @Author Duys
 * @Description
 * @Date 2023/2/3 13:12
 **/
// 1503. 所有蚂蚁掉下来前的最后一刻
public class LeetCode_1503 {
    // 想明白一个事情
    // 两只蚂蚁相遇后只是换了方向，速度不变，则可以认为蚂蚁还是朝着之前的方向继续移动。
    public int getLastMoment(int n, int[] left, int[] right) {
        int ans = 0;
        // 向左走的蚂蚁最后走到左边界
        for (int i : left) {
            ans = Math.max(ans, i);
        }
        // 向右移动的蚂蚁最后走都右边界
        for (int i : right) {
            ans = Math.max(ans, n - i);
        }
        return ans;
    }
}
