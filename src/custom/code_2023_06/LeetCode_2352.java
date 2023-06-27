package custom.code_2023_06;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @ClassName LeetCode_2352
 * @Author Duys
 * @Description
 * @Date 2023/6/6 10:55
 **/
// 2352. 相等行列对
public class LeetCode_2352 {
    public int equalPairs(int[][] grid) {
        Map<String, Integer> rows = new HashMap<>();
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < n - 1; j++) {
                builder.append(grid[i][j]);
                builder.append("-");
            }
            builder.append(grid[i][n - 1]);
            rows.put(builder.toString(), rows.getOrDefault(builder.toString(), 0) + 1);
        }
        int ans = 0;
        // 开始来迭代
        for (int j = 0; j < n; j++) {
            StringBuilder cur = new StringBuilder();
            for (int i = 0; i < n - 1; i++) {
                cur.append(grid[i][j]);
                cur.append("-");
            }
            cur.append(grid[n - 1][j]);
            ans += rows.getOrDefault(cur.toString(), 0);
        }
        return ans;
    }

    public static int test() {
        try {
            int a = 1 / 0;
            return 0;
        } catch (Exception e) {
            return 1;
        } finally {
            return 2;
        }
    }

    public static void main(String[] args) {
        System.out.println(Math.round(-11.3));
        System.out.println(Math.floor(-11.3 + 0.5));
        System.out.println(test());
        byte a = 127;
        byte b = (byte) (a + 1);
        System.out.println(b);
        Queue queue;
    }
}





