package custom.code_2023_04;

/**
 * @ClassName LeetCode_1625
 * @Author Duys
 * @Description
 * @Date 2023/4/19 17:03
 **/
// 1625. 执行操作后字典序最小的字符串
public class LeetCode_1625 {

    public String findLexSmallestString(String s, int a, int b) {
        int n = s.length();
        boolean[] visited = new boolean[n];
        String ans = s;
        s += s;
        for (int i = 0; !visited[i]; i = (i + b) % n) {
            visited[i] = true;
            for (int j = 0; j < 10; j++) {
                int limit = b % 2 == 0 ? 0 : 9;
                for (int k = 0; k <= limit; k++) {
                    char[] cur = s.substring(i, i + n).toCharArray();
                    // +上a
                    for (int p = 1; p < n; p += 2) {
                        cur[p] = (char) ('0' + (cur[p] - '0' + j * a) % 10);
                    }
                    // 转动b
                    for (int p = 0; p < n; p += 2) {
                        cur[p] = (char) ('0' + (cur[p] - '0' + k * a) % 10);
                    }
                    String sc = new String(cur);
                    if (sc.compareTo(ans) < 0) {
                        ans = sc;
                    }
                }
            }
        }
        return ans;
    }
}
