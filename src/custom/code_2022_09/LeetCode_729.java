package custom.code_2022_09;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @ClassName LeetCode_729
 * @Author Duys
 * @Description
 * @Date 2022/9/15 15:06
 **/
// 729. 我的日程安排表 I
public class LeetCode_729 {

    class MyCalendar {
        private TreeSet<int[]> tree;

        public MyCalendar() {
            tree = new TreeSet<>((a, b) -> a[0] - b[0]);
        }

        public boolean book(int start, int end) {
            if (tree.isEmpty()) {
                tree.add(new int[]{start, end});
                return true;
            }
            int[] tmp = {end, 0};
            // 大于等于当前end的最小
            int[] arr = tree.ceiling(tmp);
            if (arr == tree.first() || tree.lower(tmp)[1] <= start) {
                tree.add(new int[]{start, end});
                return true;
            }
            return false;
        }
    }

    class MyCom implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] - o2[0];
        }
    }
}
