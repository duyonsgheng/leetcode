package custom.code_2023_04;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @ClassName LeetCode_1657
 * @Author Duys
 * @Description
 * @Date 2023/4/24 11:20
 **/
// 1657. 确定两个字符串是否接近
public class LeetCode_1657 {
    // 1.词频一样
    // 2.词频一样的字符是不是一样多 ,比如word1中 词频为3的一个
    // cabbba  aabbbc
    // abbccc  bbccca
    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int[] wct1 = new int[26];
        int[] wct2 = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            wct1[word1.charAt(i) - 'a']++;
            wct2[word2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            // 相同的位置如果都为空，说明必然换不了，有剩余的
            if (wct1[i] == 0 ^ wct2[i] == 0) {
                return false;
            }
        }
        Arrays.sort(wct1);
        Arrays.sort(wct2);
        for (int i = 0; i < 26; i++) {
            if (wct1[i] != wct2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(true ^ true);
        System.out.println(true ^ false);
        System.out.println(false ^ false);
    }
}
