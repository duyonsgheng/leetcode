package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2125
 * @date 2023年08月22日
 */
// 2125. 银行中的激光束数量
public class LeetCode_2125 {
    public int numberOfBeams(String[] bank) {
        int n = bank.length, m = bank[0].length();
        char[][] arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = bank[i].charAt(j);
            }
        }
        int ans = 0;
        int last = -1;
        int lasti = -1;
        for (int i = 0; i < n; i++) {
            int check = check(arr[i]);
            if (check != 0) {
                last = check;
                lasti = i;
                break;
            }
        }
        if (lasti == -1) {
            return 0;
        }
        for (int i = lasti + 1; i < n; i++) {
            int check = check(arr[i]);
            if (check != 0) {
                ans += check * last;
                last = check;
                lasti = i;
            }
        }
        return ans;
    }

    public int check(char[] arr) {
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            cnt += arr[i] == '1' ? 1 : 0;
        }
        return cnt;
    }
}
