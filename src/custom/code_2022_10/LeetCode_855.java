package custom.code_2022_10;

import java.util.TreeSet;

/**
 * @ClassName LeetCode_855
 * @Author Duys
 * @Description
 * @Date 2022/10/8 17:30
 **/
// 855. 考场就座
public class LeetCode_855 {
    class ExamRoom {
        int n;
        TreeSet<Integer> set;

        public ExamRoom(int n) {
            this.n = n;
            set = new TreeSet<>();
        }

        public int seat() {
            int index = 0;
            if (set.size() > 0) {
                int dist = set.first();
                Integer pre = null;
                // 保证距离的最大
                for (Integer i : set) {
                    if (pre != null) {
                        int d = (i - pre) / 2;
                        if (d > dist) {
                            dist = d;
                            index = pre + d;
                        }
                    }
                    pre = i;
                }
                // 如果最后一个位置
                if (n - 1 - set.last() > dist) {
                    index = n - 1;
                }
            }
            set.add(index);
            return index;
        }

        public void leave(int p) {
            set.remove(p);
        }
    }
}
