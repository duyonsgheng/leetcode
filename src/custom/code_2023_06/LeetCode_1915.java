package custom.code_2023_06;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1915
 * @date 2023年06月29日
 */
// 1915. 最美子字符串的数目
// https://leetcode.cn/problems/number-of-wonderful-substrings/
public class LeetCode_1915 {
    // 字符只有10种
    // 状态压缩了
    public long wonderfulSubstrings(String word) {
        char[] arr = word.toCharArray();
        int[] cnt = new int[1 << 10];
        cnt[0] = 1; // 一个字符也没有，算一种
        long ans = 0;
        int pre = 0;
        for (char c : arr) {
            pre ^= (1 << (c - 'a'));// 计算前缀
            ans += cnt[pre]; // 次数位偶数
            // 枚举奇数出现1次的时候
            for (int i = 0; i < 10; i++) {
                ans += cnt[pre ^ (1 << i)];
            }
            cnt[pre]++;
        }
        return ans;
    }
}
