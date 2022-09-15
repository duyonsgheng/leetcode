package week.week_2022_08_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * @ClassName Code_04_ClosestTwoPoints1
 * @Author Duys
 * @Description
 * @Date 2022/8/11 10:51
 **/
// 来自算法导论
// 给定平面上n个点，x和y坐标都是整数
// 找出其中的一对点的距离，使得在这n个点的所有点对中，该距离为所有点对中最小的
// 返回最短距离，精确到小数点后面4位
// 测试链接 : https://www.luogu.com.cn/problem/P1429
// 提交如下代码，把主类名改成Main，可以直接通过
// T(N) = 2*T(N/2) + O(N*logN)
// 这个表达式的时间复杂度是O(N*(logN的平方))
// 复杂度证明 : https://math.stackexchange.com/questions/159720/
// 网上大部分的帖子，答案都是这个复杂度
public class Code_04_ClosestTwoPoints1 {
    // 首先大体思路
    // 1.归并：左右两边算一个最小，然后合并算一个最小
    // 2.通过算得的最小来作为一个指导，然后在指导数据的一个范围内，来继续算
    public static int n = 200001;
    public static Point[] points = new Point[n];
    public static Point[] deals = new Point[n];

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(reader);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                double x = in.nval;
                in.nextToken();
                double y = in.nval;
                points[i] = new Point(x, y);
            }
            // 根据x上的点排序
            Arrays.sort(points, 0, n, (a, b) -> a.x <= b.x ? -1 : 1);
            double ans = nearest(0, n - 1);
            out.println(String.format("%.4f", ans));
            out.flush();
        }
    }

    // 这里左边 N/2 右边 N/2 合并：有一个排序，并且还有一个没觉 N*log(N)
    // 所以整体就是 T(N) = 2*T(N/2) + O(N*logN)
    public static double nearest(int left, int right) {
        double ans = Double.MAX_VALUE;
        if (left == right) {
            return ans;
        }
        int mid = (left + right) / 2;
        // 左右求得最小，
        ans = Math.min(nearest(left, mid), nearest(mid + 1, right));
        int l = mid;
        int r = mid + 1;
        int size = 0;
        // 从中点开始左右两边，如果在指导范围内，就计算一下
        // 只有在 mid 的左边 x轴距离小于等于ans的点才是需要继续计算的，其他的更左边的点都不需要考虑了
        while (l >= left && (points[mid].x - points[l].x <= ans)) {
            deals[size++] = points[l--];
        }
        // 只有在 mid 的右边 x轴距离小于等于ans的点才是需要继续计算的，其他的更右边的点都不需要考虑了
        while (r <= right && (points[r].x - points[mid].x <= ans)) {
            deals[size++] = points[r++];
        }
        // 既然x轴上的计算了，现在需要根据y轴上的继续计算
        // 根据y轴的排序
        Arrays.sort(deals, 0, size, (a, b) -> a.y <= b.y ? -1 : 1);
        // 这里根据猜想和论证，在 mid为中点 左边 ans的距离，右边ans的距离，上边ans的距离的一个 矩形内的点，最多只有6个能满足
        // 所以来一次枚举，也是O(N)的
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (deals[j].y - deals[i].y >= ans) {
                    break;// 可以break，因为是排序的，也可以continue;
                }
                ans = Math.min(ans, distance(deals[j], deals[i]));
            }
        }
        return ans;
    }

    public static double distance(Point a, Point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    static class Point {
        public double x;
        public double y;

        public Point(double a, double b) {
            x = a;
            y = b;
        }
    }
}
