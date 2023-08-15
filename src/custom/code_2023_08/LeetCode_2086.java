package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2086
 * @date 2023年08月14日
 */
// 2086. 从房屋收集雨水需要的最少水桶数
// https://leetcode.cn/problems/minimum-number-of-food-buckets-to-feed-the-hamsters/
public class LeetCode_2086 {
    public int minimumBuckets(String hamsters) {
        if ("H".equals(hamsters) || hamsters.startsWith("HH") || hamsters.endsWith("HH") || hamsters.contains("HHH")) {
            return -1;
        }
        char[] arr = hamsters.toCharArray();
        int ans = 0;
        for (char c : arr) {
            ans += c == 'H' ? 1 : 0;
        }
        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i] == 'H' && arr[i + 1] == '.' && arr[i + 2] == 'H') {
                ans--;
                i += 2;
            }
        }
        return ans;
    }
}
