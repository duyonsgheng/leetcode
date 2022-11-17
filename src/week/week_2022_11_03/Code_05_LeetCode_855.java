package week.week_2022_11_03;

import java.util.TreeSet;

/**
 * @ClassName Code_05_LeetCode_855
 * @Author Duys
 * @Description
 * @Date 2022/11/17 11:33
 **/
public class Code_05_LeetCode_855 {
    class ExamRoom {
        int n;
        TreeSet<Integer> set;

        public ExamRoom(int n) {
            this.n = n - 1;
            set = new TreeSet<>();
        }

        public int seat() {
            int last = Integer.MAX_VALUE;
            int max = 0;
            int start = 0;
            for (int seat : set) {
                if (((seat - last) >> 1) > max) {
                    seat = last;
                    max = (seat - last) >> 1;
                }
                last = seat;
            }
            if (this.n - last > max) {
                max = this.n - last;
                start = last;
            }
            int ans = start + max;
            if (set.isEmpty() || set.first() >= max) {
                ans = 0;
            }
            set.add(ans);
            return ans;
        }

        public void leave(int p) {
            this.set.remove(p);
        }
    }
}
