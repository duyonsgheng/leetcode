package custom.code_2023_01;

/**
 * @ClassName LeetCode_1456
 * @Author Duys
 * @Description
 * @Date 2023/1/17 10:23
 **/
// 1456. 定长子串中元音的最大数目
public class LeetCode_1456 {
    // 窗口
    public static int maxVowels(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                arr[i] = '0';
            }
        }
        int cnt = 0;
        // 窗口大小为k
        for (int i = 0; i < k; i++) {
            if (arr[i] == '0') {
                cnt++;
            }
        }
        // a b c i i i d e f
        // 0 1 2 3 4 5 6 7 8
        int ans = cnt;
        for (int l = 0, r = k; r < n; r++, l++) {
            // 右边进一个
            // 左边出一个
            if (arr[r] == '0') {
                cnt++;
            }
            if (arr[l] == '0') {
                cnt--;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(maxVowels("abciiidef", 3));
        System.out.println(maxVowels("aeiou", 2));
        System.out.println(maxVowels("leetcode", 3));
        System.out.println(maxVowels("rhythms", 4));
        System.out.println(maxVowels("tryhard", 1));
    }
}
