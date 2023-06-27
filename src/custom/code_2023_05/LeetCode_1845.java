package custom.code_2023_05;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1845
 * @Author Duys
 * @Description
 * @Date 2023/5/30 9:06
 **/
// 1845. 座位预约管理系统
public class LeetCode_1845 {
    class SeatManager {
        private PriorityQueue<Integer> queue;

        public SeatManager(int n) {
            queue = new PriorityQueue<>();
            for (int i = 1; i <= n; i++) {
                queue.add(i);
            }
        }

        public int reserve() {
            if (queue.isEmpty()) {
                return 0;
            }
            return queue.poll();
        }

        public void unreserve(int seatNumber) {
            queue.add(seatNumber);
        }
    }
}
