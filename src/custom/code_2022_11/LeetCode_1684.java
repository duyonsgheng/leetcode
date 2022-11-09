package custom.code_2022_11;

/**
 * @ClassName LeetCode_1684
 * @Author Duys
 * @Description
 * @Date 2022/11/8 9:18
 **/
// 1684. 统计一致字符串的数目
public class LeetCode_1684 {

    public static int countConsistentStrings(String allowed, String[] words) {
        int[] source = new int[26];
        for (char c : allowed.toCharArray()) {
            if (source[c - 'a'] == 0) {
                source[c - 'a'] = 1;
            }
        }
        int ans = 0;
        for (String word : words) {
            boolean flag = true;
            for (char c : word.toCharArray()) {
                if (source[c - 'a'] == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] words = {"cc", "acd", "b", "ba", "bac", "bad", "ac", "d"};
        String source = "cad";
        System.out.println(countConsistentStrings(source, words));
    }
}
