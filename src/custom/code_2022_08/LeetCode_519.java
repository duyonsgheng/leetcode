package custom.code_2022_08;

import java.util.HashMap;
import java.util.Random;

/**
 * @ClassName LeetCode_519
 * @Author Duys
 * @Description
 * @Date 2022/8/22 10:28
 **/
// 519. 随机翻转矩阵
public class LeetCode_519 {

    class Solution {
        private int n;
        private int m;
        private int total;
        private HashMap<Integer, Integer> map = new HashMap<>();
        private Random random = new Random();

        public Solution(int n, int m) {
            this.n = n;
            this.m = m;
            this.total = n * m;
        }

        public int[] flip() {
            int x = random.nextInt(total--);
            int index = map.getOrDefault(x, x);
            map.put(x, map.getOrDefault(total, total));
            return new int[]{index / n, index % n};
        }

        public void reset() {
            total = n * m;
            map.clear();
        }
    }
}
