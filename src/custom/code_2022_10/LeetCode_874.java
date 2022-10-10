package custom.code_2022_10;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LeetCode_874
 * @Author Duys
 * @Description
 * @Date 2022/10/10 13:40
 **/
// 874. 模拟行走机器人
public class LeetCode_874 {
    // 模拟
    public int robotSim(int[] commands, int[][] obstacles) {
        // 机器人的朝向
        // 北 - x,y+1
        // 南 - x,y-1
        // 东 - x+1,y
        // 西 - x-1,y
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        // 障碍
        Set<String> set = new HashSet<>();
        for (int[] arr : obstacles) {
            set.add(arr[0] + "," + arr[1]);
        }
        int ans = 0;
        int x = 0;
        int y = 0;
        //开始  0-北  1-东  2-南 3-西
        // (d+3) %4
        //左转  3-西  0-北  1-东 2-南
        // (d+1)%4
        //右转  1-东  2-南  3-西 0-北
        int d = 0;
        for (int com : commands) {
            // 左转
            if (com == -2) {
                d = (d + 3) % 4;
            } else if (com == -1) {
                d = (d + 1) % 4;
            } else {
                // 朝着之前的方向前进
                for (int i = 1; i <= com; i++) {
                    int nextX = x + dx[d];
                    int nextY = y + dy[d];
                    if (set.contains(nextX + "," + nextY)) {
                        break;
                    }
                    y = nextY;
                    x = nextX;
                    ans = Math.max(ans, x * x + y * y);
                }

            }
        }
        return ans;
    }
}
