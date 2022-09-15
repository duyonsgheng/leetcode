package custom.code_2022_07;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_386
 * @Author Duys
 * @Description
 * @Date 2022/7/22 13:41
 **/
// 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
//你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
public class LeetCode_386 {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0, j = 1; i < n; i++) {
            ans.add(j);
            if (j * 10 <= n) {
                j *= 10;
            } else {
                while (j % 10 == 9 || j + 1 > n) {
                    j /= 10;
                }
                j++;
            }
        }
        return ans;
    }

    public List<Integer> lexicalOrder1(int n) {
        List<Integer> ans = new ArrayList<>();
        // 第一位数字
        for (int i = 1; i <= 9; i++) {
            dfs(i, n, ans);
        }
        return ans;
    }

    public void dfs(int cur, int n, List<Integer> ans) {
        if (cur > n) {
            return;
        }
        ans.add(cur);
        // 后面的数字
        for (int i = 0; i <= 9; i++) {
            dfs(cur * 10 + i, n, ans);
        }
    }
}
