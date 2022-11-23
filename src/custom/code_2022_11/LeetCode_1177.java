package custom.code_2022_11;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1177
 * @Author Duys
 * @Description
 * @Date 2022/11/22 15:03
 **/
// 1177. 构建回文串检测
public class LeetCode_1177 {

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            // 前缀状态
            sum[i] = sum[i - 1] ^ (1 << (s.charAt(i) - 'a'));
        }
        List<Boolean> ans = new ArrayList<>();
        for (int[] q : queries) {
            int status = sum[q[1] + 1] ^ sum[q[0]];
            // 统计状态中1的个数，表示奇数出现了几个
            ans.add(Integer.bitCount(status) <= (1 + (q[2] << 1)));
        }
        return ans;
    }
}
