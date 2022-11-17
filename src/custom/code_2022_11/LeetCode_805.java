package custom.code_2022_11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LeetCode_805
 * @Author Duys
 * @Description
 * @Date 2022/11/14 11:31
 **/
// 805. 数组的均值分割
public class LeetCode_805 {
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        int m = n / 2;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int s = 0; s < (1 << m); s++) {
            int tot = 0;
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                if (((s >> i) & 1) == 1) {
                    tot += nums[i];
                    cnt++;
                }
            }
            Set<Integer> set = map.getOrDefault(tot, new HashSet<>());
            set.add(cnt);
            map.put(tot, set);
        }
        for (int s = 0; s < (1 << (n - m)); s++) {
            int tot = 0;
            int cnt = 0;
            for (int i = 0; i < (n - m); i++) {
                if (((s >> i) & 1) == 1) {
                    tot += nums[i + m];
                    cnt++;
                }
            }
            for (int k = Math.max(1, cnt); k < n; k++) {
                if (k * sum % n != 0) {
                    continue;
                }
                int t = k * sum / n;// 平均
                if (!map.containsKey(t - tot)) {
                    continue;
                }
                if (!map.get(t - tot).contains(k - cnt)) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }
}
