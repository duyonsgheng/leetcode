package custom.code_2022_11;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1054
 * @Author Duys
 * @Description
 * @Date 2022/11/8 11:00
 **/
//1054. 距离相等的条形码
public class LeetCode_1054 {
    // 先用多的，然后再用少的
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : barcodes) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // 多的排在前面先用
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int key : map.keySet()) {
            queue.add(new int[]{key, map.get(key)});
        }
        int n = barcodes.length;
        int[] ans = new int[n];
        int index = 0;
        while (!queue.isEmpty()) {
            int[] more1 = queue.poll();
            int[] more2 = queue.poll();
            ans[index++] = more1[0];
            more1[1]--;
            if (more1[1] > 0) {
                queue.add(more1);
            }
            if (more2 != null) {
                ans[index++] = more2[0];
                more2[1]--;
                if (more2[1] > 0) {
                    queue.add(more2);
                }
            }
        }
        return ans;
    }
}
