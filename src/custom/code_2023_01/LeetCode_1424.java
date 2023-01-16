package custom.code_2023_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1424
 * @Author Duys
 * @Description
 * @Date 2023/1/4 14:43
 **/
// 1424. 对角线遍历 II
public class LeetCode_1424 {
    public static void main(String[] args) {
        // nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(1, 2, 3, 4, 5, 6));
      /*  nums.add(Arrays.asList(6, 7));
        nums.add(Arrays.asList(8));
        nums.add(Arrays.asList(9, 10, 11));
        nums.add(Arrays.asList(12, 13, 14, 15, 16));*/
        findDiagonalOrder1(nums);
    }

    public static int[] findDiagonalOrder(List<List<Integer>> nums) {
        int size = 0;
        int n = nums.size();
        for (int i = 0; i < n; i++)
            size += nums.get(i).size();
        int[] ans = new int[size];
        // [0] 表示排序号，[1] 表示 行 [2]列
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int index = 0;
        queue.add(new int[]{index++, 0, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            ans[cur[0]] = nums.get(cur[1]).get(cur[2]);
            // 向下扩
            if (cur[2] == 0 && cur[1] + 1 < n) {
                queue.add(new int[]{index++, cur[1] + 1, 0});
            }
            // 向右扩
            if (nums.get(cur[1]).size() > cur[2] + 1) {
                queue.add(new int[]{index++, cur[1], cur[2] + 1});
            }
        }
        return ans;
    }

    public static int[] findDiagonalOrder1(List<List<Integer>> nums) {
        Deque<Integer> deque = new LinkedList<>();
        int maxCol = -1;
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            maxCol = Math.max(nums.get(i).size(), maxCol);
        }
        for (int i = 0; i < n; i++) {
            int x = i;
            int y = 0;
            while (x >= 0) {
                if (nums.get(x).size() > y) {
                    deque.addLast(nums.get(x).get(y));
                }
                x--;
                y++;
            }
        }
        for (int i = 1; i < maxCol; i++) {
            int y = i;
            int x = n - 1;
            while (x >= 0) {
                if (nums.get(x).size() > y) {
                    deque.addLast(nums.get(x).get(y));
                }
                x--;
                y++;
            }
        }
        int[] ans = new int[deque.size()];
        int index = 0;
        while (!deque.isEmpty()) {
            ans[index++] = deque.pollFirst();
        }
        return ans;
    }
}
