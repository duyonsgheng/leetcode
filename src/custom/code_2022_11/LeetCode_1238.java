package custom.code_2022_11;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1238
 * @Author Duys
 * @Description
 * @Date 2022/11/28 17:39
 **/
// 1238. 循环码排列
public class LeetCode_1238 {

    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> ans = new ArrayList<>();
        int len = 1 << n;
        boolean[] visited = new boolean[len + 1];
        ans.add(start);
        visited[start] = true;
        process(n, ans, visited, start, len);
        return ans;
    }

    public void process(int n, List<Integer> ans, boolean[] visited, int start, int len) {
        // 结束表示
        if (ans.size() == len) {
            visited[len] = true;
        } else {
            for (int i = 0; i < n; i++) {
                int cur = start ^ (1 << i);
                if (visited[cur]) {
                    continue;
                }
                ans.add(cur);
                visited[cur] = true;
                process(n, ans, visited, cur, len);
                if (visited[len]) {
                    return;
                }
                visited[n] = false;
                ans.remove(ans.size() - 1);
            }
        }
    }
}
