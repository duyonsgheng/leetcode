package custom.code_2023_06;

/**
 * @ClassName LeetCode_1898
 * @Author Duys
 * @Description
 * @Date 2023/6/25 11:07
 **/
// 1898. 可移除字符的最大数目
public class LeetCode_1898 {
    // 根据数据量，二分
    public int maximumRemovals(String s, String p, int[] removable) {
        int l = 0, r = removable.length - 1, m = 0;
        while (l <= r) {
            m = l + (r - l) / 2;
            StringBuffer sb = new StringBuffer(s);
            for (int i = 0; i <= m; i++) {
                // 删除
                sb.setCharAt(removable[i], ' ');
            }
            if (!check(sb.toString(), p)) {
                r = m - 1;
            } else l = m + 1;
        }
        return r + 1;
    }

    public boolean check(String s, String p) {
        int n = s.length(), m = p.length(), i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == p.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == m;
    }
}
