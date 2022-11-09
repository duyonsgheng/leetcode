package custom.code_2022_11;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1090
 * @Author Duys
 * @Description
 * @Date 2022/11/8 13:41
 **/
// 1090. 受标签影响的最大值
public class LeetCode_1090 {


    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int n = values.length;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        // 记录相同的标签用了几次了
        Map<Integer, Integer> count = new HashMap<>();
        // 按照从大到小排序
        Arrays.sort(arr, (a, b) -> values[b] - values[a]);
        int ans = 0, sum = 0;
        for (int i = 0; i < n && sum < numWanted; i++) {
            int v = values[arr[i]];
            int l = labels[arr[i]];
            int pre = count.getOrDefault(l, 0);
            // 如果相同的标签没达到上限，可以用
            if (pre < useLimit) {
                ans += v;
                count.put(l, pre + 1);
                // 有几个数了
                sum++;
            }
        }
        return ans;
    }

}
