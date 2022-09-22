package custom.code_2022_09;

/**
 * @ClassName LeetCode_789
 * @Author Duys
 * @Description
 * @Date 2022/9/20 14:16
 **/
// 789. 逃脱阻碍者
public class LeetCode_789 {

    // 思路：不管什么样得方式移动，朝着目标，最短得距离一定是曼哈顿距离，如果我先到达，那么阻碍者就后到达
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int[] start = {0, 0};
        int dist = dist(start, target);
        for (int[] gh : ghosts) {
            int cur = dist(gh, target);
            if (cur <= dist) {
                return false;
            }
        }
        return true;
    }

    // 曼哈顿距离的
    public int dist(int[] x, int[] y) {
        return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
    }
}
