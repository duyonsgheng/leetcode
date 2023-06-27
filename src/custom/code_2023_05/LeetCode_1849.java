package custom.code_2023_05;

/**
 * @ClassName LeetCode_1849
 * @Author Duys
 * @Description
 * @Date 2023/5/30 13:35
 **/
// 1849. 将字符串拆分为递减的连续值
public class LeetCode_1849 {
    // z枚举第一个字串
    public boolean splitString(String s) {
        long pre = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            pre = pre * 10 + s.charAt(i) - '0';
            if (pre > 10000000000L) { // 总共才9位数
                return false;
            }
            if (dfs(s, pre, i + 1)) {
                return true;
            }
        }
        return false;
    }

    public boolean dfs(String s, long pre, int index) {
        if (index == s.length()) {
            return true;
        }
        long p = 0;
        for (int i = index; i < s.length(); i++) {
            p = p * 10 + s.charAt(i) - '0';
            if (p > 10000000000L) {
                return false;
            }
            // 当前的值也满足和前面的值相差1，后面的也可以搞定，
            if (pre - 1 == p && dfs(s, p, i + 1)) {
                return true;
            }
            if (p >= pre) {
                return false;
            }
        }
        return false;
    }

}

