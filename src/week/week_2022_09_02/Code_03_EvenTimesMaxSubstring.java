package week.week_2022_09_02;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_03_EvenTimesMaxSubstring
 * @Author Duys
 * @Description
 * @Date 2022/9/15 13:48
 **/
// 来自微软
// 给定一个字符串s，其中都是英文小写字母
// 如果s中的子串含有的每种字符都是偶数个
// 那么这样的子串就是达标子串，子串要求是连续串
// 返回s中达标子串的最大长度
// 1 <= s的长度 <= 10^5
// 字符种类都是英文小写
public class Code_03_EvenTimesMaxSubstring {

    // 思路，字符串都是小写的
    // 可以使用整型state的每一位来表示一个字符出现的次数，1表示奇数，0表示偶数
    public static int maxLen(String s) {
        // 记录当前状态出现最早位置
        Map<Integer, Integer> stateIndexMap = new HashMap<>();
        stateIndexMap.put(0, -1);// 空串的时候，在-1位置就出现了
        int state = 0;
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';
            state ^= (1 << index);
            if (stateIndexMap.containsKey(state)) {
                ans = Math.max(ans, i - stateIndexMap.get(state));
            } else {
                stateIndexMap.put(state, i);
            }
        }
        return ans;
    }
}
