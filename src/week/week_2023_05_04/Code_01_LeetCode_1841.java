package week.week_2023_05_04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_01_LeetCode_1841
 * @Author Duys
 * @Description
 * @Date 2023/5/25 9:13
 **/
// 1481. 不同整数的最少数目
public class Code_01_LeetCode_1841 {
    // 词频统计一下，然后先删除词频较少的，剩下的词频多，而且元素就多
    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int n = map.size();
        int[] cnt = new int[n];
        int i = 0;
        for (int num : map.values()) {
            cnt[i++] = num;
        }
        Arrays.sort(cnt);
        for (i = 0; i < n; i++) {
            if (k < cnt[i]) {
                break;
            }
            k -= cnt[i];
        }
        return n - i;
    }
}
