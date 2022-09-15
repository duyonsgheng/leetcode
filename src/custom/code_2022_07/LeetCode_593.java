package custom.code_2022_07;

/**
 * @ClassName LeetCode_593
 * @Author Duys
 * @Description
 * @Date 2022/7/29 11:38
 **/
//593. 有效的正方形
// 给定2D空间中四个点的坐标p1,p2,p3和p4，如果这四个点构成一个正方形，则返回 true 。
//点的坐标pi 表示为 [xi, yi] 。输入 不是 按任何顺序给出的。
//链接：https://leetcode.cn/problems/valid-square
public class LeetCode_593 {

    // 首先正方形的四个点满足什么关系
    // p1   p3
    // p2   p4
    // 任意三个点拿出来，组成直角等腰三角形
    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        return process(p1, p2, p3) && process(p2, p3, p4) && process(p1, p3, p4) && process(p1, p2, p4);
    }

    public static boolean process(int[] a1, int[] a2, int[] a3) {
        // 两点之间的距离 a^2 = b^+c^2
        // a1 - a2
        int d1 = (a1[0] - a2[0]) * (a1[0] - a2[0]) + (a1[1] - a2[1]) * (a1[1] - a2[1]);
        // a1 - a3
        int d2 = (a1[0] - a3[0]) * (a1[0] - a3[0]) + (a1[1] - a3[1]) * (a1[1] - a3[1]);
        // a2 -a3
        int d3 = (a3[0] - a2[0]) * (a3[0] - a2[0]) + (a3[1] - a2[1]) * (a3[1] - a2[1]);
        // d1是斜边
        return (d1 > d2 && d2 == d3 && d1 == d2 + d3) ||
                // d2 是斜边
                (d2 > d3 && d1 == d3 && d2 == d1 + d3) ||
                // d3是斜边
                (d3 > d2 && d1 == d2 && d3 == d2 + d1);
    }

}
