package custom.code_2023_08;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2021
 * @date 2023年08月22日
 */
// 2121. 相同元素的间隔之和
// https://leetcode.cn/problems/intervals-between-identical-elements/
public class LeetCode_2121 {
    // 2,1,3,1,2,3,3
    // 3 . 6 0
    // 3 . 5 1
    // 3 . 2 3
    public long[] getDistances1(int[] arr) {
        int n = arr.length;
        long[] ans = new long[n];
        Map<Integer, Long> indexSum = new HashMap<>();
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (indexSum.containsKey(arr[i])) {
                ans[i] += (long) i * cntMap.get(arr[i]) - indexSum.get(arr[i]);
            }
            indexSum.put(arr[i], indexSum.getOrDefault(arr[i], 0l) + i);
            cntMap.put(arr[i], cntMap.getOrDefault(arr[i], 0) + 1);
        }
        indexSum.clear();
        cntMap.clear();
        for (int i = n - 1; i >= 0; i--) {
            if (indexSum.containsKey(arr[i])) {
                ans[i] += indexSum.get(arr[i]) - (long) i * cntMap.get(arr[i]);
            }
            indexSum.put(arr[i], indexSum.getOrDefault(arr[i], 0l) + i);
            cntMap.put(arr[i], cntMap.getOrDefault(arr[i], 0) + 1);
        }
        return ans;
    }

    public long[] getDistances(int[] arr) {
        //前缀，<key,val>表示值为key的前面一个相同的下标为val[0]，相同的个数为val[1]
        Map<Integer, int[]> m1 = new HashMap<>();
        int n = arr.length;
        long[] re1 = new long[n];
        for (int i = 0; i < n; i++) {
            int[] orDefault = m1.getOrDefault(arr[i], new int[2]);
            //当其前面有与他下相同的时候。相同的下标为ordefaule[0],相同了几个为orderfault[1]
            if (orDefault[1] != 0) {
                re1[i] += re1[orDefault[0]] + (i - orDefault[0]) * orDefault[1];
            }
            orDefault[0] = i;
            orDefault[1]++;
            m1.put(arr[i], orDefault);
        }
        //后缀
        Map<Integer, int[]> m2 = new HashMap<>();
        long[] re2 = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            int[] orDefault = m2.getOrDefault(arr[i], new int[2]);
            //当其后面有与他下相同的时候。相同的下标为ordefaule[0],相同了几个为orderfault[1]
            if (orDefault[1] != 0) {
                re2[i] += re2[orDefault[0]] + (orDefault[0] - i) * orDefault[1];
            }
            orDefault[0] = i;
            orDefault[1]++;
            m2.put(arr[i], orDefault);
        }
        long[] re = new long[n];
        for (int i = 0; i < n; i++) {
            re[i] = re1[i]+re2[i];
        }
        return re;
    }
}
