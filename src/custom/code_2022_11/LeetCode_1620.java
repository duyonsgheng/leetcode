package custom.code_2022_11;

/**
 * @ClassName LeetCode_1620
 * @Author Duys
 * @Description
 * @Date 2022/11/2 9:24
 **/
// 1620. 网络信号最好的坐标
public class LeetCode_1620 {
    public int[] bestCoordinate(int[][] towers, int radius) {
        int xm = Integer.MIN_VALUE;
        int ym = Integer.MIN_VALUE;
        // 找到x y 最大的边界
        for (int[] to : towers) {
            xm = Math.max(xm, to[0]);
            ym = Math.max(ym, to[1]);
        }
        int x = 0;
        int y = 0;
        int max = 0;
        for (int i = 0; i <= xm; i++) {
            for (int j = 0; j <= ym; j++) {
                int cur = 0;
                for (int[] to : towers) {
                    // 两点之间的距离
                    int dis = dist(i, j, to);
                    if (dis <= radius * radius) {
                        // 向下取整函数
                        cur += Math.floor(to[2] / (1 + Math.sqrt(dis)));
                    }
                }
                if (cur > max) {
                    max = cur;
                    x = i;
                    y = j;
                }
            }
        }
        return new int[]{x, y};
    }

    // 两点之间的距离
    public int dist(int x, int y, int[] arr) {
        return (x - arr[0]) * (x - arr[0]) + (y - arr[1]) * (y - arr[1]);
    }
}
