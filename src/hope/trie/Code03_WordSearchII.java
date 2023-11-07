package hope.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName Code03_WordSearchII
 * @date 2023年09月06日
 */
// https://leetcode.cn/problems/word-search-ii/
// 前缀树的题，暴力展开，但是剪枝很重要
public class Code03_WordSearchII {
    public static List<String> findWords(char[][] board, String[] words) {
        build(words);
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, 1, ans);
            }
        }
        clear();
        return ans;
    }

    // i , j 当前来到的位置
    // t 前缀树的编号
    public static int dfs(char[][] arr, int i, int j, int t, List<String> ans) {
        if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length || arr[i][j] == 0) {
            return 0;
        }
        char cur = arr[i][j];
        int road = cur - 'a';
        t = tree[t][road];
        // 说明没路或者之前已经收集过了
        if (pass[t] == 0) {
            return 0;
        }
        int fix = 0;
        if (end[t] != null) {
            fix++;
            ans.add(end[t]);
            end[t] = null;
        }
        arr[i][j] = 0;
        fix += dfs(arr, i + 1, j, t, ans);
        fix += dfs(arr, i - 1, j, t, ans);
        fix += dfs(arr, i, j + 1, t, ans);
        fix += dfs(arr, i, j - 1, t, ans);
        pass[t] -= fix;
        arr[i][j] = cur;
        return fix;
    }

    public static int maxn = 10001;
    public static int[][] tree = new int[maxn][26];
    public static int[] pass = new int[maxn];
    public static String[] end = new String[maxn];
    public static int cnt;

    public static void build(String[] words) {
        cnt = 1;
        for (String word : words) {
            int cur = 1;
            pass[cur]++;
            for (int i = 0, path; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                if (tree[cur][path] == 0) {
                    tree[cur][path] = ++cnt;
                }
                cur = tree[cur][path];
                pass[cur]++;
            }
            end[cur] = word;
        }
    }

    public static void clear() {
        for (int i = 1; i <= cnt; i++) {
            Arrays.fill(tree[i], 0);
            pass[i] = 0;
            end[i] = null;
        }
    }
}
