package week.week_2022_07_02;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName Code_04_LeetCode_759
 * @Author Duys
 * @Description
 * @Date 2022/7/14 13:34
 **/

// 给定员工的 schedule 列表，表示每个员工的工作时间。
// 每个员工都有一个非重叠的时间段 Intervals 列表，这些时间段已经排好序。
// 返回表示 所有 员工的 共同，正数长度的空闲时间 的有限时间段的列表，同样需要排好序。
// 测试链接 : https://leetcode.cn/problems/employee-free-time/
public class Code_04_LeetCode_759 {


    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<int[]> arr = new ArrayList<>();
        for (List<Interval> p : schedule) {
            for (Interval i : p) {
                // 0 表示 开始时间点， 有员工要上线干活
                // 1 表示 结束时间点， 有员工要下线休息
                arr.add(new int[]{i.start, i.end, 0});
                arr.add(new int[]{i.end, i.end, 1});
            }
        }
        arr.sort((a, b) -> a[0] - b[0]);
        Set<Integer> set = new HashSet<>();
        set.add(arr.get(0)[1]);// 结束时间
        List<Interval> ans = new ArrayList<>();
        for (int i = 1; i < arr.size(); i++) {
            int[] cur = arr.get(i);
            if (cur[2] == 0) { // 上线的
                if (set.isEmpty() && arr.get(i - 1)[0] != cur[0]) {
                    ans.add(new Interval(arr.get(i - 1)[0], cur[0]));
                }
                // 把当前下线的时间存入
                set.add(cur[1]);
            } else {
                set.remove(cur[1]);
            }
        }
        return ans;
    }

    public static class Interval {
        public int start;
        public int end;

        public Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
