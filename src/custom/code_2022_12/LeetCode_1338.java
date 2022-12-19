package custom.code_2022_12;

import java.util.Arrays;

/**
 * @ClassName LeetCode_1338
 * @Author Duys
 * @Description
 * @Date 2022/12/13 9:36
 **/
// 1338. 数组大小减半
public class LeetCode_1338 {
    public int minSetSize(int[] arr) {
        int max = 100001;
        int n = arr.length;
        Integer[] cnt = new Integer[max];
        Arrays.fill(cnt, 0);
        for (int num : arr) {
            cnt[num]++;
        }
        Arrays.sort(cnt, (a, b) -> b - a);
        int sum = 0;
        for (int i = 0; i < max; i++) {
            sum += cnt[i];
            if (sum >= n / 2) {
                return i + 1;
            }
        }
        return 0;
    }
}
