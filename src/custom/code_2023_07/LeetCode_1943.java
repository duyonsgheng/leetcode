package custom.code_2023_07;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1943
 * @date 2023年07月12日
 */
// 1943. 描述绘画结果
// https://leetcode.cn/problems/describe-the-painting/
public class LeetCode_1943 {
    // 可以使用差分和前缀和
    public List<List<Long>> splitPainting(int[][] segments) {
        TreeSet<Integer> set = new TreeSet<>(); // 记录各个区间的边界
        int max = 0; // 记录最大的边界在哪里
        for (int[] segment : segments) {
            set.add(segment[0]);
            set.add(segment[1]);
            max = Math.max(max, segment[1]);
        }
        // 建立差分数组
        long[] diffArr = new long[max + 1];
        for (int[] segment : segments) {
            diffArr[segment[0]] += segment[2];
            diffArr[segment[1]] -= segment[2];
        }
        // 对差分数组求前缀和，得到原数组
        for (int i = 1; i <= max; i++) {
            diffArr[i] = diffArr[i] + diffArr[i - 1];
        }
        List<List<Long>> ans = new ArrayList<>();
        while (set.size() > 1) {
            List<Long> temp = new ArrayList<>();
            // 左边界
            int l = set.pollFirst();
            // 有边界
            int r = set.first();
            long v = diffArr[l];
            // 如果当前这个区域没有，则放弃
            if (v == 0) {
                continue;
            }
            temp.add((long) l);
            temp.add((long) r);
            temp.add(v);
            ans.add(temp);
        }
        return ans;
    }
}
