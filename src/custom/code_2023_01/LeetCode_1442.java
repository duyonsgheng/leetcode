package custom.code_2023_01;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1442
 * @Author Duys
 * @Description
 * @Date 2023/1/5 16:15
 **/
// 1442. 形成两个异或相等数组的三元组数目
public class LeetCode_1442 {

    public int countTriplets(int[] arr) {
        int n = arr.length;
        int[] xorSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            xorSum[i + 1] = xorSum[i] ^ arr[i];
        }
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> total = new HashMap<>();
        int ans = 0;
        for (int k = 0; k < n; k++) {
            if (count.containsKey(xorSum[k + 1])) {
                ans += count.get(xorSum[k + 1]) * k - total.get(xorSum[k + 1]);
            }
            count.put(xorSum[k], count.getOrDefault(xorSum[k], 0) + 1);
            total.put(xorSum[k], total.getOrDefault(xorSum[k], 0) + k);
        }
        return ans;
    }
}
