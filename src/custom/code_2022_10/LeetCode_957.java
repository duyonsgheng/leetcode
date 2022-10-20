package custom.code_2022_10;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_957
 * @Author Duys
 * @Description
 * @Date 2022/10/20 16:56
 **/
// 957. N 天后的牢房
public class LeetCode_957 {

    public int[] prisonAfterNDays(int[] cells, int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int state = 0;
        // 初始状态
        for (int i = 0; i < 8; i++) {
            if (cells[i] > 0) {
                state ^= 1 << i;
            }
        }
        // 天数从后往前推
        // 假设第n天就是初始状态，推第1天是啥状态，一样的
        while (n > 0) {
            // 上一次遇到了这个状态是在多少天
            // 那么这一次就不需要再次重复计算了
            if (map.containsKey(state)) {
                n %= map.get(state) - n;
            }
            // 把状态新的天数给更换
            map.put(state, n);
            if (n >= 1) {
                n--;
                // 计算下一个状态
                state = nextDay(state);
            }
        }
        int[] ans = new int[8];
        // 收集答案
        for (int i = 0; i < 8; i++) {
            if (((state >> i) & 1) > 0) {
                ans[i] = 1;
            }
        }
        return ans;
    }

    // 因为首位和末尾没有双领居
    public int nextDay(int state) {
        int ans = 0;
        for (int i = 1; i <= 6; i++) {
            // 两边都是1或者两边都是0
            if (((state >> (i - 1)) & 1) == ((state >> (i + 1)) & 1)) {
                ans ^= 1 << i;
            }
        }
        return ans;
    }
}
