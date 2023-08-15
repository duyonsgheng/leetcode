package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2055
 * @date 2023年08月04日
 */
// 2055. 蜡烛之间的盘子
// https://leetcode.cn/problems/plates-between-candles/
public class LeetCode_2055 {
    // ||**||**|*
    public static int[] platesBetweenCandles(String s, int[][] queries) {
        // 左边最近的蜡烛在哪里
        char[] arr = s.toCharArray();
        int n = arr.length;
        // 左边最近的蜡烛在什么位置，包含自己
        int[] l1 = new int[n];
        int[] r1 = new int[n];
        // **|**|***|
        l1[0] = arr[0] == '|' ? 0 : -1;
        r1[n - 1] = arr[n - 1] == '|' ? n - 1 : -1;
        for (int i = 1, last = l1[0]; i < n; i++) {
            if (arr[i] == '*') {
                l1[i] = last;
            } else {
                l1[i] = i;
                last = i;
            }
        }
        for (int i = n - 2, last = r1[n - 1]; i >= 0; i--) {
            if (arr[i] == '*') {
                r1[i] = last;
            } else {
                r1[i] = i;
                last = i;
            }
        }
        // 每一根蜡烛左边有效的盘子几个
        // 每一根蜡烛右边有效的盘子几个
        int[] cl1 = new int[n];
        int l = 0, cnt = 0;
        boolean pre = false;
        // **|**|***|
        while (l < n) {
            while (l < n && arr[l] == '*') {
                l++;
                cnt++;
            }
            if (l >= n) {
                break;
            }
            // 第一个蜡烛
            if (!pre) {
                cnt = 0;
            } else
                cl1[l] = cnt;
            l++;
            pre = true;
        }
        int qn = queries.length;
        int[] ans = new int[qn];
        for (int i = 0; i < qn; i++) {
            int le = queries[i][0];
            int re = queries[i][1];
            int le1 = r1[le];
            int re1 = l1[re];
            if (le1 >= re1 || le1 == -1 || re1 == -1) {
                continue;
            }
            ans[i] = cl1[re1] - cl1[le1];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ints = platesBetweenCandles("||*", new int[][]{{2, 2}});
        //
        //int[] ints1 = platesBetweenCandles("***|**|*****|**||**|*", new int[][]{{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}});
    }
}
