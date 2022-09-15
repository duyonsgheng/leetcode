package duys_code.day_22;

import java.util.PriorityQueue;

/**
 * @ClassName Code_03_407_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/trapping-rain-water-ii/
 * @Date 2021/11/10 16:25
 **/
public class Code_03_407_LeetCode {
    /**
     * 接雨水问题2：给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
     */
    /**
     * 思路：先把外围一圈的加入我们的小根堆。然后弹出最小的，因为最小的是瓶颈，然后把弹出的值的左右上下全部拽进来，然后进行结算
     * 那我们需要封装一个node 来把每一个位置记录下来，
     */
    public static class Node {
        // 矩阵中对应位置的值
        public int value;
        // 对应矩阵中行
        public int row;
        // 对应矩阵中列
        public int col;

        public Node(int v, int r, int c) {
            value = v;
            row = r;
            col = c;
        }
    }

    public static int trapRainWater(int[][] matrix) {

        if (matrix == null || matrix.length < 1 || matrix[0] == null || matrix[0].length < 1) {
            return -1;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        // 使用二维矩阵记录当前位置是否已经进入过队列了，如果没有空间要求，也可以使用set来做
        boolean[][] isInQueue = new boolean[N][M];
        // 小根堆
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.value - b.value);
        // 先处理四条边线
        // 每次进队列的时候就结算，四条边线不用结算。因为最外层的四条边上不能积水
        // 上边 0 ~ M-1
        for (int i = 0; i <= M - 1; i++) {
            isInQueue[0][i] = true;
            minHeap.add(new Node(matrix[0][i], 0, i));
        }
        // 左边 1 ~ N-1
        for (int i = 1; i <= N - 1; i++) {
            isInQueue[i][0] = true;
            minHeap.add(new Node(matrix[i][0], i, 0));
        }
        // 下边
        // 1 ~ M-1
        for (int i = 1; i <= M - 1; i++) {
            isInQueue[N - 1][i] = true;
            minHeap.add(new Node(matrix[N - 1][i], N - 1, i));
        }
        // 右边
        // 1 ~ N-2
        for (int i = 1; i < N - 1; i++) {
            isInQueue[i][M - 1] = true;
            minHeap.add(new Node(matrix[i][M - 1], i, M - 1));
        }
        int ans = 0;
        int max = 0; // 单数组的max差不多。就是瓶颈是啥
        while (!minHeap.isEmpty()) {
            Node cur = minHeap.poll();
            max = Math.max(max, cur.value);
            int row = cur.row;
            int col = cur.col;
            // 上下左右四个点
            // 每次进队列的时候就结算
            if (row > 0 && !isInQueue[row - 1][col]) {
                ans += Math.max(0, max - matrix[row - 1][col]);
                isInQueue[row - 1][col] = true;
                minHeap.add(new Node(matrix[row - 1][col], row - 1, col));
            }
            if (row < N - 1 && !isInQueue[row + 1][col]) {
                ans += Math.max(0, max - matrix[row + 1][col]);
                isInQueue[row + 1][col] = true;
                minHeap.add(new Node(matrix[row + 1][col], row + 1, col));
            }

            if (col > 0 && !isInQueue[row][col - 1]) {
                ans += Math.max(0, max - matrix[row][col - 1]);
                isInQueue[row][col - 1] = true;
                minHeap.add(new Node(matrix[row][col - 1], row, col - 1));
            }
            if (col < M - 1 && !isInQueue[row][col + 1]) {
                ans += Math.max(0, max - matrix[row][col + 1]);
                isInQueue[row][col + 1] = true;
                minHeap.add(new Node(matrix[row][col + 1], row, col + 1));
            }
        }
        return ans;
    }

}
























