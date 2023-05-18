package week.week_2023_04_03;

/**
 * @ClassName Code_01_LeetCode_1234
 * @Author Duys
 * @Description
 * @Date 2023/4/20 13:39
 **/
// https://leetcode.cn/problems/replace-the-substring-for-balanced-string/
public class Code_01_LeetCode_1234 {
    // 思路
    // 1.先词频统计
    // 2.窗口。从每个位置开始往右边扩展，当前窗口内词频统计，以及窗口外得词频统计
    // 3.先看看窗口内得字符数能不能让窗口外得字符所有得持平，再看看剩余得字符数是不是 %4 ==0
    public int balancedString(String s) {
        int n = s.length();
        int[] arr = new int[n];
        int[] cnts = new int[4];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            arr[i] = c == 'W' ? 1 : (c == 'E' ? 2 : (c == 'R' ? 3 : 0));
            cnts[arr[i]]++;
        }
        int ans = n;
        for (int l = 0, r = 0; l < n; l++) {
            while (!ok(cnts, l, r) && r < n) {
                // 如果不满足就一直扩展，那么外面得词频要减少
                cnts[arr[r++]]--;
            }
            if (ok(cnts, l, r)) {
                ans = Math.min(ans, r - l);
            } else {
                // 实在满足不了，下一个位置作为窗口开始
                break;
            }
            // 当前位置算过了，窗口需要缩一个
            cnts[arr[l]]++;
        }
        return ans;
    }

    public boolean ok(int[] cnts, int l, int r) {
        int max = Math.max(Math.max(cnts[0], cnts[1]), Math.max(cnts[2], cnts[3]));
        int changes = max * 4 - cnts[0] - cnts[1] - cnts[2] - cnts[3];
        int rest = r - l - changes;
        return rest >= 0 && rest % 4 == 0;
    }
}
