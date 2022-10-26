package custom.code_2022_10;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName LeetCode_973
 * @Author Duys
 * @Description
 * @Date 2022/10/26 9:48
 **/
// 973. 最接近原点的 K 个点
public class LeetCode_973 {
    public static void main(String[] args) {
        int[][] points = {{3, 3}, {5, -1}, {-2, 4}};
        int k = 2;
        int[][] ints = kClosest(points, k);
        for (int[] arr : ints) {
            System.out.println(arr[0] + " " + arr[1]);
        }
    }

    public static int[][] kClosest(int[][] points, int k) {
        // 先排序
        Arrays.sort(points, new My());
        int[][] ans = new int[k][2];
        for (int i = 0; i < k; i++) {
            ans[i] = points[i];
        }
        return ans;
    }

    static class My implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            // 到原点的距离
            int d1 = o1[0] * o1[0] + o1[1] * o1[1];
            int d2 = o2[0] * o2[0] + o2[1] * o2[1];
            return d1 - d2;
        }
    }
}
