package custom.code_2022_12;

/**
 * @ClassName LeetCode_1401
 * @Author Duys
 * @Description
 * @Date 2022/12/27 15:42
 **/
// 1401. 圆和矩形是否有重叠
public class LeetCode_1401 {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // 1.矩形中心的点
        double x0 = (x1 + x2) / 2.0;
        double y0 = (y1 + y2) / 2.0;

        // 矩形中心点到原点之间的向量
        double[] p = new double[]{Math.abs(xCenter - x0), Math.abs(yCenter - y0)};

        // 矩形中点到顶点的向量
        double[] q = new double[]{x2 - x0, y2 - y0};

        // 矩形顶点到圆心的距离
        double[] u = new double[]{Math.max(p[0] - q[0], 0.0), Math.max(p[1] - q[1], 0.0)};
        return (u[0] * u[0] + u[1] * u[1]) <= radius * radius;
    }
}
