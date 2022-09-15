package custom.code_2022_07;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_306
 * @Author Duys
 * @Description
 * @Date 2022/7/14 15:59
 **/
// 306. 累加数
// 累加数 是一个字符串，组成它的数字可以形成累加序列。
// 112358
// 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
public class LeetCode_306 {

    public static boolean isAdditiveNumber(String num) {
        return dfs(num.toCharArray(), 0, new ArrayList<>());
    }

    public static boolean dfs(char[] str, int index, List<List<Integer>> path) {
        int m = path.size();
        if (index == str.length) {
            return m >= 3;
        }
        int max = str[index] == '0' ? index + 1 : str.length;
        List<Integer> cur = new ArrayList<>();
        for (int i = index; i < max; i++) {
            cur.add(0, str[i] - '0');
            if (m < 2 || check(path.get(m - 2), path.get(m - 1), cur)) {
                path.add(cur);
                if (dfs(str, i + 1, path)) {
                    return true;
                }
                path.remove(path.size() - 1);
            }
        }
        return false;
    }

    public static boolean check(List<Integer> a, List<Integer> b, List<Integer> c) {
        List<Integer> ans = new ArrayList<>();
        int num = 0;
        for (int i = 0; i < a.size() || i < b.size(); i++) {
            if (i < a.size()) {
                num += a.get(i);
            }
            if (i < b.size()) {
                num += b.get(i);
            }
            ans.add(num % 10);
            num /= 10;
        }
        if (num > 0) {
            ans.add(num);
        }
        boolean ok = c.size() == a.size();
        for (int i = 0; i < c.size() && ok; i++) {
            if (c.get(i) != a.get(i)) {
                ok = false;
            }
        }
        return ok;
    }

    public static void main(String[] args) {
        System.out.println(isAdditiveNumber("199100199"));
    }
}
