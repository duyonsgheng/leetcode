package duys_code.day_42;

/**
 * @ClassName Code_05_335_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/self-crossing/
 * @Date 2022/1/4 13:20
 **/
public class Code_05_335_LeetCode {
    // 给你一个整数数组 distance 。
    //从 X-Y 平面上的点(0,0)开始，先向北移动 distance[0] 米，然后向西移动 distance[1] 米，向南移动 distance[2] 米，向东移动 distance[3] 米，
    //持续移动。也就是说，每次移动后你的方位会发生逆时针变化。
    //判断你所经过的路径是否相交。如果相交，返回 true ；否则，返回 false 。

    // 只需要进行可能性分析
    // 1.第一步向上走 x1
    // 2.接着向左走  x2
    // 3.接着向下走  x3
    // 4.接着向右走  x4
    // 会不会于x1相撞  一定是长度大于x2的病x3的距离一定是小于等于x1的
    // 课上举例很容里理解，比如当前来到的是第i跟线，那么会不会跟i-1 装上，会不会跟i-2装上，会不会跟i-3装上，然后每一种可能相撞的情况都有对应的条件
    public static boolean isSelfCrossing(int[] x) {
        if (x == null || x.length < 4) {
            return false;
        }
        if ((x.length > 3 && x[2] <= x[0] && x[3] >= x[1]) // 只有一圈的时候，就装上了。
                || x.length > 4 && ((x[3] <= x[1] && x[4] >= x[2]) || (x[3] == x[1] && x[0] + x[4] >= x[2])) // 外面的包住了里面的
        ) {
            return true;
        }
        for (int i = 5; i < x.length; i++) {
            if (x[i - 1] <= x[i - 3] && ((x[i] >= x[i - 2])
                    || (x[i - 2] >= x[i - 4] && x[i - 5] + x[i - 1] >= x[i - 3] && x[i - 4] + x[i] >= x[i - 2]))
            ) {
                return true;
            }
        }
        return false;
    }
}
