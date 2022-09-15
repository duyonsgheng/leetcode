package week.week_2021_12_04;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Code_04_MaximumNumberOfVisiblePoints
 * @Author Duys
 * @Description
 * @Date 2022/4/14 16:17
 **/

// 整个二维平面算是一张地图，给定[x,y]，表示你站在x行y列
// 你可以选择面朝的任何方向
// 给定一个正数值angle，表示你视野的角度为
// 这个角度内你可以看无穷远，这个角度外你看不到任何东西
// 给定一批点的二维坐标，返回你在朝向最好的情况下，最多能看到几个点
// 测试链接 : https://leetcode-cn.com/problems/maximum-number-of-visible-points/
public class Code_04_MaximumNumberOfVisiblePoints {

    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        // 点的数量
        int n = points.size();
        // 开始点位
        int satrtX = location.get(0);
        int startY = location.get(1);

        int zero = 0; // 和当前点重复的点有哪些
        double[] arr = new double[n << 1];
        int m = 0;
        for (int i = 0; i < n; i++) {
            int x = points.get(i).get(0) - satrtX;
            int y = points.get(i).get(1) - startY;
            if (x == 0 && y == 0) {
                zero++;
            } else {
                // x y
                arr[m] = Math.toDegrees(Math.atan2(x, y));
                arr[m + 1] = arr[m] + 360;
                m += 2;
            }
        }
        Arrays.sort(arr, 0, m);
        int max = 0;
        for (int l = 0, r = 0; l < n; l++) {
            while (r < m && arr[r] - arr[l] <= angle) {
                r++;
            }
            max = Math.max(max, r - l);
        }
        return max + zero;
    }
}
