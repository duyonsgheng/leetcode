package custom.code_2022_12;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1678
 * @Author Duys
 * @Description
 * @Date 2022/12/5 11:03
 **/
// 1687. 从仓库到码头运输箱子
// 链接：https://leetcode.cn/problems/delivering-boxes-from-storage-to-ports
public class LeetCode_1678 {
    // boxes = [[1,4],[1,2],[2,1],[2,1],[3,2],[3,4]], portsCount = 3, maxBoxes = 6, maxWeight = 7
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length, ans = 0, curp = 0, w = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int i = 1; i <= n; i++) {
            int cur = ans + 2; // 去一趟，回来一趟
            // 如果当前要去的码头和之前的码头不一样，必须要多加一趟
            curp += i >= 2 && boxes[i - 1][0] != boxes[i - 2][0] ? 1 : 0;
            w += boxes[i - 1][1];
            queue.add(new int[]{i, cur - curp, boxes[i - 1][1] - w});
            while (!queue.isEmpty() && queue.peek()[0] <= i - maxBoxes || queue.peek()[2] + w > maxWeight) {
                queue.poll();
            }
            ans = queue.peek()[1] + curp;
        }
        return ans;
    }
}
