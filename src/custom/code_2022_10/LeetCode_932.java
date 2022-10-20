package custom.code_2022_10;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_932
 * @Author Duys
 * @Description
 * @Date 2022/10/18 16:03
 **/
// 932. 漂亮数组
public class LeetCode_932 {
    // 分治：
    // 对于 1~n 一共有 (n+1)/2个奇数 n/2 个偶数
    // 形成一个奇数数组和偶数数组
    // 然后进行拼接，因为奇数+偶数还是奇数。
    // arr[i] * 2  != arr[k] + arr[p]
    // 等号左边是偶数，右边只需要满足不同时为奇数，不同时为偶数就可以了
    public int[] beautifulArray(int n) {
        Map<Integer, int[]> map = new HashMap<>();
        return process(n, map);
    }

    // 当前有n个数，1到n，返回这个区间上形成的漂亮数组
    private int[] process(int n, Map<Integer, int[]> map) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int[] ans = new int[n];
        if (n == 1) {
            ans[0] = 1;
        } else {
            // 如果n不为1
            int index = 0;
            // 奇数
            for (int i : process((n + 1) / 2, map)) {
                ans[index++] = 2 * i - 1;
            }
            // 偶数
            for (int i : process(n / 2, map)) {
                ans[index++] = 2 * i;
            }
        }
        map.put(n, ans);
        return ans;
    }
}
