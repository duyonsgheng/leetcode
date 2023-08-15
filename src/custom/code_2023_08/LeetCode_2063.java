package custom.code_2023_08;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2063
 * @date 2023年08月07日
 */
// 2063. 所有子字符串中的元音
// https://leetcode.cn/problems/vowels-of-all-substrings/
public class LeetCode_2063 {
    public static long countVowels(String word) {
        char[] arr = word.toCharArray();
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int n = arr.length;
        long cnt = set.contains(arr[0]) ? 1 : 0;
        long ans = cnt;
        for (int i = 1; i < n; i++) {
            // 子序列包含当前位置
            // 子序列不包含当前位置
            if (set.contains(arr[i])) {
                cnt += (i + 1);
            }
            ans += cnt;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countVowels("aba"));
    }
}
