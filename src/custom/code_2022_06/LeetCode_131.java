package custom.code_2022_06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode_131
 * @Author Duys
 * @Description
 * @Date 2022/6/15 12:55
 **/
// 131. 分割回文串
// 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
//回文串 是正着读和反着读都一样的字符串。
public class LeetCode_131 {


    public static List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n]; // i到j范围上是不是回文
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], true);
        }
        char[] str = s.toCharArray();
        // 枚举以每一个位置开头
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = str[i] == str[j] && dp[i + 1][j - 1];
            }
        }
        process(str, 0, dp, ans, new ArrayList<>());
        return ans;
    }

    public static void process(char[] str, int index, boolean[][] dp, List<List<String>> ans, List<String> path) {
        if (index == str.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < str.length; i++) {
            if (dp[index][i]) {
                path.add(new String(str, index, i + 1 - index));
                process(str, i + 1, dp, ans, path);
                path.remove(path.size() - 1);
            }
        }
    }

    // 暴力做法，以每一个字符为中点，看看能不能分割，使用manacher算出每一个位置的回文半径
    public static List<List<String>> partition1(String s) {
        if (s == null || s.length() < 1) {
            return null;
        }
        char[] manacher = manacher(s);
        // 回文半径数组
        int[] pArr = new int[manacher.length];
        int R = -1; // 回文半径的最右边的下一个不可达位置
        int C = -1; // 取得当前半径对应的中心点
        for (int i = 0; i < manacher.length; i++) {
            // i在R的范围内 ,至少有一个范围不用去验证。要么是i的对称点，要么是i到R的距离
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            // 已经有一个最小的距离了，看看能不能扩得更远
            while (i + pArr[i] < manacher.length && i - pArr[i] > -1 && manacher[i + pArr[i]] == manacher[i - pArr[i]]) {
                pArr[i]++;
            }
            // 看看能不能更新 R 和 C
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
        }
        // 根据算到的半径数组来得出结果了，只要偶数位置
        int[] pArr1 = new int[s.length()];
        int index = 0;
        for (int i = 0; i < manacher.length; i++) {
            if ((i & 1) == 0) {
                continue;
            }
            pArr1[index++] = pArr[i];
        }
        return null;
    }

    public static char[] manacher(String s) {
        char[] str = s.toCharArray();
        char[] manacher = new char[(str.length << 1) + 1];
        int index = 0;
        for (int i = 0; i < manacher.length; i++) {
            manacher[i] = (i & 1) == 0 ? '#' : str[index++];
        }
        return manacher;
    }

    public static void main(String[] args) {
        String str = "abcdefg";
        // index = 2
        //
        char[] chars = str.toCharArray();
        System.out.println(new String(chars, 2, 1));
        partition(str);
    }
}
