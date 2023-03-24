package custom.code_2023_02;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1557
 * @Author Duys
 * @Description
 * @Date 2023/2/13 10:34
 **/
// 1557. 可以到达所有点的最少点数目
public class LeetCode_1557 {
    // 只需要找到入度为0的点即可
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        // 入度
        int[] in = new int[n];
        for (List<Integer> edge : edges) {
            in[edge.get(1)]++; // 入度
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}
