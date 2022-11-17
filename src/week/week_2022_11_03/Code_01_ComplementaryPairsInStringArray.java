package week.week_2022_11_03;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_01_ComplementaryPairsInStringArray
 * @Author Duys
 * @Description
 * @Date 2022/11/17 9:19
 **/
// 来自亚马逊
// 给定一个字符串数组strs，其中每个字符串都是小写字母组成的
// 如果i < j，并且strs[i]和strs[j]所有的字符随意去排列能组成回文串
// 那么说(i,j)叫做一个互补对(complementary)
// 求strs中有多少个互补对
// strs长度 <= 3 * 10^5
// 单个字符串长度 <= 10^5
// strs里所有字符串总长度 <= 10^6
public class Code_01_ComplementaryPairsInStringArray {
    // 思路：
    // 数据量 挺大，如果遍历数组然后遍历字符串，那肯定超时像一个办法
    // 如果能随意组合成一个回文，一定只有两种可能
    // 可能1：所有的字母出现的次数都是偶数
    // 可能2：只有一个字母的次数出现了奇数次，其他的都是偶数次
    // 每一个字符串的字母都是小写，那么我们开可以使用一个整数来标记每一个字符串中每个字符出现的次数是奇数还是偶数
    // 偶数就用0表示状态，奇数就用1表示
    // 比如当前 0 1 0 1 1 0
    // 那么我们 期望另外的和他一样的状态，也可以出现只有一位不同，其他的都相同那么也可以保证只有一个字符出现了奇数，其他的都是偶数
    public static int maxNum(String[] strs) {
        // key - 状态 value -出现的次数
        Map<Integer, Integer> count = new HashMap<>();
        int ans = 0;
        for (String str : strs) {
            int status = 0; // 初始都出现了偶数次，表示一个字符也没有，可以算做回文
            for (int i = 0; i < str.length(); i++) {
                status ^= 1 << (str.charAt(i) - 'a'); // 把所有字符出现的次数或到状态里面去
            }
            // 一个也不变出现了多少次
            ans += count.getOrDefault(status, 0);
            for (int i = 0; i < 26; i++) {
                // 只有一位不同
                ans += count.getOrDefault(status ^ (1 << i), 0);
            }
            count.put(status, count.getOrDefault(status, 0) + 1);
        }
        return ans;
    }
}
