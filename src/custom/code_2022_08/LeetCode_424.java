package custom.code_2022_08;

/**
 * @ClassName LeetCode_424
 * @Author Duys
 * @Description
 * @Date 2022/8/9 17:02
 **/
// 424. 替换后的最长重复字符
public class LeetCode_424 {

    // 滑动窗口
    // l到r范围上，所有字符串的次数 - 最多字符串的次数 = 其他字符串 <= k 就是满足的
    public static int characterReplacement(String s, int k) {
        char[] str = s.toCharArray();
        int[] count = new int[26];
        int ans = 0;
        int max = 0;
        int sum = 0;
        for (int l = 0, r = 0; r < str.length; r++) {
            count[str[r] - 'A']++;
            sum++;
            max = Math.max(max, count[str[r] - 'A']);
            while (!(sum - max <= k)) {
                // 窗口收缩
                count[str[l++] - 'A']--;
                sum--;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public boolean check(int[] count, int k) {
        int sum = 0;
        int max = 0;
        for (int i = 0; i < count.length; i++) {
            max = Math.max(count[i], max);
            sum += count[i];
        }
        return sum - max <= k;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("ABAB", 2));
    }
}
