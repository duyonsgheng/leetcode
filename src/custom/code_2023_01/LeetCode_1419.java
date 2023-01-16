package custom.code_2023_01;

import java.util.Map;

/**
 * @ClassName LeetCode_1419
 * @Author Duys
 * @Description
 * @Date 2023/1/3 17:18
 **/
// 1419. 数青蛙
public class LeetCode_1419 {

    public int minNumberOfFrogs(String croakOfFrogs) {
        int ans = 0, c = 0, r = 0, o = 0, a = 0, k = 0;
        for (int i = 0; i < croakOfFrogs.length(); i++) {
            char cur = croakOfFrogs.charAt(i);
            if (cur == 'c') {
                if (k > 0) {
                    k--;
                } else {
                    ans++;
                }
                c++;
            } else if (cur == 'r') {
                c--;
                r++;
            } else if (cur == 'o') {
                r--;
                o++;
            } else if (cur == 'a') {
                o--;
                a++;
            } else if (cur == 'k') {
                a--;
                k++;
            }
            if (c < 0 || r < 0 || o < 0 || a < 0) {
                break;
            }
        }
        if (c != 0 || r != 0 || o != 0 || a != 0) {
            return -1;
        }
        return ans;
    }
}
