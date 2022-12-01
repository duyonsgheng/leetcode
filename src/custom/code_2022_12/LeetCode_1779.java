package custom.code_2022_12;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1779
 * @Author Duys
 * @Description
 * @Date 2022/12/1 11:04
 **/
// 1779. 找到最近的有相同 X 或 Y 坐标的点
public class LeetCode_1779 {
    public static int nearestValidPoint(int x, int y, int[][] points) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            int p1 = Math.abs(a[0] - x) + Math.abs(a[1] - y);
            int p2 = Math.abs(b[0] - x) + Math.abs(b[1] - y);
            if (p1 == p2) { // 距离相等就根据位置排位
                return a[2] - b[2];
            }
            // 否则就返回距离最近的
            return p1 - p2;
        });
        for (int i = 0; i < points.length; i++) {
            if (x == points[i][0] || y == points[i][1]) {
                queue.add(new int[]{points[i][0], points[i][1], i});
            }
        }
        return queue.isEmpty() ? -1 : queue.peek()[2];
    }

    public static void main(String[] args) {
        int x = 3, y = 4, points[][] = {{2, 3}};
        System.out.println(nearestValidPoint(x, y, points));
    }
}
