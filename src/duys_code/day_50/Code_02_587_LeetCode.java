package duys_code.day_50;

import java.util.Arrays;

/**
 * @ClassName Code_02_587_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/erect-the-fence/
 * @Date 2021/10/29 13:44
 **/
public class Code_02_587_LeetCode {

    /**
     * 凸包问题：
     * 1.先按照数组正序使用叉乘，找到比自己更右边的点，使用栈依次弹出之前找到的点
     * 2.再按照数组的逆序使用叉乘，就是两次描绘线
     */
    public static int[][] outerTrees(int[][] points) {
        int n = points.length;
        int s = 0;
        // 先根据横坐标 x 排序，x相等的 纵坐标越小越排前面
        Arrays.sort(points, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        // 准备一个栈
        int[][] stack = new int[n << 1][];
        // 先描绘下面的线 - 线正序描绘
        for (int i = 0; i < n; i++) {
            // 如果当前这个点实在 倒数第一个 stack[s-1]更右侧。那么 依次弹栈
            while (s > 1 && cross(stack[s - 2], stack[s - 1], points[i]) > 0) {
                s--;
            }
            stack[s++] = points[i];
        }

        // 逆序开始描绘另外一边
        for (int i = n - 1; i >= 0; i--) {
            while (s > 1 && cross(stack[s - 2], stack[s - 1], points[i]) > 0) {
                s--;
            }
            stack[s++] = points[i];
        }
        // 去重返回
        Arrays.sort(stack, 0, s, (a, b) -> b[0] == a[0] ? b[1] - a[1] : b[0] - a[0]);
        n = 1;
        for (int i = 1; i < s; i++) {
            if (stack[i][0] != stack[i - 1][0] || stack[i][1] != stack[i - 1][1]) {
                stack[n++] = stack[i];
            }
        }
        return Arrays.copyOf(stack, n);
    }


    /**
     * 叉乘含义 ： 现在又A B C三个点
     * 从A点出发，A到B的向量 如果在A到C的向量的左边 那么叉乘就大于0
     * 从A点出发，A到B的向量 如果在A到C的向量的右边 那么叉乘就小于0
     * 从A点出发，A到B的向量 如果在A到C的向量的同方向 那么叉乘等于0
     * 从A点出发，A到B的向量 如果在A到C的向量的反方向 那么叉乘等于0
     */
    // 叉乘
    public static int cross(int[] a, int[] b, int[] c) {
        // (b的y坐标 - a的y坐标) * (c的x坐标 - b的x坐标)
        // (b的x坐标 - a的x坐标) * (c的y坐标 - b的y坐标)
        return (b[1] - a[1]) * (c[0] - b[0]) - (b[0] - a[0]) * (c[1] - b[1]);
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 1};
        int[] b = new int[]{2, 2};
        int[] c = new int[]{3, 3};
        System.out.println(cross(a, b, c));
    }
}
