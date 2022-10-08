package custom.code_2022_09;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_01_02
 * @Author Duys
 * @Description
 * @Date 2022/9/27 8:49
 **/
// https://leetcode.cn/problems/check-permutation-lcci/
public class LeetCode_01_02 {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "bca";
        System.out.println(CheckPermutation(s1, s2));
    }

    public static boolean CheckPermutation(String s1, String s2) {
        if (s1 == null || s1.length() <= 0 || s2 == null || s2.length() <= 0) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0) + 1);
        }
        boolean visi = false;
        for (int i = 0; i < s2.length(); i++) {
            if (!map.containsKey(s2.charAt(i))) {
                visi = true;
            }
            if (map.containsKey(s2.charAt(i))) {
                map.put(s2.charAt(i), map.get(s2.charAt(i)) - 1);
            }
            if (map.containsKey(s2.charAt(i)) && map.get(s2.charAt(i)) <= 0) {
                map.remove(s2.charAt(i));
            }
        }
        if (visi) {
            return false;
        }
        return map.isEmpty();
    }
}
