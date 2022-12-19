package custom.code_2022_12;

/**
 * @ClassName LeetCode_1347
 * @Author Duys
 * @Description
 * @Date 2022/12/13 14:24
 **/
// 1347. 制造字母异位词的最小步骤数
public class LeetCode_1347 {
    public static int minSteps(String s, String t) {
        int[] cnt1 = new int[26];
        for (char c : s.toCharArray()) {
            cnt1[c - 'a']++;
        }
        int[] cnt2 = new int[26];
        for (char c : t.toCharArray()) {
            cnt2[c - 'a']++;
        }
        int more1 = 0; // s多的
        int more2 = 0; // t少的
        for (int i = 0; i < 26; i++) {
            if (cnt1[i] == cnt2[i]) {
                continue;
            }
            if (cnt1[i] > cnt2[i]) {
                more1 += cnt1[i] - cnt2[i];
            } else {
                more2 += cnt2[i] - cnt1[i];
            }

        }
        if (more1 == more2) {
            return more1;
        }
        return 0;
    }

    public static void main(String[] args) {
        // leetcode
        // practice
        System.out.println(minSteps("friend", "family"));
    }
}
