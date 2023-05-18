package custom.code_2023_05;

/**
 * @ClassName LeetCode_1717
 * @Author Duys
 * @Description
 * @Date 2023/5/8 10:55
 **/
// 1717. 删除子字符串的最大得分
public class LeetCode_1717 {
    // ab -x
    // ba -y
    // 思路：贪心，策略就是先消除大的分值的，如果x<y，那么a b互换，然后消除ab，最后消除ba，以不是a 或者 b的字符为断点，分为一段一段的算
    public int maximumGain(String s, int x, int y) {
        int n = s.length();
        char[] arr = s.toCharArray();
        // 互换，我们转换为ab是大的分值，先以消除ab，然后没有ab的情况下，消除ba，所以对字符串做出转换
        if (x < y) {
            int tmp = x;
            x = y;
            y = tmp;
            for (int i = 0; i < n; i++) {
                if (arr[i] == 'a') {
                    arr[i] = 'b';
                } else if (arr[i] == 'b') {
                    arr[i] = 'a';
                }
            }
        }
        int ans = 0;
        int i = 0;
        while (i < n) {
            // 跳过干扰字段
            while (i < n && arr[i] != 'a' && arr[i] != 'b') i++;
            int cnta = 0, cntb = 0;
            // 如果遇到不是a 不是b的时候，以当前字符为断点，cnta cntb 都归0
            while (i < n && (arr[i] == 'a' || arr[i] == 'b')) {
                if (arr[i] == 'a') {
                    // 当前这一段里有几个a
                    cnta++;
                } else {
                    if (cnta > 0) {
                        cnta--;
                        ans += x;
                    } else cntb++;
                }
                i++;
            }
            // ab大的分值算完了，看看还能不能做到消除ba
            ans += Math.min(cnta, cntb) * y;
        }
        return ans;
    }
}
