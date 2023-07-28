package custom.code_2023_07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2007
 * @date 2023年07月26日
 */
// 2007. 从双倍数组中还原原数组
// https://leetcode.cn/problems/find-original-array-from-doubled-array/
public class LeetCode_2007 {
    public static int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if (n % 2 != 0) {
            return new int[]{};
        }
        Arrays.sort(changed);
        List<Integer> list = new ArrayList<>();
        // 偶数的全部抽出来
        // [1,3,4,2,6,8]
        // 1 2 3 4 6 8
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (queue.isEmpty()) {
                queue.add(changed[i]);
            } else {
                if (queue.peek() * 2 == changed[i]) {
                    list.add(queue.poll());
                } else {
                    queue.add(changed[i]);
                }
            }
        }
        if (!queue.isEmpty()) {
            return new int[]{};
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }


    public static void main(String[] args) {
        findOriginalArray(new int[]{1, 3, 4, 2, 6, 8});
    }
}
