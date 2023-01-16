package custom.code_2023_01;

/**
 * @ClassName LeetCode_1433
 * @Author Duys
 * @Description
 * @Date 2023/1/4 16:39
 **/
// 1433. 检查一个字符串是否可以打破另一个字符串
public class LeetCode_1433 {
    public static boolean checkIfCanBreak(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        for (char c : s1.toCharArray()) {
            arr1[c - 'a']++;
        }
        for (char c : s2.toCharArray()) {
            arr2[c - 'a']++;
        }
        int[] sum1 = new int[26];
        int[] sum2 = new int[26];
        sum1[0] = arr1[0];
        sum2[0] = arr2[0];
        for (int i = 1; i < 26; i++) {
            sum1[i] = sum1[i - 1] + arr1[i];
        }
        for (int i = 1; i < 26; i++) {
            sum2[i] = sum2[i - 1] + arr2[i];
        }
        boolean ans = true;
        for (int i = 0; i < 26; i++) {
            if (sum1[i] < sum2[i]) {
                ans = false;
                break;
            }
        }
        if (ans) {
            return ans;
        }
        ans = true;
        for (int i = 0; i < 26; i++) {
            if (sum2[i] < sum1[i]) {
                ans = false;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //System.out.println(checkIfCanBreak("leetcodee", "interview"));
        System.out.println(checkIfCanBreak("abe", "acd"));
    }
}
