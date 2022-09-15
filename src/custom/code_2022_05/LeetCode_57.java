package custom.code_2022_05;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_57
 * @Author Duys
 * @Description
 * @Date 2022/5/6 11:40
 **/
// 57. 插入区间
// 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
//在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
public class LeetCode_57 {

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        // 去找到第一个大于newInterval[0]的区间是哪一个
        int s = find(intervals, newInterval[0], true);
        // 去找到小于等于newInterval[1]的区间是哪一个
        int e = find(intervals, newInterval[1], false);
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < s; i++) {
            ans.add(intervals[i]);
        }
        // 全部包含了
        if (s == intervals.length || e == -1) {
            ans.add(newInterval);
        } else {
            ans.add(new int[]{Math.min(intervals[s][0], newInterval[0]), Math.max(intervals[e][1], newInterval[1])});
        }
        for (int i = e + 1; i < intervals.length; i++) {
            ans.add(intervals[i]);
        }
        return ans.toArray(new int[ans.size()][2]);
    }

    // 1.找到刚刚大于等于 newInterval[0]的位置
    // 2.找到刚刚小于等于 newInterval[1]的位置
    public static int find(int[][] arr, int target, boolean mod) {
        int l = 0;
        int r = arr.length - 1;
        int mid = -1;
        while (l <= r) {
            mid = l + ((r - l) >> 1);
            if (arr[mid][0] <= target && arr[mid][1] >= target) {
                return mid;
            } else if (arr[mid][0] > target) {
                r = mid - 1;
            } else if (arr[mid][1] < target) {
                l = mid + 1;
            }
        }
        // 找大于，如果没有就直接找下一个
        // 找小于，如果没有，就直接拿当前的
        return mod ? r + 1 : r;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 3}, {6, 9}};
        int[] a = {2, 5};
        insert(arr, a);

    }
    /*public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;

    }*/
}
