package custom.code_2023_04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @ClassName LeetCode_1647
 * @Author Duys
 * @Description
 * @Date 2023/4/23 11:25
 **/
// 1647. 字符频次唯一的最小删除次数
public class LeetCode_1647 {

    public static int minDeletions(String s) {
        char[] cs = s.toCharArray();
        int[] cnts = new int[26];
        for (int i = 0; i < cs.length; i++) {
            cnts[cs[i] - 'a']++;
        }
        int ans = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < cnts.length; i++) {
            int cur = cnts[i];
            while (cur != 0 && !set.add(cur)) {
                cur--;
                ans++;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(minDeletions("abcabc"));

    }
}
