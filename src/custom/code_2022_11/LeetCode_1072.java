package custom.code_2022_11;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1072
 * @Author Duys
 * @Description
 * @Date 2022/11/9 9:29
 **/
// 1072. 按列翻转得到最大值等行数
public class LeetCode_1072 {

    // 贪心策略，如果这一行开始是0，则按照原来的状态记录；如果这一行开始是1，则这一行都反转
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        Map<String, Integer> count = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            StringBuffer sb = new StringBuffer();
            if (matrix[i][0] == 1) {
                for (int j = 0; j < m; j++) {
                    sb.append(1 - matrix[i][j]);
                }
            } else {
                for (int j = 0; j < m; j++) {
                    sb.append(matrix[i][j]);
                }
            }
            count.put(sb.toString(), count.getOrDefault(sb.toString(), 0) + 1);
            ans = Math.max(ans, count.get(sb.toString()));
        }
        return ans;
    }
}
