package week.week_2022_08_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * @ClassName Code_04_ClosestTwoPoints2
 * @Author Duys
 * @Description
 * @Date 2022/8/11 13:23
 **/
public class Code_04_ClosestTwoPoints2 {

    public static int N = 200001;

    public static Point[] points = new Point[N];

    public static Point[] merge = new Point[N];

    public static Point[] deals = new Point[N];

    // 其他的流程一样，但是我们把y轴方向的排序就做到归并里去了
    // 降低了在合并过程的排序开销

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            for (int i = 0; i < n; i++) {
                in.nextToken();
                double x = (double) in.nval;
                in.nextToken();
                double y = (double) in.nval;
                points[i] = new Point(x, y);
            }
            Arrays.sort(points, 0, n, (a, b) -> a.x <= b.x ? -1 : 1);
            double ans = nearest(0, n - 1);
            out.println(String.format("%.4f", ans));
            out.flush();
        }
    }


    public static double nearest(int left, int right) {
        double ans = Double.MAX_VALUE;
        if (left == right) {
            return ans;
        }
        int mid = (left + right) / 2;
        double minX = points[mid].x;
        ans = Math.min(nearest(left, mid), nearest(mid + 1, right));
        int p1 = left;
        int p2 = mid + 1;
        int mergeSize = left;
        int dealSize = 0;
        while (p1 <= mid && p2 <= right) {
            // 谁小拷贝谁
            merge[mergeSize] = points[p1].y <= points[p2].y ? points[p1++] : points[p2++];
            if (Math.abs(merge[mergeSize].x - minX) <= ans) {
                deals[dealSize++] = merge[mergeSize];
            }
            mergeSize++;
        }
        // 左边还有剩余
        while (p1 <= mid) {
            merge[mergeSize] = points[p1++];
            if (Math.abs(merge[mergeSize].x - minX) <= ans) {
                deals[dealSize++] = merge[mergeSize];
            }
            mergeSize++;
        }
        // 右边还有剩余
        while (p2 <= right) {
            merge[mergeSize] = points[p2++];
            if (Math.abs(merge[mergeSize].x - minX) <= ans) {
                deals[dealSize++] = merge[mergeSize];
            }
            mergeSize++;
        }
        // 最右把merge的拷贝回原来的，下一次继续使用
        for (int i = left; i <= right; i++) {
            points[i] = merge[i];
        }
        for (int i = 0; i < dealSize; i++) {
            for (int j = i + 1; j < dealSize; j++) {
                if (deals[j].y - deals[i].y >= ans) {
                    break;
                }
                ans = Math.min(ans, distance(deals[i], deals[j]));
            }
        }
        return ans;
    }

    public static double distance(Point a, Point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    public static class Point {
        public double x;
        public double y;

        public Point(double a, double b) {
            x = a;
            y = b;
        }
    }
}
