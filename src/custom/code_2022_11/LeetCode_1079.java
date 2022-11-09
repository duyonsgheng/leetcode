package custom.code_2022_11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName LeetCode_1079
 * @Author Duys
 * @Description
 * @Date 2022/11/9 15:19
 **/
// 1079. 活字印刷
public class LeetCode_1079 {

    public static int numTilePossibilities(String tiles) {
        char[] nums = tiles.toCharArray();
        int[] count = new int[26];
        for (char ch : nums) {
            count[ch - 'A']++;
        }
        return process(count);
    }

    public static int process(int[] count) {
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] == 0) {
                continue;
            }
            ans++;
            count[i]--;
            ans += process(count);
            count[i]++;
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "AAB";
        System.out.println(numTilePossibilities(str));
    }
}
