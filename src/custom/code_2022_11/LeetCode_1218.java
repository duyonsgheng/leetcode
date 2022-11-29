package custom.code_2022_11;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1218
 * @Author Duys
 * @Description
 * @Date 2022/11/25 16:15
 **/
// 1218. 最长定差子序列
public class LeetCode_1218 {

    // 给你一个整数数组arr和一个整数difference，请你找出并返回 arr中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    // 动态规划
    public int longestSubsequence(int[] arr, int difference) {
        int ans = 0;
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(arr[i], map.getOrDefault(arr[i] - difference, 0) + 1);
            ans = Math.max(ans, map.get(arr[i]));
        }
        return ans;
    }
}
