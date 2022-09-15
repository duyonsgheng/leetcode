package duys_code.day_18;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @ClassName Code_04_TopK
 * @Author Duys
 * @Description https://www.nowcoder.com/practice/7201cacf73e7495aa5f88b223bbbf6d1
 * @Date 2021/11/2 13:07
 **/
public class Code_04_TopK {
    /**
     * 给定两个有序数组arr1和arr2，再给定一个整数k，返回来自arr1和arr2的两个数相加和最大的前k个，两个数必须分别来自两个数组
     * 按照降序输出
     * [要求]
     * 时间复杂度为O(k \log k)O(klogk)
     * 输入描述：
     * 第一行三个整数N, K分别表示数组arr1, arr2的大小，以及需要询问的数
     * 接下来一行N个整数，表示arr1内的元素
     * 再接下来一行N个整数，表示arr2内的元素
     */
    /**
     * 已知两个数组已经有序。那么最大的和一定是 n-1 和 m-1 两个数相加
     * 准备一个n*m的矩阵
     * 然后从 最右下角的格子开始，准备一个大根堆。每次弹出的时候，把当前格子的上边和左边都抓出来进堆
     */
    public static int[] topK(int[] arr1, int[] arr2, int topK) {
        if (arr1 == null || arr2 == null || topK < 1) {
            return null;
        }
        int N = arr1.length;
        int M = arr2.length;
        topK = Math.min(topK, M * N);
        int[] ans = new int[topK];
        int ansIndex = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>(new MaxHeapComp());
        queue.add(new Node(N - 1, M - 1, arr1[N - 1] + arr2[M - 1]));
        int i1 = N - 1;
        int i2 = M - 1;
        HashSet<Long> set = new HashSet<>();// 记录已经算过了的位置。这里同样使用把二维转为一维的方法
        set.add(x(i1, i2, M));
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            ans[ansIndex++] = poll.sum;
            i1 = poll.index1;
            i2 = poll.index2;
            set.remove(x(i1, i2, M)); // 节约内存
            // 把i1 i2 左边的位置抓进来
            if (i1 >= 1 && !set.contains(x(i1 - 1, i2, M))) {
                set.add(x(i1 - 1, i2, M));
                queue.add(new Node(i1 - 1, i2, arr1[i1 - 1] + arr2[i2]));
            }
            // 把i1 i2 的上边位置抓进来
            if (i2 >= 1 && !set.contains(x(i1, i2 - 1, M))) {
                set.add(x(i1, i2 - 1, M));
                queue.add(new Node(i1, i2 - 1, arr1[i1] + arr2[i2 - 1]));
            }
        }
        return ans;
    }

    public static long x(int i1, int i2, int M) {
        return (long) i1 * (long) M + (long) i2;
    }

    public static class Node {
        public int index1;
        public int index2;
        public int sum;

        public Node(int i1, int i2, int s) {
            index1 = i1;
            index2 = i2;
            sum = s;
        }
    }

    // 大根堆的比较器
    public static class MaxHeapComp implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.sum - o1.sum;
        }
    }
}
